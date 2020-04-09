package com.hcoder.clothingstoremanagement.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hcoder.clothingstoremanagement.entity.Incoming;
import com.hcoder.clothingstoremanagement.entity.Trader;
import com.hcoder.clothingstoremanagement.service.UserService;

@Controller
public class Incomings {

	@Autowired
	UserService userService;

	@RequestMapping("/incoming")
	public String getIncoming(@RequestParam(value = "date", required = false) String theDate, Model theModel,
			HttpServletRequest request) {

		List<Incoming> incomings;

		if (theDate == null) {

			theDate = LocalDate.now().toString();
			incomings = userService.getAllIncoming();

		} else {

			incomings = userService.GetIncomingsByDate(theDate);

		}

		int incomingTotal = userService.getIcomingTotal();
		int warehouseTotal = userService.getWarehouseTotal();

		int soldTotal = incomingTotal - warehouseTotal;

		theModel.addAttribute("tradersList", userService.getAllTraders());

		theModel.addAttribute("date", theDate);
		theModel.addAttribute("incomings", incomings);
		theModel.addAttribute("incoming", new Incoming());
		theModel.addAttribute("soldTotal", soldTotal);
		theModel.addAttribute("incomingTotal", incomingTotal);
		theModel.addAttribute("warehouseTotal", warehouseTotal);

		return "incoming";
	}

	@PostMapping("/add-incoming")
	public String addIncoming(@ModelAttribute("incoming") Incoming theIncoming, Model theModel) {

		theModel.addAttribute("incoming", new Incoming());

		theIncoming.setCurrentQuantity(theIncoming.getQuantity());

		int total = theIncoming.getTradePrice() * theIncoming.getQuantity();

		theIncoming.setDate(LocalDate.now().toString());

		theIncoming.setTotal(total);

		userService.AddIncoming(theIncoming);

		Trader trader = userService.getTraderByName(theIncoming.gettrader());

		trader.setRemaining(trader.getRemaining() + total);

		userService.saveTrader(trader);

		return "redirect:/incoming";

	}

	@RequestMapping("/delete-incoming")
	public String deleteIncoming(@RequestParam int id) {

		userService.deleteIncoming(id);
		return "redirect:/incoming";
	}

}
