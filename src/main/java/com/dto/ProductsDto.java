package com.dto;

import java.math.BigInteger;

import com.model.UpdateType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsDto {

	private BigInteger id;
	private String codproduct;
	private String description;
	private BigInteger id_company;
	private UpdateType updateType;
	
}