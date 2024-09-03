package com.cams.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cams.stock.User;
import com.cams.stock.service.StockService;
import com.cams.stock.service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;
@Controller
public class LoginController {

    @Autowired
    private UserService userService; // Ensure UserService is properly injected
    @Autowired
    private StockService stockService;

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        User user = userService.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("loggedInUser", user); // Store user in session

            // Redirect based on user role
            if ("ADMIN".equals(user.getRole())) {
                return "redirect:/admin1stpage";
            } else {
                return "redirect:/userDashboard";
            }
        } else {
            model.addAttribute("error", "Invalid email or password.");
            return "login"; // Return to login page if credentials are invalid
        }
    }

    @GetMapping("/userDashboard")
    public String userDashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");

        if (user != null && !"ADMIN".equals(user.getRole())) {
            model.addAttribute("user", user);
            return "userDashboard"; // Return the view name for the user dashboard
        } else {
            return "redirect:/login"; // Redirect to login if no user is in session or user is an admin
        }
    }

    @GetMapping("/admin1stpage")
    public String admin1stpage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");

        if (user != null && "ADMIN".equals(user.getRole())) {
            model.addAttribute("user", user);
            return "admin1stpage"; // Return the view name for the admin page
        } else {
            return "redirect:/login"; // Redirect to login if no user is in session or user is not an admin
        }
    }

    
    @GetMapping("/Stocki")

    public String GetStocks(HttpSession session,Model model) {
    	
    	
        model.addAttribute("Stocki", stockService.getAllStocks());
        User user = (User) session.getAttribute("loggedInUser");
        System.out.println(user);
        if (user != null && !"ADMIN".equals(user.getRole())) {
            model.addAttribute("user", user);
            return "buysell"; // Return the view name for the user dashboard
        } else {
            return "redirect:/login"; // Redirect to login if no user is in session or user is an admin
        }
        
    }
   

   
    @GetMapping("/UserOrder")
    public String getUserOrders(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user != null) {
            User userWithOrders = userService.getUserWithOrders(user.getUserId());
            model.addAttribute("user", userWithOrders);
            return "UserOrder"; // Return the view name for user orders
        } else {
            return "redirect:/login"; // Redirect to login if no user is in session
        }
    }
    

    
}