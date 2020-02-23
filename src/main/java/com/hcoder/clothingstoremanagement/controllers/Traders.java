package com.hcoder.clothingstoremanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hcoder.clothingstoremanagement.entity.Incoming;
import com.hcoder.clothingstoremanagement.entity.Trader;
import com.hcoder.clothingstoremanagement.service.UserService;

@Controller
public class Traders {

	@Autowired
	UserService userService;

	@RequestMapping("/traders")
	public String getTraders(@RequestParam(name = "remainingBool", required = false) Boolean remainingBool,
			Model theModel) {

		List<Trader> traders = null;

		if (remainingBool == null) {

			traders = userService.getAllTraders();
		}

		else if (remainingBool == true) {

			traders = userService.getAllTradersWithRemaining();

		} else {

			traders = userService.getAllTradersWithoutRemaining();
		}

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

	@RequestMapping("/traderProfile")
	public String geTrader(@ModelAttribute("traderId") int id, Model theModel) {

		Trader trader = userService.getTraderById(id);

		List<Incoming> traderIncomings = userService.getIncomingsByTraderName(trader.getName());
		int incomingsTotal = userService.getIncomingTotalByTraderName(trader.getName());

		theModel.addAttribute("traderData", trader);

		theModel.addAttribute("traderIncomings", traderIncomings);

		theModel.addAttribute("incomingsTotal", incomingsTotal);

		theModel.addAttribute("remainingTotal", trader.getRemaining());

		return "trader-profile";
	}

	@RequestMapping("/pay-off-amount-for-trader")
	public String payOffAmountForTrader(@RequestParam(name = "moneyAmount") int theAmount,
			@ModelAttribute("traderData") Trader traderData, Model theModel) {

		Trader trader = userService.getTraderById(traderData.getId());
		List<Incoming> traderIncomings = userService.getIncomingsByTraderName(trader.getName());

		int incomingsTotal = userService.getIncomingTotalByTraderName(trader.getName());

		
		trader.setPayed(trader.getPayed() + theAmount);
		trader.setRemaining(incomingsTotal - trader.getPayed());

		userService.saveTrader(trader);

		theModel.addAttribute("traderData", trader);

		theModel.addAttribute("traderIncomings", traderIncomings);

		theModel.addAttribute("incomingsTotal", incomingsTotal);

		theModel.addAttribute("remainingTotal", trader.getRemaining());

		return "trader-profile";
	}


}
