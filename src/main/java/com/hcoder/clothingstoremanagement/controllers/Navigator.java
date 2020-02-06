package com.hcoder.clothingstoremanagement.controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hcoder.clothingstoremanagement.entity.Bill;
import com.hcoder.clothingstoremanagement.entity.Client;
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
	public String getIncoming(Model theModel) {

		theModel.addAttribute("incoming", new Incoming());

		List<Incoming> items = userService.GetAllIncoming();

		int incomingTotal = userService.getIcomingTotal();
		int warehouseTotal = userService.getWarehouseTotal();

		int soldTotal = incomingTotal - warehouseTotal;

		theModel.addAttribute("items", items);
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

		int incomingTotal = userService.getIcomingTotal();
		int warehouseTotal = userService.getWarehouseTotal();

		int soldTotal = incomingTotal - warehouseTotal;

		theModel.addAttribute("items", items);
		theModel.addAttribute("bill", new Bill());
		theModel.addAttribute("soldTotal", soldTotal);
		theModel.addAttribute("incomingTotal", incomingTotal);
		theModel.addAttribute("warehouseTotal", warehouseTotal);

		return "warehouse";
	}

	@PostMapping("/add-bill")
	public String addBill(@ModelAttribute("bill") Bill theBill) {

		Bill bill = new Bill();

		bill.setDate(LocalDate.now().toString());
		bill.setQuantity(theBill.getQuantity());
		bill.setPiecePrice(theBill.getPiecePrice());

		// Use the Bill ID from the FORM DATA

		Warehouse warehouse = userService.getWarehouseById(theBill.getId());

		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>" + warehouse.getId());
		// Take from Warehouse the items that sell
		warehouse.setQuantity(warehouse.getQuantity() - bill.getQuantity());

		userService.updateWarehouseQuantity(warehouse);

		int gain = (theBill.getPiecePrice() - warehouse.getTradePrice()) * bill.getQuantity();
		bill.setGain(gain);

		bill.setItem(warehouse.getItem());
		bill.setStore(warehouse.getStore());
		bill.setTradePrice(warehouse.getTradePrice());

		userService.addBill(bill);

		return "redirect:/warehouse";

	}

	@RequestMapping("/bill")
	public String getBill(Model theModel) {

		int spendingTotal = userService.getSpendingTotal();

		List<Bill> items = userService.getAllBills();
		int listSize = items.size();
		int gainTotal = 0;

		for (int i = 0; i < listSize; i++) {
			Bill item = items.get(i);

			gainTotal += item.getGain();
		}

		int total = gainTotal - spendingTotal;

		theModel.addAttribute("items", items);
		theModel.addAttribute("total", total);
		theModel.addAttribute("spendingTotal", spendingTotal);
		theModel.addAttribute("gainTotal", gainTotal);

		return "bill";
	}

	@RequestMapping("/spending")
	public String getSpending(Model theModel) {

		List<Spending> spendings = userService.getAllSpending();

		theModel.addAttribute("spendings", spendings);
		theModel.addAttribute("spendingTotal", userService.getSpendingTotal());

		return "spending";
	}

	@RequestMapping("/clients")
	public String getClients(Model theModel) {

		List<Client> clients = userService.getAllClients();

		theModel.addAttribute("clientsList", clients);
		theModel.addAttribute("draweeTotal", userService.getClientsDraweeTotal());

		return "clients";
	}
	
	@RequestMapping("/clientProfile")
	public String goToClientAccount(@ModelAttribute("clientId") int id,  Model theModel) {

		Client client = userService.getClientById(id);

		theModel.addAttribute("clientData", client);

		return "client-profile";
	}

}
