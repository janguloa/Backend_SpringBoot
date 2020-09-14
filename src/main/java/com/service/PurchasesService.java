package com.service;

import java.time.Instant;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.dto.PurchasesDto;
import com.exceptions.SpringInventoryException;
import com.model.Company;
import com.model.Purchases;
import com.repository.CompanyRepository;
import com.repository.PurchasesRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PurchasesService {
	
	private final PurchasesRepository purchasesRepository;
	private final CompanyRepository companyRepository;
	
	@Transactional
	public PurchasesDto save (PurchasesDto purchasesDto) {
		
		Company company = companyRepository.findById(purchasesDto.getId_company())
				.orElseThrow(() -> new SpringInventoryException("Compañia no encontrada con el código" + purchasesDto.getId_company()));
		
		
		purchasesRepository.save(PurchasesDto(purchasesDto, company));
		
		return purchasesDto;
		
	}
	
	private Purchases PurchasesDto(PurchasesDto purchasesDto, Company company) {
		
		return Purchases.builder()
				.description(purchasesDto.getDescription())
				.createdate(Instant.now())
				.totalPrice(purchasesDto.getTotalPrice())
				.company(company)
				.build();
	}
}