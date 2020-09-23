package com.dto;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryProductsDto {
	
	private BigInteger id;
	private int count;
	private Double priceSale;
	private int totalFaulty;
	private Double discountPercent;
	private BigInteger idProduct;
	private BigInteger idCompany;
}