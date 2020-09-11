package com.dto;

import com.model.Company;
import com.model.Users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsDto {

	private String codproduct;
	private String description;
	private Users user;
	private Company company;
	
}