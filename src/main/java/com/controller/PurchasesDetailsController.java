package com.controller;

import static com.model.UpdateType.DELETE;
import static com.model.UpdateType.UPDATE;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.PurchasesDetailsDto;
import com.service.PurchasesDetailsService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/auth/purchases/details")
@AllArgsConstructor
public class PurchasesDetailsController {
	
	private final PurchasesDetailsService purchasesDetailsService;
	
	@PostMapping("/create")
	public ResponseEntity<Void> create(@RequestBody PurchasesDetailsDto purchasesDetailsDto) {
		
		purchasesDetailsService.save(purchasesDetailsDto);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody PurchasesDetailsDto purchasesDetailsDto) {
		
		purchasesDetailsDto.setId(id);
		purchasesDetailsDto.setUpdateType(UPDATE);
		
		purchasesDetailsService.update(purchasesDetailsDto);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id, @RequestBody PurchasesDetailsDto purchasesDetailsDto) {
		
		purchasesDetailsDto.setId(id);
		purchasesDetailsDto.setUpdateType(DELETE);
		
		purchasesDetailsService.update(purchasesDetailsDto);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}