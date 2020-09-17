package com.dto;

import com.model.UpdateType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchasesDetailsDto {
	
	private Long id;
	private String description;
	private int quantity;
	private Double unitaryCost;
	private Double unitaryShippingCost;
	private Double taxesCost;
	private UpdateType updateType;
	private Long id_company; 
	private Long id_purchases;
	private Long id_product;

}