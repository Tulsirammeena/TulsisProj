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
    @GetMapping("/userDashboard")
    public String userDashboard() {
        return "userDashboard"; // This corresponds to 
    }
    @GetMapping("/login")
    public String login() {
        return "login"; // This corresponds to 
    }
    @GetMapping("/signup")
    public String signup() {
        return "signup"; // This corresponds to 
    }
}
 
 