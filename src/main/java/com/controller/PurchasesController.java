package com.controller;

import static com.model.UpdateType.DELETE;
import static com.model.UpdateType.UPDATE;

import java.math.BigInteger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Void> update(@PathVariable BigInteger id, @RequestBody PurchasesDto purchasesDto) {
		
		purchasesDto.setId(id);
		purchasesDto.setUpdateType(UPDATE);
		purchasesService.update(purchasesDto);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable BigInteger id, @RequestBody PurchasesDto purchasesDto) {
		
		purchasesDto.setId(id);
		purchasesDto.setUpdateType(DELETE);
		purchasesService.update(purchasesDto);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}