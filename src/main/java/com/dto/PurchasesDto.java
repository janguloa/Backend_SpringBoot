package com.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchasesDto {
	
	private Long id;
	private String description;
	private Long id_company; 
	private Double totalPrice;

}