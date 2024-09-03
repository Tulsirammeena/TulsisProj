package com.cams.stock.controller;


import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cams.stock.Order;
import com.cams.stock.Stock;
import com.cams.stock.User;
import com.cams.stock.service.StockService;

import jakarta.servlet.http.HttpSession;


@Controller
public class StockController {

    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private StockService stockService;

    @PostMapping("/admin/stocks")
    public String saveStock(
        @RequestParam(value = "stockId", required = false) String stockId,
        @RequestParam(value = "currentDayPrice", required = false) Double currentDayPrice,
        @RequestParam(value = "previousDayPrice", required = false) Double previousDayPrice,
        Model model
    ) {
        Stock stock = new Stock();
        stock.setStockId(stockId);
        stock.setCurrentDayPrice(currentDayPrice);
        stock.setPreviousDayPrice(previousDayPrice);

        try {
            stockRepository.save(stock);
            model.addAttribute("success", "Stock saved successfully!");
        } catch (Exception e) {
            model.addAttribute("error", "Failed to save stock: " + e.getMessage());
            return "redirect:/index";  // Returning to the form page with an error
        }

        return "redirect:/admin1stpage";  // Redirect to avoid form resubmission
    }
    
   

    @GetMapping("/stocks")

    public String getStocks(Model model) {
    	
    	System.out.println("AD");
        model.addAttribute("stocks", stockService.getAllStocks());
        
        
        return "allstockdata"; // This should match the Thymeleaf template name
    }
   
    

   
}