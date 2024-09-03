package com.cams.stock.controller;

import java.util.List;
import java.util.PriorityQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cams.stock.Order;
import com.cams.stock.Stock;
import com.cams.stock.service.TransactionService;

@Controller
public class TransactionController {
	
	
	private final StockRepository stockRepository;
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService,StockRepository stockRepository) {
        this.transactionService = transactionService;
        this.stockRepository = stockRepository;
    }

    @GetMapping("/compute")
    public String listTransactions(Model model) {
        model.addAttribute("transactions", transactionService.getAllTransactions());
        return "transaction"; // This corresponds to the HTML template name
    }
    
    
   

    // Method to print queue data for all stocks
    @GetMapping("/Process")
    public String process() {
        List<Stock> stocks = stockRepository.findAll();
        System.out.println("Total stocks: " + stocks.size());

        for (Stock stock : stocks) {
            System.out.println("Stock ID: " + stock.getStockId());

            PriorityQueue<Order> buyOrders = stock.getBuyOrders();
            if (buyOrders.isEmpty()) {
                System.out.println("No Buy Orders.");
            } else {
                System.out.println("Buy Orders:");
                for (Order order : buyOrders) {
                    System.out.println(order);
                }
            }

            PriorityQueue<Order> sellOrders = stock.getSellOrders();
            if (sellOrders.isEmpty()) {
                System.out.println("No Sell Orders.");
            } else {
                System.out.println("Sell Orders:");
                for (Order order : sellOrders) {
                    System.out.println(order);
                }
            }

            System.out.println(); // Print a newline for better readability
        }
        return "transaction";
    }
  
}

