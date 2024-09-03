package com.cams.stock.controller;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cams.stock.Order;
import com.cams.stock.Stock;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Custom query methods can be added here if needed
	Optional<Stock> findByStockId(Long id);
}