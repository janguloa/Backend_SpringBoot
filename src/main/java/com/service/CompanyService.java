package com.service;

import static com.model.UpdateType.UPDATE;

import java.time.Instant;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dto.CompanyDto;
import com.exceptions.SpringInventoryException;
import com.model.Company;
import com.repository.CompanyRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
// @Transactional(readOnly = false, rollbackFor = Exception.class)
public class CompanyService {
	
	private final CompanyRepository companyRepository;
	private final AuthService authService;
	
	@Transactional
	public CompanyDto save(CompanyDto companyDto) {
		
		Company save = companyRepository.save(mapToCompany(companyDto));
		companyDto.setId(save.getId());
		
		return companyDto;		
	}
	
	@Transactional
	public CompanyDto update(CompanyDto companyDto) {
		
		fetchCompanyAndEnable(companyDto.getId(), companyDto);
		
		return companyDto;
	}
	
	@Transactional
	private void fetchCompanyAndEnable(Long id, CompanyDto companyDto) {
		
		Company company = companyRepository.findById(id)
				.orElseThrow(() -> new SpringInventoryException("Compañia no encontrada con el código" + id));
		
		if(UPDATE.equals(companyDto.getUpdateType())) {
			
			company.setDescription(companyDto.getDescription().toUpperCase());
			company.setEnabled(true);
		} else {
			
			company.setEnabled(false);
		}
		companyRepository.save(company);
	}
	
	private Company mapToCompany(CompanyDto companyDto) {
		
		return Company.builder()
				.description(companyDto.getDescription().toUpperCase())
				.createdate(Instant.now())
				.users(authService.getCurrentUser())
				.enabled(true)
				.build();
	}
}