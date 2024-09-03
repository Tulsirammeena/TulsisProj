package com.cams.stock;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;



@Entity
@Table(name = "Order_Table")
@EntityListeners(OrderListener.class)
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
    
    
    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private Stock stock; // Stock identifier
    
    private String stockname;
    
    public String getStockname() {
		return stockname;
	}

	public void setStockname(String stockname) {
		this.stockname = stockname;
	}

	private String orderType;  // "BUY" or "SELL"
    private Integer quantity;
    
    
    

    // Getters and setters
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
    	System.out.println(orderType);
        this.orderType = orderType;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    
    public void assignToUser(User user) {
        if (user != null) {
            this.user = user;
            user.addOrder(this); // Update the user's order list
        }
    }
    
  
    @Override
    public String toString() {
        return "Order{" +
                "orderType='" + orderType + '\''  +
                ", quantity=" + quantity +
                '}';
    }

	public Long getId() {
		if (stock != null) {
            return stock.getId();
        }  
		return null;
	}
}