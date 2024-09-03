package com.cams.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cams.stock.Order;
import com.cams.stock.Stock;
import com.cams.stock.User;
import com.cams.stock.controller.OrderRepository;
import com.cams.stock.controller.StockRepository;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private StockRepository stockRepository;

    @Transactional
    public Order createOrder(Order order) {
        // Add any business logic or validations here
    	
    	
        return orderRepository.save(order);
    }
    
    
    
    public List<Order> getAllOrders() {
        // Retrieve all orders from the database
        return orderRepository.findAll();
    }

	public void deleteOrder(Long orderId) {
		if (!orderRepository.existsById(orderId)) {
            throw new RuntimeException("Order with ID " + orderId + " does not exist");
        }
        
        // Delete the order
        orderRepository.deleteById(orderId);
		
	}
	 @Transactional
	    public void handleOrderChange(Order order) {
	        // Fetch the corresponding stock
		 	System.out.println("HELLO");
	        Optional<Stock> optionalStock = stockRepository.findById(order.getId());

	        // Check if the stock is present; if not, throw an exception
	        Stock stock = optionalStock.orElseThrow(() -> 
	            new IllegalArgumentException("Stock not found with stockId: " + order.getId()));

	        // Add the order to the stock's queue
	        stock.addOrder(order);

	       
	    }
	 

}