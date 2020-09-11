package com.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dto.CompanyDto;
import com.exceptions.SpringInventoryException;
import com.model.Company;
import com.repository.CompanyRepository;
import static com.model.UpdateType.DELETE;
import static com.model.UpdateType.UPDATE;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
// @Transactional(readOnly = false, rollbackFor = Exception.class)

public class CompanyService {
	
	private final CompanyRepository companyRepository;
	
	@Transactional
	public CompanyDto save(CompanyDto companyDto) {
		
		Company save = companyRepository.save(mapToCompany(companyDto));
		companyDto.setId(save.getId());
		
		return companyDto;		
	}
	
	@Transactional
	public CompanyDto update(CompanyDto companyDto) {
		
		Optional <Company> companyDb = companyRepository.findById(companyDto.getId());
		
		if(companyDb.isPresent()) {
			
			Company companyUpdate = companyDb.get();
			companyUpdate.setId(companyDto.getId());
			
			if(UPDATE.equals(companyDto.getUpdateType())) {
				
				companyUpdate.setDescription(companyDto.getDescription().toUpperCase());
				companyUpdate.setEnabled(true);
				
			}
			else {
				companyUpdate.setEnabled(false);
				
			}
			
			companyRepository.save(companyUpdate);
		}	
		else {
			
			new SpringInventoryException("La compañia no existe con el siguiente nombre" + companyDto.getDescription());
			
		}
		return companyDto;
	}
	
	private Company mapToCompany(CompanyDto companyDto) {
		
		return Company.builder()
				.description(companyDto.getDescription().toUpperCase())
				.enabled(true)
				.build();
	}
}