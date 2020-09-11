package com.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.dto.UsersDto;
import com.exceptions.SpringInventoryException;
import com.model.Company;
import com.model.Users;
import com.repository.CompanyRepository;
import com.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class UsersService {
	
	private final UserRepository usersRepository;
	private final CompanyRepository companyRepository;
	
	@Transactional
	public UsersDto updateCompany(UsersDto usersDto, Long id) {
			
		Optional<Company> company = companyRepository.findById(usersDto.getId_company());
		company.orElseThrow(() -> new SpringInventoryException("Compañia no encontrada con el código" + id));
			
		usersDto.setId(id);
		fetchUserAndCompany(usersDto, company.get());
				
		return usersDto;
	}
	
	private void fetchUserAndCompany(UsersDto usersDto, Company company) {
		
		Users users = usersRepository.findById(usersDto.getId())
				.orElseThrow(() -> new SpringInventoryException("Usuario no encontrado con el codigo" + usersDto.getId()));
		users.setCompany(company);
		usersRepository.save(users);
		
	}
}