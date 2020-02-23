package com.hcoder.clothingstoremanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hcoder.clothingstoremanagement.entity.Result;
import com.hcoder.clothingstoremanagement.service.UserService;

@Controller
public class Summry {

	@Autowired
	UserService userService;

	@RequestMapping("/months")
	public String groupByMonth(Model theModel) {

		List<Result> monthsList = userService.groupByMonths();

		theModel.addAttribute("monthsList", monthsList);

		return "months";
	}

}
