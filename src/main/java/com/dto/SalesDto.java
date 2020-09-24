package com.dto;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesDto {
	
	private BigInteger id;
	private String receipt;
	private int quantity;
	private Double unitaryPrice;
	private Double totalprice;
	private BigInteger id_company; 
	private BigInteger id_product;
	private BigInteger id_customer;

}