package com.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Company;
import com.model.InventoryProducts;

@Repository
public interface InventoryProductsRepository extends JpaRepository<InventoryProducts, BigInteger> {
	
	Optional<InventoryProducts> findById(BigInteger id);
	
	void deleteById(BigInteger id);
	
	List<InventoryProducts> findAllByCompany(Company company);

}