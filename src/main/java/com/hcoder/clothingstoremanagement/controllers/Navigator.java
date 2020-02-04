package com.hcoder.clothingstoremanagement.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hcoder.clothingstoremanagement.entity.Incoming;

@Controller
public class Navigator {

	@RequestMapping("/home")
	public String goHome() {

		return "home";
	}

	@RequestMapping("/incoming")
	public String showLastestTen(Model theModel) {

		List<Incoming> items = new ArrayList<>();

		Incoming incoming = new Incoming(5, 100, "قميص سادة", "ميدو البرنس", 500, 90);
		Incoming incoming2 = new Incoming(5, 100, "قميص سادة", "ميدو البرنس", 500, 90);
		Incoming incoming3 = new Incoming(5, 100, "قميص سادة", "ميدو البرنس", 500, 90);

		items.add(incoming);
		items.add(incoming2);
		items.add(incoming3);

		theModel.addAttribute("items", items);

		return "incoming";
	}

}
