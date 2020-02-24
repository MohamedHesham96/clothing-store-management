package com.hcoder.clothingstoremanagement.controllers;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hcoder.clothingstoremanagement.entity.Bill;
import com.hcoder.clothingstoremanagement.entity.Client;
import com.hcoder.clothingstoremanagement.entity.ClientRecord;
import com.hcoder.clothingstoremanagement.entity.Incoming;
import com.hcoder.clothingstoremanagement.entity.Spending;
import com.hcoder.clothingstoremanagement.entity.Trader;
import com.hcoder.clothingstoremanagement.entity.Warehouse;
import com.hcoder.clothingstoremanagement.service.UserService;

@Controller
public class Navigator {

	@Autowired
	UserService userService;
	//
	// @InitBinder
	// public void initBinder(WebDataBinder dataBinder) {
	//
	// StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
	//
	// dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	//
	// }

	private EntityManager entityManager;

	@Autowired
	public Navigator(EntityManager entityManager) {

		this.entityManager = entityManager;
	}

	@RequestMapping("/home")
	public String goHome() {

		Session session = entityManager.unwrap(Session.class);

		String SQL_QUERY = "select date, sum(gain) as gain_a_sum\r\n" + "from bill\r\n" + "group by month(2);";
		Query query = session.createQuery(SQL_QUERY);

		for (Iterator it = query.iterate(); it.hasNext();) {
			Object[] row = (Object[]) it.next();

			System.out.println(">>>>>>>>>>>>>>>>>>> Row: " + row[0]);
		}

		return "index";
	}

	@RequestMapping("/incoming")
	public String getIncoming(@RequestParam(value = "date", required = false) String theDate, Model theModel) {

		List<Incoming> incomings;

		if (theDate == null) {

			theDate = LocalDate.now().toString();
			incomings = userService.GetAllIncoming();

		} else {

			incomings = userService.GetIncomingsByDate(theDate);

		}

		int incomingTotal = userService.getIcomingTotal();
		int warehouseTotal = userService.getWarehouseTotal();

		int soldTotal = incomingTotal - warehouseTotal;

		theModel.addAttribute("date", theDate);
		theModel.addAttribute("items", incomings);
		theModel.addAttribute("incoming", new Incoming());
		theModel.addAttribute("soldTotal", soldTotal);
		theModel.addAttribute("incomingTotal", incomingTotal);
		theModel.addAttribute("warehouseTotal", warehouseTotal);

		return "incoming";
	}

	@PostMapping("/add-incoming")
	public String addIncoming(@ModelAttribute("incoming") Incoming theIncoming, Model theModel) {

		theModel.addAttribute("incoming", new Incoming());

		theIncoming.setDate(LocalDate.now().toString());
		theIncoming.setTotal(theIncoming.getTradePrice() * theIncoming.getQuantity());

		userService.AddIncoming(theIncoming);
		Warehouse warehouse = new Warehouse();

		warehouse.setItem(theIncoming.getItem());
		warehouse.setQuantity(theIncoming.getQuantity());
		warehouse.setTradePrice(theIncoming.getTradePrice());
		warehouse.setStore(theIncoming.getStore());

		userService.addToWarehouse(warehouse);

		return "redirect:/incoming";

	}

	@RequestMapping("/warehouse")
	public String getWarehouse(Model theModel) {

		List<Warehouse> items = userService.getAllWarehouse();

		int soldTotal = userService.getIcomingTotal() - userService.getWarehouseTotal();

		theModel.addAttribute("items", items);
		theModel.addAttribute("bill", new Bill());
		theModel.addAttribute("soldTotal", soldTotal);
		theModel.addAttribute("incomingTotal", userService.getIcomingTotal());
		theModel.addAttribute("warehouseTotal", userService.getWarehouseTotal());
		theModel.addAttribute("clientsList", userService.getAllClients());

		return "warehouse";
	}

