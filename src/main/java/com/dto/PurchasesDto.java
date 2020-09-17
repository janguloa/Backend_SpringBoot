package com.dto;

import com.model.UpdateType;

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
	private UpdateType updateType;

}