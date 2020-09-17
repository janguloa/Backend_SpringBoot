package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Company;
import com.model.InventoryProducts;

@Repository
public interface InventoryProductsRepository extends JpaRepository<InventoryProducts, Long> {
	
	Optional<InventoryProducts> findById(Long id);
	
	void deleteById(Long id);
	
	List<InventoryProducts> findAllByCompany(Company company);

}