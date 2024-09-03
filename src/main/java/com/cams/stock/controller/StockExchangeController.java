package com.cams.stock.controller;
 
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
 
 
@Controller
@RequestMapping("/")
public class StockExchangeController {
	
    @GetMapping("/index")
    public String index() {
        return "index"; // This corresponds to 
    }
   
    @GetMapping("/login")
    public String login() {
        return "login"; // This corresponds to 
    }
    @GetMapping("/signup")
    public String signup() {
        return "signup"; // This corresponds to 
    }
    @GetMapping("/adminDashboard")
    public String adminDashboard() {
        return "adminDashboard"; // This corresponds to 
    }
//    @GetMapping("/admin1stpage")
//    public String admin1stpage() {
//        return "admin1stpage"; // This corresponds to 
//    }
    @GetMapping("/allstockdata")
    public String allstockdata() {
        return "allstockdata"; // This corresponds to 
    }
    @GetMapping("/transaction")
    public String transaction() {
        return "transaction"; // This corresponds to 
    }
   
}
 
 