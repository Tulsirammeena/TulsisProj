package com.cams.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cams.stock.User;
import com.cams.stock.service.UserService;



import org.springframework.ui.Model;


@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        User user = userService.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            // Redirect to dashboard if login is successful
            return "redirect:/userDashboard";
        } else {
            // Add an error message to the model if login fails
            model.addAttribute("error", "Invalid email or password.");
            return "login"; // Return to login page with error
        }
    }
}