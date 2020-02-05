package com.hcoder.clothingstoremanagement.controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hcoder.clothingstoremanagement.entity.Bill;
import com.hcoder.clothingstoremanagement.entity.Incoming;
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

		int listSize = items.size();
		for (int i = 0; i < listSize; i++) {
			Incoming item = items.get(i);
			item.setTotal(item.getTradePrice() * item.getQuantity());

		}
		theModel.addAttribute("items", items);

		return "incoming";
	}

	@PostMapping("/add-incoming")
	public String addIncoming(@ModelAttribute("incoming") Incoming theIncoming, Model theModel) {

		theModel.addAttribute("incoming", new Incoming());

		theIncoming.setDate(LocalDate.now().toString());

		userService.AddIncoming(theIncoming);
		Warehouse warehouse = new Warehouse();

		warehouse.setItem(theIncoming.getItem());
		warehouse.setQuantity(theIncoming.getQuantity());
		warehouse.setTradePrice(theIncoming.getTradePrice());

		userService.addToWarehouse(warehouse);

		return "redirect:/incoming";

	}

	@RequestMapping("/warehouse")
	public String getWarehouse(Model theModel) {

		List<Warehouse> items = userService.getAllWarehouse();

		theModel.addAttribute("items", items);

		theModel.addAttribute("bill", new Bill());

		return "warehouse";
	}

	@PostMapping("/add-bill")
	public String addBill(@ModelAttribute("bill") Bill theBill) {

		Bill bill = new Bill();

		bill.setItem(theBill.getItem());
		bill.setDate(LocalDate.now().toString());
		bill.setQuantity(theBill.getQuantity());
		bill.setPiecePrice(theBill.getPiecePrice());
		bill.setStore("سيبها على الله");
		bill.setTradePrice(100);
		bill.setGain(10);
		
		userService.addBill(bill);
		
		return "redirect:/warehouse";

	}

}
