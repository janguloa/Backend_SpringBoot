package com.service;

import static com.model.UpdateType.UPDATE;

import java.time.Instant;
import java.util.List;

import javax.transaction.Transactional;
import static java.util.stream.Collectors.toList;

import java.math.BigInteger;

import org.springframework.stereotype.Service;

import com.dto.CustomerDto;
import com.exceptions.SpringInventoryException;
import com.model.Customer;
import com.repository.CustomerRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerService {
	
	private final CustomerRepository customerRepository;
	
	@Transactional
	public CustomerDto save (CustomerDto customerDto) {
		
		customerRepository.save(maptoCustomer(customerDto));
		
		return customerDto;
	}
	
	@Transactional
	public CustomerDto update (CustomerDto customerDto) {
		
		fetchCustomerandEnable(customerDto);
		
		return customerDto;
	}
	
	public List<CustomerDto> getAllCustomer() {
		
		return customerRepository.findAll().stream().map(this::mapToDto).collect(toList());
	}
	
	public CustomerDto getCompanyId(BigInteger id) {
		
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new SpringInventoryException("El cliente no fue encontrado con el siguiente codigo " + id));
		
		return mapToDto(customer);
	}

	private Customer maptoCustomer(CustomerDto customerDto) {
		
		return Customer.builder()
				.customerIdent(customerDto.getCustomerIdent())
				.names(customerDto.getNames())
				.email(customerDto.getEmail())
				.telephone(customerDto.getTelephone())
				.createdDate(Instant.now())
				.enabled(true)
				.build();
	}
	
	private void fetchCustomerandEnable (CustomerDto customerDto) {
		
		Customer customer = customerRepository.findById(customerDto.getId())
				.orElseThrow(() -> new SpringInventoryException("El cliente no fue encontrado con el siguiente codigo " + customerDto.getId()));
		
		if(UPDATE.equals(customerDto.getUpdateType())) {
			
			customer.setNames(customerDto.getNames());
			customer.setEmail(customerDto.getEmail());
			customer.setTelephone(customerDto.getTelephone());
		}
		else {
			customer.setEnabled(false);
		}
		
		customerRepository.save(customer);
	}
	
	private CustomerDto mapToDto(Customer customer) {
		
		return CustomerDto.builder()
				.id(customer.getCustomerId())
				.customerIdent(customer.getCustomerIdent())
				.names(customer.getNames())
				.email(customer.getEmail())
				.telephone(customer.getTelephone())
				.build();
	}
}