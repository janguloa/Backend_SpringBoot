package com.controller;

import static com.model.UpdateType.DELETE;
import static com.model.UpdateType.UPDATE;
import static com.model.Operations.ENABLED;
import static com.model.Operations.DISABLED;

import java.math.BigInteger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public ResponseEntity<Void> create(@RequestBody ProductsDto productsDto) {
		
		productsDto.setOperations(ENABLED);
		productsService.save(productsDto);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Void> update(@PathVariable BigInteger id, @RequestBody ProductsDto productsDto) {
		
		productsDto.setId(id);
		productsDto.setUpdateType(UPDATE);
		productsService.update(productsDto);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable BigInteger id, @RequestBody ProductsDto productsDto) {
		
		productsDto.setId(id);
		productsDto.setUpdateType(DELETE);
		productsDto.setOperations(DISABLED);
		productsService.update(productsDto);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}