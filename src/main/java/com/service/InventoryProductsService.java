package com.service;

import java.time.Instant;
import static com.model.UpdateType.UPDATE;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.dto.InventoryProductsDto;
import com.exceptions.SpringInventoryException;
import com.model.Company;
import com.model.InventoryProducts;
import com.model.Products;
import com.model.PurchasesDetails;
import com.repository.CompanyRepository;
import com.repository.InventoryProductsRepository;
import com.repository.ProductsRepository;
import com.repository.PurchasesDetailsRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InventoryProductsService {
	
	private final InventoryProductsRepository inventoryProductsRepository;
	private final CompanyRepository companyRepository;
	private final ProductsRepository productsRepository;
	private final PurchasesDetailsRepository purchasesDetailsRepository;
	private final AuthService authService;
	
	@Transactional
	public InventoryProductsDto save (InventoryProductsDto inventoryProductsDto) {
		
		Company company = companyRepository.findById(inventoryProductsDto.getIdCompany())
				.orElseThrow(()-> new SpringInventoryException("La compañia no existe con el siguiente codigo " + inventoryProductsDto.getIdCompany()));
		
		Products products = productsRepository.findById(inventoryProductsDto.getIdProduct())
				.orElseThrow(()-> new SpringInventoryException("La compañia no existe con el siguiente codigo " + inventoryProductsDto.getIdProduct()));
		
		inventoryProductsRepository.save(inventoryProductsToDto(products, company, inventoryProductsDto));
		
		return inventoryProductsDto;
	}
	
	public InventoryProductsDto update (InventoryProductsDto inventoryProductsDto) {
		
		fetchInventoryProdAndEnable(inventoryProductsDto);
		
		return inventoryProductsDto;
	}
	
	public void delete (InventoryProductsDto inventoryProductsDto) {
		
		inventoryProductsRepository.deleteById(inventoryProductsDto.getId());
		
	}
	
	@Transactional
	private InventoryProducts inventoryProductsToDto (Products products, Company company, InventoryProductsDto inventoryProductsDto) {
	
		PurchasesDetails purchasesDetails = purchasesDetailsRepository.findByProducts(products)
				.orElseThrow(() -> new SpringInventoryException("La purchasesDetails no existe con el siguiente codigo " + inventoryProductsDto.getIdProduct()));
		
		return InventoryProducts.builder()
				.count(inventoryProductsDto.getCount())
				.priceSale(inventoryProductsDto.getPriceSale())
				.totalFaulty(inventoryProductsDto.getTotalFaulty())
				.discountPercent(inventoryProductsDto.getDiscountPercent())
				.totalStock(getTotalStock(inventoryProductsDto))
				.cost(purchasesDetails.fetchCost())
				.products(products)
				.company(company)
				.user(authService.getCurrentUser())
				.createdDate(Instant.now())
				.build();
	}
	
	@Transactional
	private void fetchInventoryProdAndEnable(InventoryProductsDto inventoryProductsDto) {
		
		InventoryProducts inventoryProducts = inventoryProductsRepository.findById(inventoryProductsDto.getId())
				.orElseThrow(() -> new SpringInventoryException("La inventario de producto no existe con el siguiente codigo " + inventoryProductsDto.getId()));
		
		inventoryProducts.setPriceSale(inventoryProductsDto.getPriceSale());
		inventoryProducts.setTotalFaulty(inventoryProductsDto.getTotalFaulty());
		inventoryProducts.setDiscountPercent(inventoryProductsDto.getDiscountPercent());
		inventoryProducts.setCount(inventoryProductsDto.getCount());
		
		inventoryProductsRepository.save(inventoryProducts);
	}
	
	private int getTotalStock(InventoryProductsDto inventoryProductsDto) {
		
		return inventoryProductsDto.getCount() - inventoryProductsDto.getTotalFaulty();
		
	}
}