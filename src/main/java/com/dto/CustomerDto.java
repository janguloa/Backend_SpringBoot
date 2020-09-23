package com.dto;

import java.math.BigInteger;
import com.model.UpdateType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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