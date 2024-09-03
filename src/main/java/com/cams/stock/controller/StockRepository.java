package com.cams.stock.controller;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cams.stock.Order;
import com.cams.stock.Stock;


public interface StockRepository extends JpaRepository<Stock, Long> {

//	Optional<Stock> findByStockId(Long id);

	Optional<Stock> findByStockId(String stock_id);
    
}

