package com.dto;

import com.model.UpdateType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryProductsDto {
	
	private Long id;
	private int count;
	private Double priceSale;
	private int totalFaulty;
	private Double discountPercent;
	private Long idProduct;
	private Long idCompany;
}