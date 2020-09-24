package com.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Company;
import com.model.Products;

@Repository
public interface ProductsRepository extends JpaRepository<Products, BigInteger> {
	
	Optional<Products> findById(BigInteger id);
	
	Optional<Products> findByCodproduct(String code);
	
	List<Products> findAllByCompany(Company company);
	
}