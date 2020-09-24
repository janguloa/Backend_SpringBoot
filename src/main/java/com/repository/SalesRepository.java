package com.repository;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Sales;

@Repository
public interface SalesRepository extends JpaRepository<Sales, BigInteger> {
	
	Optional<Sales> findById(BigInteger id);

}