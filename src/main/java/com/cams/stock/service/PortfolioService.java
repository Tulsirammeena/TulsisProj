package com.cams.stock.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cams.stock.Portfolio;
import com.cams.stock.Stock;
import com.cams.stock.User;

import jakarta.transaction.Transactional;


/// BUY SELL CASE BACHA HAI
@Service
public class PortfolioService {
	
	 @Autowired
	    private PortfolioRepository portfolioRepository;

	 @Transactional
	 public void upsertPortfolio(Long userId, Long id, Integer quantity, BigDecimal initialValue, BigDecimal currentValue, String ordertype, Integer shareprice) {
	     // Find the existing portfolio entry or create a new instance
	     Portfolio portfolio = portfolioRepository.findByUserIdAndStockId(userId, id)
	             .orElse(new Portfolio());

	     // Set up user and stock details
	     User user = new User();
	     user.setUserId(userId); // Assuming User entity has a userId field

	     Stock stock = new Stock();
	     stock.setId(id);

	     // Set user and stock relationships
	     portfolio.setUser(user);
	     portfolio.setStock(stock);

	     // Check if the portfolio entry is new or existing

	     // Check if the portfolio entry is new or existing
	     if (portfolio.getPortfolioId() == null) {
	         // New portfolio entry: Set all the attributes
	         portfolio.setOrdertype(ordertype);
	         portfolio.setQuantity(quantity);
	         portfolio.setInitialValue(BigDecimal.valueOf(quantity).multiply(BigDecimal.valueOf(shareprice))); // Initial value is quantity * shareprice
	         portfolio.setCurrentValue(BigDecimal.valueOf(quantity).multiply(BigDecimal.valueOf(shareprice))); // Current value is quantity * shareprice
	         portfolio.setShareprice(shareprice);
	     } else {
	         // Existing portfolio entry: Update based on ordertype
	         if ("buy".equalsIgnoreCase(ordertype)) {
	             // Increase quantity and update values for a buy order
	             portfolio.setQuantity(portfolio.getQuantity() + quantity);
	             portfolio.setInitialValue(portfolio.getInitialValue().add(BigDecimal.valueOf(quantity).multiply(BigDecimal.valueOf(shareprice))));
	             portfolio.setCurrentValue(BigDecimal.valueOf(portfolio.getQuantity()).multiply(BigDecimal.valueOf(shareprice)));
	         } else if ("sell".equalsIgnoreCase(ordertype)) {
	             // Update quantity and values for a sell order
	             if (portfolio.getQuantity() >= quantity) {
	                 // Calculate the average cost per share
	                 BigDecimal averageCostPerShare = portfolio.getInitialValue().divide(BigDecimal.valueOf(portfolio.getQuantity()), BigDecimal.ROUND_HALF_UP);
	                 
	                 // Update portfolio attributes for a sell operation
	                 portfolio.setQuantity(portfolio.getQuantity() - quantity);
	                 portfolio.setInitialValue(portfolio.getInitialValue().subtract(averageCostPerShare.multiply(BigDecimal.valueOf(quantity))));
	                 portfolio.setCurrentValue(BigDecimal.valueOf(portfolio.getQuantity()).multiply(BigDecimal.valueOf(shareprice)));
	             } else {
	                 throw new IllegalArgumentException("Cannot sell more shares than owned.");
	             }
	         } else {
	             throw new IllegalArgumentException("Invalid order type.");
	         }
	     } 
	     
	     

	     // Save the portfolio, either updating existing or inserting new
	     portfolioRepository.save(portfolio);
	 }


	    @Transactional
	    public void updatePortfolioCurrentValues() {
	        List<Portfolio> portfolios = portfolioRepository.findAll();

	        for (Portfolio portfolio : portfolios) {
	            Stock stock = portfolio.getStock();
	            if (stock != null) {
	                Double sharePrice = stock.getCurrentDayPrice();
	                if (sharePrice != null) {
	                    BigDecimal currentValue = BigDecimal.valueOf(sharePrice)
	                                    .multiply(BigDecimal.valueOf(portfolio.getQuantity()));
	                    portfolio.setCurrentValue(currentValue);
	                    portfolioRepository.save(portfolio);
	                }
	            }
	        }
	    }
}