	@PostMapping("/add-bill")
	public String addBill(@RequestParam(name = "clientId") int clientId, @RequestParam(name = "payed") int payed,
			@ModelAttribute("bill") Bill theBill) {

		Bill bill = new Bill();
		ClientRecord clientRecord = new ClientRecord();
		Client client = userService.getClientById(clientId);

		bill.setDate(LocalDate.now().toString());
		bill.setQuantity(theBill.getQuantity());
		bill.setPiecePrice(theBill.getPiecePrice());
		bill.setClient(client);

		// Use the Bill ID from the FORM DATA
		Warehouse warehouse = userService.getWarehouseById(theBill.getId());

		// Take from Warehouse the items that sell
		warehouse.setQuantity(warehouse.getQuantity() - bill.getQuantity());

		userService.updateWarehouseQuantity(warehouse);

		int gain = (theBill.getPiecePrice() - warehouse.getTradePrice()) * bill.getQuantity();

		bill.setGain(gain);
		bill.setItem(warehouse.getItem());
		bill.setStore(warehouse.getStore());
		bill.setTradePrice(warehouse.getTradePrice());

		clientRecord.setClient(client);
		clientRecord.setItem(warehouse.getItem());
		clientRecord.setPay(payed);
		clientRecord.setQuantity(bill.getQuantity());
		clientRecord.setPrice(bill.getPiecePrice() * bill.getQuantity());

		int theNewdrawee = client.getDrawee() + clientRecord.getPrice() - clientRecord.getPay();

		client.setDrawee(theNewdrawee);

		userService.addBill(bill);
		userService.saveClientRecord(clientRecord);
		userService.saveClient(client);

		return "redirect:/warehouse";

	}

	@RequestMapping("/bill")
	public String getBill(@RequestParam(value = "date", required = false) String theDate, Model theModel) {

		List<Bill> bills;

		if (theDate == null) {

			theDate = LocalDate.now().toString();
			bills = userService.getAllBills();

		} else {

			bills = userService.getBillsByDate(theDate);

		}

		int listSize = bills.size();
		int gainTotal = 0;

		Bill item;

		for (int i = 0; i < listSize; i++) {

			item = bills.get(i);

			gainTotal += item.getGain();
		}

		int spendingTotal = userService.getSpendingTotal();

		// صافي الربح
		int total = gainTotal - spendingTotal;

		theModel.addAttribute("date", theDate);
		theModel.addAttribute("total", total);
		theModel.addAttribute("items", bills);
		theModel.addAttribute("gainTotal", gainTotal);
		theModel.addAttribute("spendingTotal", spendingTotal);

		return "bill";
	}

	@RequestMapping("/clients")
	public String getClients(Model theModel) {

		List<Client> clients = userService.getAllClients();

		theModel.addAttribute("clientsList", clients);

		theModel.addAttribute("theClient", new Client());

		theModel.addAttribute("draweeTotal", userService.getClientsDraweeTotal());

		return "clients";
	}

	@RequestMapping("/add-new-client")
	public String addNewClient(@ModelAttribute("theClient") Client theClient) {

		userService.saveClient(theClient);

		return "redirect:/clients";
	}

	@RequestMapping("/clientProfile")
	public String goToClientAccount(@ModelAttribute("clientId") int id, Model theModel) {

		Client client = userService.getClientById(id);
		List<ClientRecord> clientRecords = client.getClientRecords();

		int size = clientRecords.size();
		int totalPayment = 0;

		for (int i = 0; i < size; i++) {

			totalPayment += clientRecords.get(i).getPrice();
		}

		theModel.addAttribute("clientData", client);
		theModel.addAttribute("totalPayment", totalPayment);

		return "client-profile";
	}

	@RequestMapping("/get-spendings-by-date")
	public String getSpendingsByDate(@RequestParam("date") String theDate, Model theModel) {

		List<Spending> spendings = userService.getSpendingsByDate(theDate);

		theModel.addAttribute("spending", new Spending());
		theModel.addAttribute("spendings", spendings);
		theModel.addAttribute("spendingTotal", userService.getSpendingTotal());

		return "spending";
	}

	@PostMapping("/make-spending")
	public String makeSpending(@ModelAttribute("spending") Spending spending, Model theModel,
			BindingResult theBindingResult) {

		spending.setDate(LocalDate.now().toString());
		userService.makeSpendingOpertaion(spending);

		return "redirect:/spending";

	}

	String myDate = LocalDate.now().toString();

	@RequestMapping("/spending")
	public String getSpendings(@RequestParam(value = "date", required = false) String theDate, Model theModel) {

		List<Spending> spendings;

		if (theDate == null) {

			theDate = LocalDate.now().toString();
			spendings = userService.getAllSpending();

			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> " + theDate);

		} else {

			spendings = userService.getSpendingsByDate(theDate);
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> " + theDate);

		}

		theModel.addAttribute("date", theDate);
		theModel.addAttribute("spending", new Spending());
		theModel.addAttribute("spendings", spendings);
		theModel.addAttribute("spendingTotal", userService.getSpendingTotal());

		return "spending";
	}

