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
	private final AuthService authService;
	
	@Transactional
	public void updateCompany(Long id) {
			
		Optional<Company> company = companyRepository.findById(id);
		company.orElseThrow(() -> new SpringInventoryException("Compañia no encontrada con el código" + id));
			
		Users userFound = authService.getCurrentUser();
		
		fetchUserAndCompany(userFound, company.get());		
	}
	
	private void fetchUserAndCompany(Users userFound, Company company) {
		
		Users users = usersRepository.findById(userFound.getUserId())
				.orElseThrow(() -> new SpringInventoryException("Usuario no encontrado con el codigo" + userFound.getUserId()));
		users.setCompany(company);
		usersRepository.save(users);	
	}
}