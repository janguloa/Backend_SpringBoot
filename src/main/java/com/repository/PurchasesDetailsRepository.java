package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.Products;
import com.model.PurchasesDetails;

public interface PurchasesDetailsRepository extends JpaRepository<PurchasesDetails, Long> {
	
	Optional<PurchasesDetails> findById(Long id);
	
	Optional<PurchasesDetails> findByProducts(Products products);
	
	@Query(value="SELECT m.purchases_det_id, m.taxes_cost,  m.unitary_cost, m.unitary_shipping_cost, m.quantity FROM purchases_details m WHERE m.id_purchases = :id_purchases", nativeQuery=true)
	List <PurchasesDetails> getAllByPurchases(@Param("id_purchases") Long id_purchases);
	
}