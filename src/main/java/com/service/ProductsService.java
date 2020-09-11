package com.service;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.dto.ProductsDto;
import com.model.Products;
import com.repository.ProductsRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductsService {
	
	private final ProductsRepository productsRepository;
	
	public ProductsDto save(ProductsDto productsDto) {
		
		Products save = productsRepository.save(ProductsDto(productsDto));
		
		return productsDto;
	}
	
	
	private Products ProductsDto(ProductsDto productsDto) {
		
		return Products.builder()
				.codproduct(productsDto.getCodproduct())
				.description(productsDto.getDescription())
				.createdDate(Instant.now())
				.state(true)
				.build();
	}
}