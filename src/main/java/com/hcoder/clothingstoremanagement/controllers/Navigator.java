package com.hcoder.clothingstoremanagement.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hcoder.clothingstoremanagement.entity.Incoming;
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

		List<Incoming> items = userService.GetAllIncoming();

		theModel.addAttribute("items", items);

		return "incoming";

		// Incoming incoming = new Incoming(5, 100, "قميص سادة", "ميدو البرنس", 500,
		// 90);
		// Incoming incoming2 = new Incoming(5, 100, "قميص سادة", "ميدو البرنس", 500,
		// 90);
		// Incoming incoming3 = new Incoming(5, 100, "قميص سادة", "ميدو البرنس", 500,
		// 90);

		// items.add(incoming);
		// items.add(incoming2);
		// items.add(incoming3);
	}

}
