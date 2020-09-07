package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Company;
import com.model.InventoryProducts;

public interface InventoryProductsRepository extends JpaRepository<InventoryProducts, Long> {
	
	Optional<InventoryProducts> findById(Long id);
	
	Optional<InventoryProducts> findByCompany(Company company);

}