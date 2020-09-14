package com.service;

import static com.model.UpdateType.UPDATE;
import java.time.Instant;
import javax.transaction.Transactional;
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
	
	@Transactional
	public ProductsDto save(ProductsDto productsDto) {
		
		Company company = companyRepository.findById(productsDto.getId_company())
				.orElseThrow(() -> new SpringInventoryException("Compañia no encontrada con el código" + productsDto.getId_company()));
		
		productsRepository.save(ProductsDto(productsDto, company));
		
		return productsDto;
	}
	
	@Transactional
	public ProductsDto update(ProductsDto productsDto) {
		
		fetchProductsAndEnable(productsDto);
		
		return productsDto;
	}
	
	private Products ProductsDto(ProductsDto productsDto, Company company) {
		
		return Products.builder()
				.codproduct(productsDto.getCodproduct())
				.description(productsDto.getDescription())
				.company(company)
				.createdDate(Instant.now())
				.user(authService.getCurrentUser())
				.enabled(true)
				.build();
	}
	
	@Transactional
	private void fetchProductsAndEnable(ProductsDto productsDto) {
		
		Products products = productsRepository.findById(productsDto.getId())
				.orElseThrow(() -> new SpringInventoryException("El producto no fue encontrado con el siguiente codigo " + productsDto.getId()));
		
		if(UPDATE.equals(productsDto.getUpdateType())) {
			
			products.setDescription(productsDto.getDescription().toUpperCase());
			
		} else {
			products.setEnabled(false);
		}
		
		productsRepository.save(products);
	}
}