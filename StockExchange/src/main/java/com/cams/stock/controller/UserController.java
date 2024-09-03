package com.cams.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cams.stock.User;
import com.cams.stock.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public String signup(@RequestParam String firstName,
                         @RequestParam String lastName,
                         @RequestParam String pan,
                         @RequestParam String phone,
                         @RequestParam String email,
                         @RequestParam String password) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPan(pan);
        user.setPhone(phone);
        user.setEmail(email);
        user.setPassword(password);

        userService.saveUser(user);

        return "redirect:/login"; // Redirect to login page after successful signup
    }
}