	@RequestMapping("/pay-off-amount")
	public String payOffAmount(@RequestParam(name = "moneyAmount") int theAmount,
			@RequestParam(name = "clientId") int theClientId, @ModelAttribute("clientData") Client clientData,
			Model theModel) {

		// clientData to get data from form but it's id is for client record id
		// theClient is the object that we will use it to update the client table
		Client theClient = userService.getClientById(theClientId);

		theClient.setDrawee(theClient.getDrawee() - theAmount);

		userService.saveClient(theClient);

		List<ClientRecord> clientRecords = theClient.getClientRecords();

		int size = clientRecords.size();
		int totalPayment = 0;

		for (int i = 0; i < size; i++) {

			totalPayment += clientRecords.get(i).getPrice();
		}

		theModel.addAttribute("clientData", theClient);
		theModel.addAttribute("totalPayment", totalPayment);

		return "client-profile";
	}

	@RequestMapping("/today")
	public String getToday(Model theModel) {

		List<Bill> bills;
		String theDate = LocalDate.now().toString();

		bills = userService.getBillsByDate(theDate);

		int listSize = bills.size();
		int gainTotal = 0;

		Bill item;

		for (int i = 0; i < listSize; i++) {

			item = bills.get(i);

			gainTotal += item.getGain();
		}

		int spendingTotal = userService.getSpendingTotalToday();

		List<Warehouse> warehouseItems = userService.getAllWarehouse();

		// صافي الربح
		int total = gainTotal - spendingTotal;

		theModel.addAttribute("total", total);
		theModel.addAttribute("items", bills);
		theModel.addAttribute("gainTotal", gainTotal);
		theModel.addAttribute("spendingTotal", spendingTotal);
		theModel.addAttribute("warehouseItems", warehouseItems);
		theModel.addAttribute("clientsList", userService.getAllClients());

		return "today";
	}

	@RequestMapping("/add-bills-list")
	public String addBillsList(@RequestParam("itemId") List<String> itemIdList,
			@RequestParam("quantity") List<String> quantityList,
			@RequestParam("piecePrice") List<String> piecePriceList, @RequestParam("payed") List<String> payedList,
			@RequestParam("clientId") List<String> clientIdList) {

		int listSize = clientIdList.size();

		System.out.println("listSize >> " + listSize);

		for (int i = 0; i < listSize; i++) {

			if (!clientIdList.get(i).equals("-1") && !itemIdList.get(i).equals("-1")) {

				Bill theBill = new Bill();
				ClientRecord clientRecord = new ClientRecord();
				Client theClient = new Client();

				System.out.println("clientId >> " + clientIdList.get(i));

				theClient = userService.getClientById(Integer.parseInt(clientIdList.get(i)));

				theBill.setDate(LocalDate.now().toString());
				theBill.setQuantity(Integer.parseInt(quantityList.get(i)));
				theBill.setPiecePrice(Integer.parseInt(piecePriceList.get(i)));
				theBill.setClient(theClient);

				Warehouse warehouse = userService.getWarehouseById(Integer.parseInt(itemIdList.get(i)));
				warehouse.setQuantity(warehouse.getQuantity() - Integer.parseInt(quantityList.get(i)));
				userService.updateWarehouseQuantity(warehouse);

				int gain = (Integer.parseInt(piecePriceList.get(i)) - warehouse.getTradePrice())
						* Integer.parseInt(quantityList.get(i));

				theBill.setGain(gain);
				theBill.setItem(warehouse.getItem());
				theBill.setStore(warehouse.getStore());
				theBill.setTradePrice(warehouse.getTradePrice());

				clientRecord.setClient(theClient);
				clientRecord.setItem(warehouse.getItem());
				clientRecord.setPay(Integer.parseInt(payedList.get(i)));

				System.out.println("Integer.parseInt(quantityList.get(i)) >> " + Integer.parseInt(quantityList.get(i)));

				clientRecord.setQuantity(Integer.parseInt(quantityList.get(i)));

				clientRecord.setPrice(Integer.parseInt(piecePriceList.get(i)) * Integer.parseInt(quantityList.get(i)));

				int theNewdrawee = theClient.getDrawee() + clientRecord.getPrice() - clientRecord.getPay();

				theClient.setDrawee(theNewdrawee);

				userService.addBill(theBill);
				userService.saveClientRecord(clientRecord);

				userService.saveClient(theClient);

				System.out.println(theClient.getName());
			}
		}

		return "redirect:/today";
	}

	@RequestMapping("/traders")
	public String getTraders(Model theModel) {

		List<Trader> traders = userService.getAllTraders();
		int[] tradersTotals = userService.getTradersTotals();

		theModel.addAttribute("tradersList", traders);

		theModel.addAttribute("theTrader", new Trader());

		theModel.addAttribute("payedTotal", tradersTotals[0]);
		theModel.addAttribute("remainingTotal", tradersTotals[1]);

		return "traders";
	}

	@RequestMapping("/add-new-trader")
	public String addNewTrader(@ModelAttribute("theTrader") Trader theTrader) {

		userService.saveTrader(theTrader);

		return "redirect:/traders";
	}

}
