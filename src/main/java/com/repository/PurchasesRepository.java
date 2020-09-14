package com.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.model.Purchases;

public interface PurchasesRepository extends JpaRepository<Purchases, Long> {
	
	Optional<Purchases> findById(Long id);

}