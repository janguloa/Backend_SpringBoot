package com.dto;

import com.model.UpdateType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CompanyDto {
	
	Long id;
	String description;
	UpdateType updateType;
}