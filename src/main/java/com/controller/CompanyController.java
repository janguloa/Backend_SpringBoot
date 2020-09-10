package com.controller;

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
	public CompanyDto update(@PathVariable Long id, @RequestBody CompanyDto companyDto) {
		
		companyDto.setId(id);
		return companyService.update(companyDto);
	}
}