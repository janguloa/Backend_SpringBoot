package com.dto;

import java.math.BigInteger;

import com.model.UpdateType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
	
	private BigInteger id;
	private String customerIdent;
	private String names;
	private String email;
	private String telephone;
	private UpdateType updateType;
	
}