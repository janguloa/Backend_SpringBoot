package com.service;

import java.time.Instant;
import static com.model.UpdateType.UPDATE;

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
	
	@Transactional
	public PurchasesDto update (PurchasesDto purchasesDto) {
		
		fetchPurchasesAndEnabled(purchasesDto);
		
		return purchasesDto;
	}
	
	private Purchases PurchasesDto(PurchasesDto purchasesDto, Company company) {
		
		return Purchases.builder()
				.description(purchasesDto.getDescription())
				.createdate(Instant.now())
				.totalPrice(0)
				.enabled(true)
				.company(company)
				.build();
	}
	
	@Transactional
	private void fetchPurchasesAndEnabled (PurchasesDto purchasesDto) {
		
		Purchases purchases = purchasesRepository.findById(purchasesDto.getId())
				.orElseThrow(() -> new SpringInventoryException("La compra no fue encontrado con el siguiente codigo " + purchasesDto.getId()));
		
		if(UPDATE.equals(purchasesDto.getUpdateType())) {
			
			
			purchases.setDescription(purchasesDto.getDescription());
		}
		
		else {
			purchases.setEnabled(false);
		}
		
		purchasesRepository.save(purchases);
	}
	
	@Transactional
	public void updateTotalPrice (Long IdPurchases, Double totalPrice) {
		
		Purchases purchases = purchasesRepository.findById(IdPurchases)
				.orElseThrow(() -> new SpringInventoryException("La compra no fue encontrado con el siguiente codigo " + IdPurchases));

		purchases.setTotalPrice(purchases.getTotalPrice() + totalPrice);
		
		purchasesRepository.save(purchases);
	}
}