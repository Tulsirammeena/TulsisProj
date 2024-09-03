package com.cams.stock;




import java.util.PriorityQueue;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;



@Entity
@Table(name = "STOCKS")
public class Stock {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "stock_id", nullable = false, unique = true)
    private String stockId;

    @Column(name = "current_day_price")
    private Double currentDayPrice;

    @Column(name = "previous_day_price")
    private Double previousDayPrice;


    // PriorityQueue for buy orders (max-heap by price)
    @Transient
    private PriorityQueue<Order> buyOrders = new PriorityQueue<>(OrderComparators.BUY_ORDER_COMPARATOR);

    // PriorityQueue for sell orders (min-heap by price)
    @Transient
    private PriorityQueue<Order> sellOrders = new PriorityQueue<>(OrderComparators.SELL_ORDER_COMPARATOR);

    // Method to add an order to the appropriate queue
    
    public void addOrder(Order order) {
        if ("BUY".equalsIgnoreCase(order.getOrderType())) {
        	
        	System.out.println("BUFFER");
            buyOrders.add(order);
            System.out.println("Size of buyOrders queue: " + buyOrders.size()+" "+stockId);
            
        } else if ("SELL".equalsIgnoreCase(order.getOrderType())) {
            sellOrders.add(order);
            System.out.println("Size of sellOrders queue: " + sellOrders.size()+" "+stockId);
        } else {
            throw new IllegalArgumentException("Invalid order type: " + order.getOrderType());
        }
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public Double getCurrentDayPrice() {
        return currentDayPrice;
    }

    public void setCurrentDayPrice(Double currentDayPrice) {
        this.currentDayPrice = currentDayPrice;
    }

 
    public Double getPreviousDayPrice() {
        return previousDayPrice;
    }

    public void setPreviousDayPrice(Double previousDayPrice) {
        this.previousDayPrice = previousDayPrice;
    }

    public PriorityQueue<Order> getBuyOrders() {
    	   if (buyOrders.isEmpty()) {
               System.out.println("No Buy Orders.");
           } else {
               System.out.println("Buy Orders:");
               for (Order order : buyOrders) {
                   System.out.println(order);
               }
           }

        return buyOrders != null ? buyOrders : new PriorityQueue<>();
    }

    // Getter for sellOrders
    public PriorityQueue<Order> getSellOrders() {
    	
    	  if (sellOrders.isEmpty()) {
              System.out.println("No Sell Orders.");
          } else {
              System.out.println("Sell Orders:");
              for (Order order : sellOrders) {
                  System.out.println(order);
              }
          }
       return sellOrders != null ? sellOrders : new PriorityQueue<>();
    }

   
}
