package com.hcoder.clothingstoremanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hcoder.clothingstoremanagement.entity.Bill;
import com.hcoder.clothingstoremanagement.entity.Client;
import com.hcoder.clothingstoremanagement.service.UserService;

@Controller
public class Clients {

	@Autowired
	UserService userService;

	@RequestMapping("/clients")
	public String getClients(Model theModel) {

		List<Client> clients = userService.getAllClients();

		theModel.addAttribute("clientsList", clients);

		theModel.addAttribute("theClient", new Client());

		theModel.addAttribute("draweeTotal", userService.getClientsDraweeTotal());

		return "clients";
	}

	@RequestMapping("/search-clients")
	public String getClients(@RequestParam String clientName, Model theModel) {

		theModel.addAttribute("clientsList", userService.getClientByName(clientName));

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
		List<Bill> clientBills = client.getBills();

		int size = clientBills.size();
		int totalPayment = 0;

		for (int i = 0; i < size; i++) {

			totalPayment += clientBills.get(i).getPiecePrice() * clientBills.get(i).getQuantity();
		}

		theModel.addAttribute("clientData", client);
		theModel.addAttribute("totalPayment", totalPayment);

		return "client-profile";
	}

	@RequestMapping("/delete-client")
	public String deleteClient(@RequestParam int id) {

		userService.deleteClient(id);

		return "redirect:/clients";
	}

}
