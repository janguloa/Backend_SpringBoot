package com.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.ProductsDto;
import com.service.ProductsService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/auth/products/")
@AllArgsConstructor

public class ProductsController {
	
	private final ProductsService productsService;
	
	@PostMapping("/create")
	public ResponseEntity<Void> created(@RequestBody ProductsDto productsDto) {
		
		productsService.save(productsDto);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}	
}