package com.cams.stock.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cams.stock.Portfolio;
import com.cams.stock.Stock;
import com.cams.stock.User;
import com.cams.stock.service.PortfolioService;
import com.cams.stock.service.StockService;
import com.cams.stock.service.UserService;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @PutMapping("/upsert")
    public void upsertPortfolio(@RequestParam Long userId,
                                @RequestParam Long stockId,
                                @RequestParam Integer quantity,
                                @RequestParam BigDecimal initialValue,
                                @RequestParam BigDecimal currentValue) {
        portfolioService.upsertPortfolio(userId, stockId, quantity, initialValue, currentValue, null, quantity);
    }
    


    @GetMapping("/portfolioupdate")
    public String updatePortfolioValues() {
        portfolioService.updatePortfolioCurrentValues();
        return "Portfolio values updated";
    }
}