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

			bills = userService.getAllBills();
			spendingTotal = userService.getSpendingTotal();

		} else {

			bills = userService.getBillsByDate(theDate);
			spendingTotal = userService.getSpendingTotalByDate(theDate);
		}

		int listSize = bills.size();
		int gainTotal = 0;
		int soldPriceTotal = 0;

		Bill item;

		for (int i = 0; i < listSize; i++) {

			item = bills.get(i);
			soldPriceTotal += bills.get(i).getPiecePrice();
			gainTotal += item.getGain();
		}

		// صافي الربح
		int total = gainTotal - spendingTotal;

		if (theDate == null) {
			theDate = LocalDate.now().toString();
			int bank = soldPriceTotal - userService.getClientsDraweeTotal() - spendingTotal;
			theModel.addAttribute("bank", bank);
		}

		theModel.addAttribute("date", theDate);
		theModel.addAttribute("items", bills);
		theModel.addAttribute("total", total);
		theModel.addAttribute("gainTotal", gainTotal);
		theModel.addAttribute("spendingTotal", spendingTotal);

		return "bill";
	}

	@RequestMapping("/add-bills-list")
	public String addBillsList(@RequestParam("itemId") List<String> itemIdList,
			@RequestParam("quantity") List<String> quantityList,
			@RequestParam("piecePrice") List<String> piecePriceList, @RequestParam("payed") List<String> payedList,
			@RequestParam("clientId") List<String> clientIdList) throws Exception {

		boolean isSellOpertaionsCorrect = checkSellOpertaions(itemIdList, quantityList, piecePriceList);

		if (!isSellOpertaionsCorrect)
			throw new Exception("Bad Inputs Exception !!");

		int listSize = clientIdList.size();

		for (int i = 0; i < listSize; i++) {

			Incoming incoming = userService.getIncomingById(Integer.parseInt(itemIdList.get(i)));

			if (!itemIdList.get(i).equals("-1")) {

				// اتحديث كميات المخزن
				incoming.setCurrentQuantity(incoming.getCurrentQuantity() - Integer.parseInt(quantityList.get(i)));
				userService.updateIncomingCurrentQuantity(incoming);

				int gain = (Integer.parseInt(piecePriceList.get(i)) - incoming.getTradePrice())
						* Integer.parseInt(quantityList.get(i));

				// اضافة فاتورة جديدة
				Bill theBill = new Bill();

				theBill.setDate(LocalDate.now().toString());
				theBill.setQuantity(Integer.parseInt(quantityList.get(i)));
				theBill.setPiecePrice(Integer.parseInt(piecePriceList.get(i)));
				theBill.setIncomingId(incoming.getId());
				theBill.setGain(gain);
				theBill.setItem(incoming.getItem());
				theBill.settrader(incoming.gettrader());
				theBill.setTradePrice(incoming.getTradePrice());
				theBill.setPayed(Integer.parseInt(payedList.get(i)));

				Client theClient = new Client();

				if (!clientIdList.get(i).equals("-1")) {

					// تحديث ديون العملاء
					theClient = userService.getClientById(Integer.parseInt(clientIdList.get(i)));

					int billDrawee = theBill.getQuantity() * theBill.getPiecePrice() - theBill.getPayed();
					int theNewdrawee = theClient.getDrawee() + billDrawee;

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

	private boolean checkSellOpertaions(List<String> itemIdList, List<String> quantityList,
			List<String> piecePriceList) {

		// set payed and piecePrice to zero if not have been entered
		for (int i = 0; i < 3; i++) {

			if (!itemIdList.get(i).equals("-1")) {

				Incoming incoming = userService.getIncomingById(Integer.parseInt(itemIdList.get(i)));

				boolean isQuantityCorret = incoming.getCurrentQuantity() >= Integer.parseInt(quantityList.get(i));

				System.out.println(isQuantityCorret);

				if (!isQuantityCorret || !quantityList.get(i).matches("^[1-9]+$")
						|| !piecePriceList.get(i).matches("^[0-9]+$")) {

					return false;
				}
			}
		}

		return true;

	}

	@RequestMapping("/delete-bill")
	public String deleteBill(@RequestParam int id) {

		userService.deleteBill(id);

		return "redirect:/today";
	}

	@RequestMapping("/delete-bill-from-client-profile")
	public String deleteBillFromClientProfile(@RequestParam int id, @RequestParam("clientId") String clientId) {

		userService.deleteBill(id);

		return "redirect:/clientProfile?clientId=" + clientId;
	}

	@RequestMapping("/delete-bill-from-bills")
	public String deleteBillFromBills(@RequestParam int id) {

		userService.deleteBill(id);

		return "redirect:/bill";
	}
}
