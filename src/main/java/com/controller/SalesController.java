package com.controller;

import java.math.BigInteger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.ProductsDto;
import com.dto.SalesDto;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/auth/sales/")
@AllArgsConstructor
public class SalesController {
	
	@PostMapping("/create")
	public ResponseEntity<Void> create(@RequestBody SalesDto salesDto) {
		
		//Llamar a servicio
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/cancel/{id}")
	public ResponseEntity<Void> cancel(@PathVariable BigInteger id, @RequestBody ProductsDto productsDto){
		
		//Llamar a servicio
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}