package com.service;

import static com.model.UpdateType.UPDATE;
import static java.util.stream.Collectors.toList;

import java.math.BigInteger;
import java.time.Instant;
import java.util.List;

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
public class CompanyService {
	
	private final CompanyRepository companyRepository;
	private final UsersService usersService;
	
	
	@Transactional
	public CompanyDto save(CompanyDto companyDto) {
		
		Company save = companyRepository.save(mapToCompany(companyDto));
		companyDto.setId(save.getCompanyId());
		
		usersService.updateCompany(companyDto.getId());
		
		return companyDto;		
	}
	
	@Transactional
	public CompanyDto update(CompanyDto companyDto) {
		
		fetchCompanyAndEnable(companyDto);
		
		return companyDto;
	}
	
	@Transactional
	private void fetchCompanyAndEnable (CompanyDto companyDto) {
		
		Company company = companyRepository.findById(companyDto.getId())
				.orElseThrow(() -> new SpringInventoryException("Compañia no encontrada con el código" + companyDto.getId()));
		
		if(UPDATE.equals(companyDto.getUpdateType())) {
			
			company.setDescription(companyDto.getDescription().toUpperCase());
			company.setEnabled(true);
		} else {
			
			company.setEnabled(false);
		}
		companyRepository.save(company);
	}
	
	public List<CompanyDto> getAllCompany(){
		
		return companyRepository.findAll().stream().map(this::mapToDto).collect(toList());
	}
	
	public CompanyDto getPost(BigInteger id) {
		
		Company company = companyRepository.findById(id)
				.orElseThrow(() -> new SpringInventoryException("Compañia no encontrada con el código" + id));
		
		return mapToDto(company);
	}
	
	private CompanyDto mapToDto(Company company) {
		
		return CompanyDto.builder()
				.id(company.getCompanyId())
				.description(company.getDescription())
				.build();
	}
	
	private Company mapToCompany(CompanyDto companyDto) {
		
		return Company.builder()
				.description(companyDto.getDescription().toUpperCase())
				.createdate(Instant.now())
				.enabled(true)
				.build();
	}
}