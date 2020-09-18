package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.model.Products;
import com.model.Purchases;
import com.model.PurchasesDetails;

public interface PurchasesDetailsRepository extends JpaRepository<PurchasesDetails, Long> {
	
	Optional<PurchasesDetails> findById(Long id);
	
	Optional<PurchasesDetails> findByProducts(Products products);
	
	@Query("SELECT SUM(m.taxes_cost) FROM purchases_details m")
	Double getAllByPurchases(Purchases purchases);
}