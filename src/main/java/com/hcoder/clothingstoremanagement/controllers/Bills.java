package com.hcoder.clothingstoremanagement.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hcoder.clothingstoremanagement.entity.Bill;
import com.hcoder.clothingstoremanagement.entity.Client;
import com.hcoder.clothingstoremanagement.entity.Incoming;
import com.hcoder.clothingstoremanagement.service.UserService;

@Controller
public class Bills {

	@Autowired
	UserService userService;

	private EntityManager entityManager;

	@Autowired
	public Bills(EntityManager entityManager) {

		this.entityManager = entityManager;
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
				Client theClient = new Client();

				theBill.setDate(LocalDate.now().toString());
				theBill.setQuantity(Integer.parseInt(quantityList.get(i)));
				theBill.setPiecePrice(Integer.parseInt(piecePriceList.get(i)));

				// اتعامل بالانكاميج بدل الوايرهاوس
				Incoming incoming = userService.getIncomingById(Integer.parseInt(itemIdList.get(i)));
				incoming.setCurrentQuantity(incoming.getCurrentQuantity() - Integer.parseInt(quantityList.get(i)));
				
				userService.updateIncomingCurrentQuantity(incoming);

				int gain = (Integer.parseInt(piecePriceList.get(i)) - incoming.getTradePrice())
						* Integer.parseInt(quantityList.get(i));

				theBill.setGain(gain);
				theBill.setItem(incoming.getItem());
				theBill.settrader(incoming.gettrader());
				theBill.setTradePrice(incoming.getTradePrice());
				theBill.setPayed(Integer.parseInt(payedList.get(i)));

				if (!clientIdList.get(i).equals("-1")) {

					theClient = userService.getClientById(Integer.parseInt(clientIdList.get(i)));

					int theNewdrawee = theClient.getDrawee() + theBill.getPiecePrice() - theBill.getPayed();

					theClient.setDrawee(theNewdrawee);

					userService.saveClient(theClient);

				} else {

					theClient = entityManager.getReference(Client.class, -1);
				}

				theBill.setClient(theClient);

				userService.addBill(theBill);

			}
		}

		return "redirect:/today";
	}

	@RequestMapping("/delete-bill")
	public String deleteBill(@RequestParam int id) {

		userService.deleteBill(id);

		return "redirect:/today";
	}
}
