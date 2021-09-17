package com.hcoder.clothingstoremanagement.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hcoder.clothingstoremanagement.entity.Bill;
import com.hcoder.clothingstoremanagement.entity.Incoming;
import com.hcoder.clothingstoremanagement.service.UserService;

@Controller
public class Navigator {

    @Autowired
    UserService userService;

    @Autowired
    private HttpSession httpSession;

    @RequestMapping("/")
    public String userLogin() {

        return "login";
    }

    @RequestMapping("/logout")
    public String userLogout() {

        httpSession.removeAttribute("username");

        return "login";

    }

    @RequestMapping("/login")
    public String userLogin(@RequestParam("username") String username, @RequestParam("password") String password) {

        if (username.equals("mazen") && password.equals("mazen")) {

            httpSession.setAttribute("username", username);

            httpSession.setAttribute("passwrord", password);

            return "redirect:/today";

        } else {

            return "login";

        }

    }

    @RequestMapping("/warehouse")
    public String getWarehouse(Model theModel) {

        List<Incoming> availableIncomings = userService.getAllAvailableIncoming();

        int soldTotal = userService.getIcomingTotal() - userService.getWarehouseTotal();

        theModel.addAttribute("availableIncomings", availableIncomings);
        theModel.addAttribute("soldTotal", soldTotal);
        theModel.addAttribute("incomingTotal", userService.getIcomingTotal());
        theModel.addAttribute("warehouseTotal", userService.getWarehouseTotal());

        return "warehouse";
    }

    @RequestMapping("/search-warehouse")
    public String getIncomingsByItemName(@RequestParam String itemName, Model theModel) {

        int soldTotal = userService.getIcomingTotal() - userService.getWarehouseTotal();

        theModel.addAttribute("availableIncomings", userService.getIncomingsByItemName(itemName));
        theModel.addAttribute("soldTotal", soldTotal);
        theModel.addAttribute("incomingTotal", userService.getIcomingTotal());
        theModel.addAttribute("warehouseTotal", userService.getWarehouseTotal());

        return "warehouse";
    }

    @RequestMapping("/today")
    public String getToday(Model theModel) {

        List<Bill> bills;
        String theDate = LocalDate.now().toString();

        bills = userService.getBillsByDate(theDate);

        int listSize = bills.size();
        int gainTotal = 0;

        Bill item;

        for (int i = 0; i < listSize; i++) {

            item = bills.get(i);

            gainTotal += item.getGain();
        }

        int spendingTotal = userService.getSpendingTotalToday();

        List<Incoming> warehouseItems = userService.getAllAvailableIncoming();

        // صافي الربح
        int total = gainTotal - spendingTotal;

        theModel.addAttribute("total", total);
        theModel.addAttribute("items", bills);
        theModel.addAttribute("gainTotal", gainTotal);
        theModel.addAttribute("spendingTotal", spendingTotal);
        theModel.addAttribute("warehouseItems", warehouseItems);
        theModel.addAttribute("clientsList", userService.getAllClients());

        return "today";
    }

    @RequestMapping("/settings")
    public String settings() {
        return "settings";
    }

    @RequestMapping("/deleteAllSystem")
    public String deleteAllSystem() {
        userService.deleteAllSystem();
        return "today";
    }
}
