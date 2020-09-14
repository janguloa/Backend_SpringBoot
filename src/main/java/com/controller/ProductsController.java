package com.controller;

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
import static com.model.UpdateType.DELETE;
import static com.model.UpdateType.UPDATE;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/auth/products/")
@AllArgsConstructor

public class ProductsController {
	
	private final ProductsService productsService;
	
	@PostMapping("/create")
	public ResponseEntity<Void> create(@RequestBody ProductsDto productsDto) {
		
		productsService.save(productsDto);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody ProductsDto productsDto) {
		
		productsDto.setId(id);
		productsDto.setUpdateType(UPDATE);
		productsService.update(productsDto);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id, @RequestBody ProductsDto productsDto) {
		
		productsDto.setId(id);
		productsDto.setUpdateType(DELETE);
		productsService.update(productsDto);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}