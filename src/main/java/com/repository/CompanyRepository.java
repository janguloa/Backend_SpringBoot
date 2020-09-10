package com.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
	
	Optional<Company> findById(Long id);
	
	Optional<Company> findByDescription(String description);

}