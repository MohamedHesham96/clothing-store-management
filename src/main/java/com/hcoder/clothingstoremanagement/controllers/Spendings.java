package com.hcoder.clothingstoremanagement.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hcoder.clothingstoremanagement.entity.Client;
import com.hcoder.clothingstoremanagement.entity.ClientRecord;
import com.hcoder.clothingstoremanagement.entity.Spending;
import com.hcoder.clothingstoremanagement.service.UserService;

@Controller
public class Spendings {

	@Autowired
	UserService userService;

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

	@RequestMapping("/spending")
	public String getSpendings(@RequestParam(value = "date", required = false) String theDate, Model theModel) {

		List<Spending> spendings;

		if (theDate == null) {

			theDate = LocalDate.now().toString();
			spendings = userService.getAllSpending();
			theModel.addAttribute("spendingTotal", userService.getSpendingTotal());

		} else {

			spendings = userService.getSpendingsByDate(theDate);
			theModel.addAttribute("spendingTotal", userService.getSpendingTotalByDate(theDate));

		}

		theModel.addAttribute("date", theDate);
		theModel.addAttribute("spending", new Spending());
		theModel.addAttribute("spendings", spendings);

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

	@RequestMapping("/delete-spending")
	public String deleteSpending(@PathParam("id") int id) {

		userService.deleteSpending(id);

		return "redirect:/spending";

	}

}
