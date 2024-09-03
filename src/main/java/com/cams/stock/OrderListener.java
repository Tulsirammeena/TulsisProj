package com.cams.stock;



import jakarta.persistence.PostPersist;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cams.stock.controller.StockRepository;


@Service
public class OrderListener {

//
//	   @Autowired
//	    private StockRepository stockRepository; // Ensure this is properly injected
//	 
//	 
//
//	    @PostPersist
//	    public void afterOrderPersisted(Order order) {
//	        // Fetch the stockId from the order
//	        String stockname = order.getStockname(); // Assuming getStockId() returns Long
//
//	        if (stockname != null) {
//	        	System.out.println(stockname);
//	        	System.out.println(order);
//	            // Retrieve the Stock entity by stockId (this returns an Optional<Stock>)
//	            Optional<Stock> stockOptional = stockRepository.findByStockId(stockname);
//
//	            // Check if the Optional contains a value
//	            if (stockOptional.isPresent()) {
//	                // Get the Stock from the Optional
//	                Stock stock = stockOptional.get();
//	                
//	                // Add the order to the Stock's queue
//	                stock.addOrder(order);
//
//	                // Save the updated Stock entity back to the database
//	                stockRepository.save(stock);
//	            } else {
//	                // Handle the case where the stock is not found (e.g., log an error or take alternative action)
//	                System.err.println("Stock with ID " + stockname + " not found.");
//	            }
//	        } else {
//	            // Handle the case where stockId is null
//	            System.err.println("Order does not have a valid stockId.");
//	        }
//	    }
}
