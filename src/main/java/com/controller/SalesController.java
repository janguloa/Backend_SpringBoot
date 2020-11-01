package com.controller;

import static org.springframework.http.ResponseEntity.status;

import java.math.BigInteger;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.SalesDto;
import com.service.SalesServices;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/auth/sales/")
@AllArgsConstructor
public class SalesController {
	
	private final SalesServices salesServices;
	
	@PostMapping("/create")
	public ResponseEntity<Void> create(@RequestBody SalesDto salesDto) {
		
		//Llamar a servicio
		
		salesServices.create(salesDto);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/cancel/{id}")
	public ResponseEntity<Void> cancel(@PathVariable BigInteger id, @RequestBody SalesDto salesDto){
		
		salesDto.setId(id);
		salesServices.cancel(salesDto);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity <List<SalesDto>> getAllSales(){
		
		return status(HttpStatus.OK).body(salesServices.getAllSales());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity <SalesDto> getSalesbyId(@PathVariable BigInteger id){
		
		return status(HttpStatus.OK).body(salesServices.getSalesById(id));
	}
}