package com.controller;

import static com.model.Operations.ADD;
import static com.model.Operations.EQUALIZE;
import static com.model.Operations.SUBTRACT;
import static com.model.UpdateType.DELETE;
import static com.model.UpdateType.UPDATE;
import static org.springframework.http.ResponseEntity.status;

import java.math.BigInteger;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
		
		purchasesDetailsDto.setOperations(ADD);
		purchasesDetailsService.save(purchasesDetailsDto);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{idDet}")
	public ResponseEntity<Void> update(@PathVariable BigInteger idDet, @RequestBody PurchasesDetailsDto purchasesDetailsDto) {
		
		purchasesDetailsDto.setId(idDet);
		purchasesDetailsDto.setUpdateType(UPDATE);
		purchasesDetailsDto.setOperations(EQUALIZE);
		
		purchasesDetailsService.update(purchasesDetailsDto);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable BigInteger id, @RequestBody PurchasesDetailsDto purchasesDetailsDto) {
		
		purchasesDetailsDto.setId(id);
		purchasesDetailsDto.setUpdateType(DELETE);
		purchasesDetailsDto.setOperations(SUBTRACT);
		
		purchasesDetailsService.update(purchasesDetailsDto);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity <List<PurchasesDetailsDto>> getAllPurchasesDetails(){
		
		return status(HttpStatus.OK).body(purchasesDetailsService.getAllPurchasesDetails());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity <PurchasesDetailsDto> getPurchasesDetailsById(@PathVariable BigInteger id){
		
		return status(HttpStatus.OK).body(purchasesDetailsService.getPurchasesDetailsbyId(id));
	}
}