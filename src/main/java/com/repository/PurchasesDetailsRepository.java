package com.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.model.Products;
import com.model.PurchasesDetails;

public interface PurchasesDetailsRepository extends JpaRepository<PurchasesDetails, BigInteger> {
	
	Optional<PurchasesDetails> findById(BigInteger id);
	
	@Query(value="SELECT e from purchases_details e WHERE e.purchases_det_id = :id_det_purchases AND m.assigned = :id_assigned",  nativeQuery=true)
	Optional<PurchasesDetails> findByIdAndAssignedQuery(@Param("id_det_purchases") BigInteger id_det_purchases, @Param("id_assigned") boolean assigned);

	Optional<PurchasesDetails> findByProducts(Products products);
	
	Optional<PurchasesDetails> findByProductsAndAssigned(Products products, boolean assigned);
	
	@Query(value="SELECT m.purchases_det_id, m.id_company, m.id_product, m.id_purchases, m.taxes_cost, m.unitary_cost, m.unitary_shipping_cost, m.quantity, m.createdate, m.description,"
			+ " m.enabled FROM purchases_details m WHERE m.id_purchases = :id_purchases AND m.enabled = :id_enabled", nativeQuery=true)
	List <PurchasesDetails> getAllByPurchases(@Param("id_purchases") BigInteger id_purchases, @Param("id_enabled") boolean id_enabled);
	
}