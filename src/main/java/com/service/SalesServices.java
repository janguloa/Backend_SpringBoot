package com.service;

import java.time.Instant;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.dto.SalesDto;
import com.exceptions.SpringInventoryException;
import com.model.Company;
import com.model.Customer;
import com.model.Products;
import com.model.Sales;
import com.repository.CompanyRepository;
import com.repository.CustomerRepository;
import com.repository.ProductsRepository;
import com.repository.SalesRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class SalesServices {
	
	private final SalesRepository salesRepository;
	private final CompanyRepository companyRepository;
	private final ProductsRepository productsRepository;
	private final CustomerRepository customerRepository;
	private final ProductsService productsService;
	
	@Transactional
	public SalesDto create(SalesDto salesDto) {
		
		Company company = companyRepository.findById(salesDto.getId_company())
				.orElseThrow(() -> new SpringInventoryException("La compañia no existe con el siguiente codigo " + salesDto.getId_company()));
		
		Products products = productsRepository.findById(salesDto.getId_product())
				.orElseThrow(() -> new SpringInventoryException("Producto no encontrada con el código " + salesDto.getId_product())); 
		
		Customer customer = customerRepository.findById(salesDto.getId_customer())
				.orElseThrow(() -> new SpringInventoryException("El cliente no fue encontrado con el siguiente codigo " + salesDto.getId_customer()));
		
		salesDto.setUnitaryPrice(products.getPriceSale());
		salesRepository.save(mapToSales(salesDto, company, products, customer));
		productsService.updateStock(salesDto.getQuantity(), products);
		
		return salesDto;
	}
	
	@Transactional
	public SalesDto cancel(SalesDto salesDto) {
		
		return salesDto;
	}
	
	private Sales mapToSales(SalesDto salesDto, Company company, Products products, Customer customer) {
		
		return Sales.builder()
				.createdDate(Instant.now())
				.receipt(salesDto.getReceipt())
				.quantity(salesDto.getQuantity())
				.unitaryPrice(salesDto.getUnitaryPrice())
				.totalprice(getTotalPrice(salesDto.getQuantity(), salesDto.getUnitaryPrice()))
				.company(company)
				.products(products)
				.customer(customer)
				.build();
	}
	
	private double getTotalPrice(double quantity, double unitaryPrice) {
		
		return quantity * unitaryPrice;
	}
}