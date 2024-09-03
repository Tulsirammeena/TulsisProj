package com.cams.stock.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cams.stock.Portfolio;


@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
	    Optional<Portfolio> findByUserIdAndStockId(Long userId, Long stockId);
}

