package com.cams.stock.service;

import com.cams.stock.Stock;
import com.cams.stock.controller.StockRepository;

import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public Stock save(Stock stock) {
        Stock savedStock = stockRepository.save(stock);
        System.out.println("Saved Stock: " + savedStock);
        return savedStock;
    }
    
    public List<Stock> getAllStocks() {
        List<Stock> stocks = stockRepository.findAll();
        System.out.println("All Stocks:");
        
        for (Stock stock : stocks) {
            System.out.println(stock);
        }
        return stocks;
    }
    public List<Stock> GetAllStocks() {
        List<Stock> stocks = stockRepository.findAll();
        System.out.println("All Stocks:");
        
        for (Stock stock : stocks) {
            System.out.println(stock);
        }
        return stocks;
    }
    
}