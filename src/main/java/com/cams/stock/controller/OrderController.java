package com.cams.stock.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cams.stock.Order;
import com.cams.stock.Stock;
import com.cams.stock.User;
import com.cams.stock.service.OrderService;

import ch.qos.logback.core.model.Model;

@Controller
@RequestMapping
public class OrderController {

    @Autowired
    private OrderService orderService;
    
    @PostMapping("/buysell")
    public String createOrder(
    		@RequestParam Long id,
            @RequestParam String stockname,
            @RequestParam Long userId,
            @RequestParam String orderType,
            @RequestParam Integer quantity,
             // Assuming the user ID is passed for associating the order with a user
            RedirectAttributes redirectAttributes) {
    	System.out.println("HELLO");
        // Fetch or create User object based on userId (you might need UserService for this)
        User user = new User();
        user.setUserId(userId); // Assuming User entity has a userId field
        
        Stock stock=new Stock();
        stock.setId(id);
        
        
        Order order = new Order();
        
       
        order.setOrderType(orderType);
        order.setQuantity(quantity);
        order.setUser(user); // Set the user for the order
        order.setStock(stock); // Set the user for the order
        order.setStockname(stockname);
        
        
        orderService.createOrder(order);

        redirectAttributes.addFlashAttribute("message", "Order created successfully!");
        return "redirect:/Stocki";// Redirect to the list or any other page after creating the order
    }
    
    @GetMapping("/list")
    public String listOrders(Model model) {
        // Fetch list of orders
        List<Order> orders = orderService.getAllOrders();
        ((RedirectAttributes) model).addAttribute("orders", orders);
        return "orders/list"; // Return view name for displaying the list of orders
    }

    @DeleteMapping("/{orderId}")
    public String deleteOrder(@PathVariable Long orderId, RedirectAttributes redirectAttributes) {
        try {
            orderService.deleteOrder(orderId);
            redirectAttributes.addFlashAttribute("message", "Order deleted successfully");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/orders";
    }
   
}