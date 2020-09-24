package com.service;

import static com.model.UpdateType.UPDATE;
import static com.model.Operations.ENABLED;
import java.math.BigInteger;
import java.time.Instant;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.dto.PurchasesDetailsDto;
import com.exceptions.SpringInventoryException;
import com.model.Company;
import com.model.Products;
import com.model.Purchases;
import com.model.PurchasesDetails;
import com.repository.CompanyRepository;
import com.repository.ProductsRepository;
import com.repository.PurchasesDetailsRepository;
import com.repository.PurchasesRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PurchasesDetailsService {
	
	private final PurchasesDetailsRepository purchasesDetailsRepository;
	private final CompanyRepository companyRepository;
	private final PurchasesRepository purchasesRepository;
	private final ProductsRepository productsRepository;
	private final PurchasesService purchasesService;
	private final ProductsService productsService;
	
	@Transactional
	public PurchasesDetailsDto save(PurchasesDetailsDto purchasesDetailsDto) {
		
		Company company = companyRepository.findById(purchasesDetailsDto.getId_company())
				.orElseThrow(() -> new SpringInventoryException("Compañia no encontrada con el código " + purchasesDetailsDto.getId_company()));
		
		Purchases purchases = purchasesRepository.findById(purchasesDetailsDto.getId_purchases())
				.orElseThrow(() -> new SpringInventoryException("Compra no encontrada con el código " + purchasesDetailsDto.getId_purchases()));
		
		Products products = productsRepository.findById(purchasesDetailsDto.getId_product())
				.orElseThrow(() -> new SpringInventoryException("Producto no encontrada con el código " + purchasesDetailsDto.getId_product())); 
				
		
		purchasesDetailsRepository.save(PurchasesDetailsToDto(purchasesDetailsDto, company, purchases, products));

		purchasesService.updateTotalPrice(purchasesDetailsDto.getId_purchases(), getAllPurchasesDetailsForPurchases(purchasesDetailsDto.getId_purchases()));
		
		purchasesDetailsDto.setOperations(ENABLED);
		
		addPurchasesDetails(purchasesDetailsDto);
		
		return purchasesDetailsDto;
	}
	
	@Transactional
	public PurchasesDetailsDto update(PurchasesDetailsDto purchasesDetailsDto) {
		
		fetchPurchasesDetailsAndEnable(purchasesDetailsDto);
		purchasesService.updateTotalPrice(purchasesDetailsDto.getId_purchases(), getAllPurchasesDetailsForPurchases(purchasesDetailsDto.getId_purchases()));
		
		return purchasesDetailsDto;
	}
	
	private PurchasesDetails PurchasesDetailsToDto (PurchasesDetailsDto purchasesDetailsDto, Company company, Purchases purchases, Products products) {
		
		return PurchasesDetails.builder()
				.description(purchasesDetailsDto.getDescription())
				.unitaryCost(purchasesDetailsDto.getUnitaryCost())
				.quantity(purchasesDetailsDto.getQuantity())
				.totalFaulty(purchasesDetailsDto.getTotalFaulty())
				.unitaryShippingCost(purchasesDetailsDto.getUnitaryShippingCost())
				.taxesCost(purchasesDetailsDto.getTaxesCost())
				.createdate(Instant.now())
				.company(company)
				.purchases(purchases)
				.products(products)
				.enabled(true)
				.assigned(false)
				.build();
	}	
	
	@Transactional
	private void fetchPurchasesDetailsAndEnable (PurchasesDetailsDto purchasesDetailsDto) {
		
		PurchasesDetails purchasesDetails = purchasesDetailsRepository.findById(purchasesDetailsDto.getId())
				.orElseThrow(() -> new SpringInventoryException("El detalle de compra no fue encontrado con el siguiente codigo " + purchasesDetailsDto.getId()));
		
		if(UPDATE.equals(purchasesDetailsDto.getUpdateType())) {
			
			purchasesDetails.setDescription(purchasesDetailsDto.getDescription());
			purchasesDetails.setQuantity(purchasesDetailsDto.getQuantity());
			purchasesDetails.setTaxesCost(purchasesDetailsDto.getTaxesCost());
			purchasesDetails.setUnitaryCost(purchasesDetailsDto.getUnitaryCost());
			purchasesDetails.setUnitaryShippingCost(purchasesDetailsDto.getUnitaryShippingCost());
			
		} else {
			
			purchasesDetailsDto.setId_purchases(purchasesDetails.getPurchases().getPurchasesId());
			purchasesDetailsDto.setUnitaryCost(purchasesDetails.getUnitaryCost());
			purchasesDetailsDto.setUnitaryShippingCost(purchasesDetails.getUnitaryShippingCost());
			purchasesDetailsDto.setTaxesCost(purchasesDetails.getTaxesCost());
			purchasesDetailsDto.setQuantity(purchasesDetails.getQuantity());
			purchasesDetails.setEnabled(false);	
		}
	}
	
	private double getAllPurchasesDetailsForPurchases(BigInteger id) {
		
		double unitaryProduct;
		double totalProduct;
		double totalPurchases = 0;
			
		List<PurchasesDetails> listaDetails = purchasesDetailsRepository.getAllByPurchases(id, true);
		
		for(PurchasesDetails item: listaDetails) {
		
			unitaryProduct = item.getUnitaryCost() + item.getUnitaryShippingCost() + item.getTaxesCost();
			totalProduct = unitaryProduct * item.getQuantity();
			
			totalPurchases += totalProduct;
		}
		return 	totalPurchases;
	}
	
	@Transactional
	public void addPurchasesDetails(PurchasesDetailsDto purchasesDetailsDto) {
		
		Products products = productsRepository.findById(purchasesDetailsDto.getId_product())
				.orElseThrow(() -> new SpringInventoryException("El producto no fue encontrado con el siguiente codigo " + purchasesDetailsDto.getId_product()));
		
		PurchasesDetails purchasesDetails = purchasesDetailsRepository.findByProductsAndAssigned(products, false)
				.orElseThrow(() -> new SpringInventoryException("El detalle de compra no tiene disponible el producto con el siguiente codigo " + purchasesDetailsDto.getId_product()));
		
		products.setTotalStock(getTotalStock(purchasesDetailsDto.getTotalFaulty(), purchasesDetailsDto.getQuantity()));
		products.setCost(purchasesDetails.fetchCost());
		products.setTotalFaulty(purchasesDetailsDto.getTotalFaulty());
		
		productsRepository.save(products);
		
		productsService.updateAssigned(purchasesDetails.getPurchasesDetId(), purchasesDetailsDto.getOperations());
	}

	private int getTotalStock(int totalFaulty, int count) {
		
		return count - totalFaulty;
	}
}