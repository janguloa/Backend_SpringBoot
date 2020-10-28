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
public class ProductsDto {

	private BigInteger id;
	private String codproduct;
	private String description;
	private Double priceSale;
	private int reOrderStock;
	private int totalFaulty;
	private Double discountPercent;
	private BigInteger id_company;
	private UpdateType updateType;
	private Operations operations;
	
}