package com.controller;

import static com.model.UpdateType.DELETE;
import static com.model.UpdateType.UPDATE;
import static org.springframework.http.ResponseEntity.status;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.CompanyDto;
import com.service.CompanyService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/auth/company/")
@AllArgsConstructor
public class CompanyController {
	
	private final CompanyService companyService;
	
	@PostMapping("/create")
	public ResponseEntity<Void> register(@RequestBody CompanyDto companyRequest) {
		
		companyService.save(companyRequest);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody CompanyDto companyDto) {
		
		companyDto.setId(id);
		companyDto.setUpdateType(UPDATE);
		companyService.update(companyDto);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PostMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id, @RequestBody CompanyDto companyDto) {
		
		companyDto.setId(id);
		companyDto.setUpdateType(DELETE);
		companyService.update(companyDto);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}