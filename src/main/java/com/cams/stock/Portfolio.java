package com.cams.stock;
 
import java.math.BigDecimal;
 
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
 
 
@Entity
@Table(name = "PORTFOLIOS")
public class Portfolio {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long portfolioId;
 
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
 
    @ManyToOne
    @JoinColumn(name = "id")
    private Stock stock;
 
    private Integer quantity;
    private BigDecimal initialValue;
    private BigDecimal currentValue;
    
    private String ordertype;
    private Integer shareprice;
    
    
	public Integer getShareprice() {
		return shareprice;
	}
	public void setShareprice(Integer shareprice) {
		this.shareprice = shareprice;
	}
	public String getOrdertype() {
		return ordertype;
	}
	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}
	public Portfolio() {
		super();
	}
	public Long getPortfolioId() {
		return portfolioId;
	}
	public void setPortfolioId(Long portfolioId) {
		this.portfolioId = portfolioId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getInitialValue() {
		return initialValue;
	}
	public void setInitialValue(BigDecimal initialValue) {
		this.initialValue = initialValue;
	}
	public BigDecimal getCurrentValue() {
		return currentValue;
	}
	public void setCurrentValue(BigDecimal currentValue) {
		this.currentValue = currentValue;
	}
    
    
}