package com.service;

import static com.model.UpdateType.UPDATE;
import static com.model.Operations.ENABLED;

import java.math.BigInteger;
import java.time.Instant;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.dto.ProductsDto;
import com.exceptions.SpringInventoryException;
import com.model.Company;
import com.model.Operations;
import com.model.Products;
import com.model.PurchasesDetails;
import com.repository.CompanyRepository;
import com.repository.ProductsRepository;
import com.repository.PurchasesDetailsRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductsService {
	
	private final ProductsRepository productsRepository;
	private final CompanyRepository companyRepository;
	private final PurchasesDetailsRepository purchasesDetailsRepository;
	private final AuthService authService;
	
	@Transactional
	public ProductsDto save(ProductsDto productsDto) {
		
		Company company = companyRepository.findById(productsDto.getId_company())
				.orElseThrow(() -> new SpringInventoryException("Compañia no encontrada con el código " + productsDto.getId_company()));
		
		Optional<Products> products = productsRepository.findByCodproduct(productsDto.getCodproduct());
			
		if(products.isPresent()) {
			
			throw new SpringInventoryException("El codigo de producto ya esta registrado " + productsDto.getCodproduct());		
		}
		else {	
			productsRepository.save(ProductsDto(productsDto, company));		
		}	
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
				.reOrderStock(productsDto.getReOrderStock())
				.priceSale(productsDto.getPriceSale())
				.discountPercent(productsDto.getDiscountPercent())
				.cost(0.0)
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
			products.setReOrderStock(productsDto.getReOrderStock());
			products.setPriceSale(productsDto.getPriceSale());
			products.setDiscountPercent(productsDto.getDiscountPercent());		
		} 
		else {
			products.setEnabled(false);
		}
		
		productsRepository.save(products);
	}
	
	@Transactional
	public void updateAssigned(BigInteger IdPurchasesDet, Operations operations) {
		
		PurchasesDetails purchasesDetails = purchasesDetailsRepository.findById(IdPurchasesDet)
				.orElseThrow(() -> new SpringInventoryException("EL detalle de compra no fue encontrado con el codigo " + IdPurchasesDet));

			if (ENABLED.equals(operations)) {
				
				purchasesDetails.setAssigned(true);
			}
			else 
			{
				purchasesDetails.setAssigned(false);
			}
		
			purchasesDetailsRepository.save(purchasesDetails);
	}
	
	@Transactional
	public void updateStock(int Quantity, Products products, int operator) {
		
		if (operator == 0)
		{
			products.setTotalStock(products.getTotalStock() + Quantity);
		}
		else
		{
			products.setTotalStock(products.getTotalStock() - Quantity);
		}
		
		productsRepository.save(products);
		
	}	
}