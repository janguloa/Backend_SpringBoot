package com.dto;

import java.math.BigInteger;

import com.model.Operations;
import com.model.UpdateType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchasesDetailsDto {
	
	private BigInteger id;
	private String description;
	private int quantity;
	private int totalFaulty;
	private Double unitaryCost;
	private Double unitaryShippingCost;
	private Double taxesCost;
	private UpdateType updateType;
	private BigInteger id_company; 
	private BigInteger id_purchases;
	private BigInteger id_product;
	private Operations operations;

}