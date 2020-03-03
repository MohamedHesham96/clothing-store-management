package com.hcoder.clothingstoremanagement.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hcoder.clothingstoremanagement.entity.Bill;
import com.hcoder.clothingstoremanagement.entity.Client;
import com.hcoder.clothingstoremanagement.entity.ClientRecord;
import com.hcoder.clothingstoremanagement.entity.Warehouse;
import com.hcoder.clothingstoremanagement.service.UserService;

@Controller
public class Navigator {

	@Autowired
	UserService userService;

	@Autowired
	private HttpSession httpSession;

	private EntityManager entityManager;

	@Autowired
	public Navigator(EntityManager entityManager) {

		this.entityManager = entityManager;
	}

	@RequestMapping("/")
	public String userLogin() {

		return "login";
	}

	@RequestMapping("/logout")
	public String userLogout() {

		httpSession.removeAttribute("username");

		return "login";

	}

	@RequestMapping("/login")
	public String userLogin(@RequestParam("username") String username, @RequestParam("password") String password) {

		if (username.equals("mazen") && password.equals("mazen")) {

			httpSession.setAttribute("username", username);

			httpSession.setAttribute("passwrord", password);

			return "redirect:/today";

		} else {

			return "login";

		}

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

	@RequestMapping("/bill")
	public String getBill(@RequestParam(value = "date", required = false) String theDate, Model theModel) {

		List<Bill> bills;
		int spendingTotal = 0;

		if (theDate == null) {

			theDate = LocalDate.now().toString();
			bills = userService.getAllBills();
			spendingTotal = userService.getSpendingTotal();

		} else {

			bills = userService.getBillsByDate(theDate);
			spendingTotal = userService.getSpendingTotalByDate(theDate);
		}

		int listSize = bills.size();
		int gainTotal = 0;

		Bill item;

		for (int i = 0; i < listSize; i++) {

			item = bills.get(i);

			gainTotal += item.getGain();
		}

		// صافي الربح
		int total = gainTotal - spendingTotal;

		theModel.addAttribute("date", theDate);
		theModel.addAttribute("total", total);
		theModel.addAttribute("items", bills);
		theModel.addAttribute("gainTotal", gainTotal);
		theModel.addAttribute("spendingTotal", spendingTotal);

		return "bill";
	}

	@RequestMapping("/add-bills-list")
	public String addBillsList(@RequestParam("itemId") List<String> itemIdList,
			@RequestParam("quantity") List<String> quantityList,
			@RequestParam("piecePrice") List<String> piecePriceList, @RequestParam("payed") List<String> payedList,
			@RequestParam("clientId") List<String> clientIdList) {

		int listSize = clientIdList.size();

		for (int i = 0; i < listSize; i++) {

			if (!itemIdList.get(i).equals("-1")) {

				Bill theBill = new Bill();
				ClientRecord clientRecord = new ClientRecord();
				Client theClient = new Client();

				theBill.setDate(LocalDate.now().toString());
				theBill.setQuantity(Integer.parseInt(quantityList.get(i)));
				theBill.setPiecePrice(Integer.parseInt(piecePriceList.get(i)));

				Warehouse warehouse = userService.getWarehouseById(Integer.parseInt(itemIdList.get(i)));
				warehouse.setQuantity(warehouse.getQuantity() - Integer.parseInt(quantityList.get(i)));
				userService.updateWarehouseQuantity(warehouse);

				int gain = (Integer.parseInt(piecePriceList.get(i)) - warehouse.getTradePrice())
						* Integer.parseInt(quantityList.get(i));

				theBill.setGain(gain);
				theBill.setItem(warehouse.getItem());
				theBill.settrader(warehouse.gettrader());
				theBill.setTradePrice(warehouse.getTradePrice());

				clientRecord.setItem(warehouse.getItem());
				clientRecord.setPay(Integer.parseInt(payedList.get(i)));

				clientRecord.setQuantity(Integer.parseInt(quantityList.get(i)));

				clientRecord.setPrice(Integer.parseInt(piecePriceList.get(i)) * Integer.parseInt(quantityList.get(i)));

				if (!clientIdList.get(i).equals("-1")) {

					theClient = userService.getClientById(Integer.parseInt(clientIdList.get(i)));

					int theNewdrawee = theClient.getDrawee() + clientRecord.getPrice() - clientRecord.getPay();

					theClient.setDrawee(theNewdrawee);

					userService.saveClient(theClient);

				} else {

					theClient = entityManager.getReference(Client.class, -1);

				}

				// theBill.setClient(theClient);
				// clientRecord.setClient(theClient);

				theBill.setClient(theClient);
				clientRecord.setClient(theClient);

				userService.addBill(theBill);
				userService.saveClientRecord(clientRecord);

			}
		}

		return "redirect:/today";
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

}
