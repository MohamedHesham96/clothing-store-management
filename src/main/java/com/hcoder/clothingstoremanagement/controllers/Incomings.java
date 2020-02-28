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

import com.hcoder.clothingstoremanagement.entity.Incoming;
import com.hcoder.clothingstoremanagement.entity.Trader;
import com.hcoder.clothingstoremanagement.entity.Warehouse;
import com.hcoder.clothingstoremanagement.service.UserService;

@Controller
public class Incomings {

	@Autowired
	UserService userService;

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

		theModel.addAttribute("tradersList", userService.getAllTraders());

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

		int total = theIncoming.getTradePrice() * theIncoming.getQuantity();

		theIncoming.setDate(LocalDate.now().toString());
		theIncoming.setTotal(total);

		userService.AddIncoming(theIncoming);
		Warehouse warehouse = new Warehouse();

		warehouse.setItem(theIncoming.getItem());
		warehouse.setQuantity(theIncoming.getQuantity());
		warehouse.setTradePrice(theIncoming.getTradePrice());
		warehouse.settrader(theIncoming.gettrader());

		Trader trader = userService.getTraderByName(theIncoming.gettrader());

		trader.setRemaining(trader.getRemaining() + total);

		userService.saveTrader(trader);

		userService.addToWarehouse(warehouse);

		return "redirect:/incoming";

	}

}