package com.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.PurchasesDto;
import com.service.PurchasesService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/auth/purchases/")
@AllArgsConstructor
public class PurchasesController {
	
	private final PurchasesService purchasesService;
	
	@PostMapping("/create")
	public ResponseEntity<Void> create (@RequestBody PurchasesDto purchasesDto) {
		
		purchasesService.save(purchasesDto);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
		
	}
}