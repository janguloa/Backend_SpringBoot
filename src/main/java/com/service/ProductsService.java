package com.service;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.dto.ProductsDto;
import com.exceptions.SpringInventoryException;
import com.model.Company;
import com.model.Products;
import com.repository.CompanyRepository;
import com.repository.ProductsRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductsService {
	
	private final ProductsRepository productsRepository;
	private final CompanyRepository companyRepository;
	private final AuthService authService;
	
	public ProductsDto save(ProductsDto productsDto) {
		
		Company company = companyRepository.findById(productsDto.getId_company())
				.orElseThrow(() -> new SpringInventoryException("Compañia no encontrada con el código" + productsDto.getId_company()));
		
		productsRepository.save(ProductsDto(productsDto, company));
		
		return productsDto;
	}
	
	private Products ProductsDto(ProductsDto productsDto, Company company) {
		
		return Products.builder()
				.codproduct(productsDto.getCodproduct())
				.description(productsDto.getDescription())
				.company(company)
				.createdDate(Instant.now())
				.user(authService.getCurrentUser())
				.state(true)
				.build();
	}
}