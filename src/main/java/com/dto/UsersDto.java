package com.dto;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {
	
	private Long id;
	private String username;
	private String password;
	private String email;
	private Long id_company;
}