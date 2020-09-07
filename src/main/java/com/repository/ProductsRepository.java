package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Company;
import com.model.Products;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {
	
	Optional<Products> findById(Long id);
	
	Optional<Products> findByCompany(Company company);
	
	Optional<Products> findByDescription(String description);
	
	Optional<Products> findByCodProduct(String codproduct);
	
}