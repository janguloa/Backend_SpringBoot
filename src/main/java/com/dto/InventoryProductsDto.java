package com.dto;

import java.math.BigInteger;

import com.model.Operations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryProductsDto {
	
	private int count;
	private BigInteger id;
	private Double priceSale;
	private int totalFaulty;
	private Double discountPercent;
	private BigInteger idProduct;
	private BigInteger idCompany;
	private BigInteger purchasesDetId;
	private Operations operations;
}