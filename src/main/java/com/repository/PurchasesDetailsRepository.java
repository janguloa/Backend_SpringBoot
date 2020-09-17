package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.PurchasesDetails;

public interface PurchasesDetailsRepository extends JpaRepository<PurchasesDetails, Long> {
	
	Optional<PurchasesDetails> findById(Long id);
	
	Optional<PurchasesDetails> findByIdProduct(Long id);
}