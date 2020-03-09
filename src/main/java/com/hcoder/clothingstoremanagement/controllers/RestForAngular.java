package com.hcoder.clothingstoremanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcoder.clothingstoremanagement.entity.Client;
import com.hcoder.clothingstoremanagement.service.UserService;

@RestController
@RequestMapping("/service")
public class RestForAngular {

	@Autowired
	UserService userService;

	@GetMapping("/getClients")
	public List<Client> getClients(Model theModel) {

		List<Client> clients = userService.getAllClients();

		return clients;
	}

}
