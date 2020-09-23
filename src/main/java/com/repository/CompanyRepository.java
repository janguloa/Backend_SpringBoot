package com.repository;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, BigInteger> {
	
	Optional<Company> findById(BigInteger id);
	
	Optional<Company> findByDescription(String description);

}