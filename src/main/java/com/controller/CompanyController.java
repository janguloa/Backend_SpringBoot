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
	public CompanyDto register(@RequestBody CompanyDto companyRequest) {
		
		return companyService.save(companyRequest);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity update(@PathVariable Long id, @RequestBody CompanyDto companyDto) {
		
		companyDto.setId(id);
		companyDto.setUpdateType(UPDATE);
		
		return status(HttpStatus.OK).body(companyService.update(companyDto));
	
	}
	
	@PostMapping("/delete/{id}")
	public CompanyDto delete(@PathVariable Long id, @RequestBody CompanyDto companyDto) {
		
		companyDto.setId(id);
		companyDto.setUpdateType(DELETE);
		return companyService.update(companyDto);
		
	}
}