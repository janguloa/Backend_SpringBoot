package com.service;

import java.time.Instant;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.dto.InventoryProductsDto;
import com.exceptions.SpringInventoryException;
import com.model.Company;
import com.model.InventoryProducts;
import com.model.Products;
import com.repository.CompanyRepository;
import com.repository.InventoryProductsRepository;
import com.repository.ProductsRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InventoryProductsService {
	
	private final InventoryProductsRepository inventoryProductsRepository;
	private final CompanyRepository companyRepository;
	private final ProductsRepository productsRepository;
	private final AuthService authService;
	
	@Transactional
	public InventoryProductsDto save (InventoryProductsDto inventoryProductsDto) {
		
		Company company = companyRepository.findById(inventoryProductsDto.getIdCompany())
				.orElseThrow(()-> new SpringInventoryException("La compañia no existe con el siguiente codigo" + inventoryProductsDto.getIdCompany()));
		
		Products products = productsRepository.findById(inventoryProductsDto.getIdProduct())
				.orElseThrow(()-> new SpringInventoryException("La compañia no existe con el siguiente codigo" + inventoryProductsDto.getIdProduct()));
		
		inventoryProductsRepository.save(inventoryProductsToDto(products, company, inventoryProductsDto));
		
		return inventoryProductsDto;
	}
	
	private InventoryProducts inventoryProductsToDto (Products products, Company company, InventoryProductsDto inventoryProductsDto) {
	
		
		return InventoryProducts.builder()
				.count(inventoryProductsDto.getCount())
				.priceSale(inventoryProductsDto.getPriceSale())
				.totalFaulty(inventoryProductsDto.getTotalFaulty())
				.discountPercent(inventoryProductsDto.getDiscountPercent())
				.totalStock(getTotalStock(inventoryProductsDto))
			//	.cost(products.fe)
				.products(products)
				.company(company)
				.user(authService.getCurrentUser())
				.createdDate(Instant.now())
				.build();
	}
	
	private int getTotalStock(InventoryProductsDto inventoryProductsDto) {
		
		return inventoryProductsDto.getCount() - inventoryProductsDto.getTotalFaulty();
		
	}
}