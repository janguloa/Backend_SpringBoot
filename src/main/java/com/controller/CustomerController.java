package com.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.CustomerDto;
import com.service.CustomerService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/auth/customer/")
@AllArgsConstructor
public class CustomerController {
	
	private final CustomerService customerService;
	
	@PostMapping("/create")
	public ResponseEntity <Void> register(@RequestBody CustomerDto customerDto) {
		
		customerService.save(customerDto);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/update/{id}")
	public ResponseEntity <Void> update(@PathVariable BigInteger id, @RequestBody CustomerDto customerDto) {
		
		customerDto.setId(id);
		customerDto.setUpdateType(UPDATE);
		
		customerService.update(customerDto);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/delete/{id}")
	public ResponseEntity <Void> delete(@PathVariable BigInteger id, @RequestBody CustomerDto customerDto) {
		
		customerDto.setId(id);
		customerDto.setUpdateType(DELETE);
		
		customerService.update(customerDto);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity <List<CustomerDto>> getAllCustomer(){
		
		return status(HttpStatus.OK).body(customerService.getAllCustomer());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity <CustomerDto> getCustomerId(@PathVariable BigInteger id){
		
		return status(HttpStatus.OK).body(customerService.getCompanyId(id));
		
	}
}