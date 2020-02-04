package com.hcoder.clothingstoremanagement.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Navigator {
	
	@RequestMapping("/home")
	public String goHome() {
		
		
		return "home";
	}
	
}
