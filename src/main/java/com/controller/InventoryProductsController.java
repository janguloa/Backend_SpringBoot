package com.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.InventoryProductsDto;
import com.service.InventoryProductsService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/auth/inventory/")
@AllArgsConstructor

public class InventoryProductsController {
	
	final private InventoryProductsService inventoryProductsService;
	
	@PostMapping("/create")
	public ResponseEntity<Void> save(@RequestBody InventoryProductsDto inventoryProductsDto) {
		
		inventoryProductsService.save(inventoryProductsDto);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
		
	}
}