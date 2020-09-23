package com.service;

import java.time.Instant;
import static com.model.UpdateType.UPDATE;
import javax.transaction.Transactional;
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
}