package com.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Company;
import com.model.Products;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {
	
	Optional<Products> findById(Long id);
	
	List<Products> findAllByCompany(Company company);
	
}