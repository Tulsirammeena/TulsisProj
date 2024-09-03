package com.cams.stock;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

//    @ManyToOne
//    @JoinColumn(name = "userId", nullable = false)
//    private User buyer;
//
//    @ManyToOne
//    @JoinColumn(name = "userId", nullable = false)
//    private User seller;

    @ManyToOne
    @JoinColumn(name = "orderId", nullable = false)
    private Order order; // Reference to Order entity

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private Stock stock; // Reference to Stock entity
    
    private Integer quantity;
    private Integer price;

//    @Column(name = "timestamp", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
//    private LocalDateTime timestamp;

    // Getters and setters
    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

//    public User getBuyer() {
//        return buyer;
//    }
//
//    public void setBuyer(User buyer) {
//        this.buyer = buyer;
//    }
//
//    public User getSeller() {
//        return seller;
//    }
//
//    public void setSeller(User seller) {
//        this.seller = seller;
//    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Stock getStockId() {
        return stock;
        //
    }

    public void setStockId(Stock stock) {
        this.stock = stock;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

//    public LocalDateTime getTimestamp() {
//        return timestamp;
//    }
//
//    public void setTimestamp(LocalDateTime timestamp) {
//        this.timestamp = timestamp;
//    }
}
