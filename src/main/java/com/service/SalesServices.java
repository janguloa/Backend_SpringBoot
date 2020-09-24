package com.service;

import java.time.Instant;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.dto.SalesDto;
import com.model.Sales;
import com.repository.SalesRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class SalesServices {
	
	private final SalesRepository salesRepository;
	
	@Transactional
	public SalesDto create(SalesDto salesDto) {
		
		
		
		return salesDto;
	}
	
	@Transactional
	public SalesDto cancel(SalesDto salesDto) {
		
		return salesDto;
	}
	
	private Sales mapToSales(SalesDto salesDto) {
		
		return Sales.builder()
				.createdDate(Instant.now())
				.receipt(salesDto.getReceipt())
				.quantity(salesDto.getQuantity())
				.unitaryPrice(salesDto.getUnitaryPrice())
				.totalprice(salesDto.getTotalprice())
				// Falta company
				// Falta producto
				// Falta customer
				.build();
	}
}