package com.dto;

import java.math.BigInteger;

import com.model.UpdateType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
	
	private BigInteger id;
	private String description;
	private UpdateType updateType;
}