package com.repository;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Purchases;

@Repository
public interface PurchasesRepository extends JpaRepository<Purchases, BigInteger> {
	
	Optional<Purchases> findById(BigInteger id);

}