package com.hcoder.clothingstoremanagement.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hcoder.clothingstoremanagement.entity.Bill;
import com.hcoder.clothingstoremanagement.entity.Client;
import com.hcoder.clothingstoremanagement.entity.ClientRecord;
import com.hcoder.clothingstoremanagement.entity.Incoming;
import com.hcoder.clothingstoremanagement.entity.Spending;
import com.hcoder.clothingstoremanagement.entity.Warehouse;
import com.hcoder.clothingstoremanagement.service.UserService;

@Controller
public class Navigator {

	@Autowired
	UserService userService;

	@RequestMapping("/home")
	public String goHome() {

		return "home";
	}

	@RequestMapping("/incoming")
	public String getIncoming(@RequestParam("date") String theDate, Model theModel) {

		List<Incoming> incomings;

		if (theDate.equals("")) {

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
	public String getBill(@RequestParam("date") String theDate, Model theModel) {

		List<Bill> bills;

		if (theDate.equals("")) {

			theDate = LocalDate.now().toString();
			bills = userService.getAllBills();

		} else {

			bills = userService.getBillsByDate(theDate);

		}

		int listSize = bills.size();
		int gainTotal = 0;
		
		Bill item ;
		
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

	@PostMapping("/make-spending")
	public String goToClientAccount(@ModelAttribute("spending") Spending spending) {

		spending.setDate(LocalDate.now().toString());
		userService.makeSpendingOpertaion(spending);

		return "redirect:/spending";
	}

	@RequestMapping("/get-spendings-by-date")
	public String getSpendingsByDate(@RequestParam("date") String theDate, Model theModel) {

		List<Spending> spendings = userService.getSpendingsByDate(theDate);

		theModel.addAttribute("spending", new Spending());
		theModel.addAttribute("spendings", spendings);
		theModel.addAttribute("spendingTotal", userService.getSpendingTotal());

		return "spending";
	}

	@RequestMapping("/spending")
	public String getSpendings(@RequestParam("date") String theDate, Model theModel) {

		List<Spending> spendings;

		if (theDate.equals("")) {

			theDate = LocalDate.now().toString();
			spendings = userService.getAllSpending();

		} else {

			spendings = userService.getSpendingsByDate(theDate);

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

		// get ClientRecord Id From client @ModelAttribute
		int clientRecordId = clientData.getId();

		ClientRecord theClientRecord = userService.getClientRecordById(clientRecordId);

		theClientRecord.setPay(theClientRecord.getPay() + theAmount);

		theClient.setDrawee(theClient.getDrawee() - theAmount);

		userService.saveClient(theClient);
		userService.saveClientRecord(theClientRecord);

		
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

}
