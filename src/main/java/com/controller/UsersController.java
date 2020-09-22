package com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.UsersDto;
import com.service.UsersService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/auth/users")
@AllArgsConstructor
public class UsersController {
	
	private final UsersService usersService;
	
//	@PostMapping("/updateCompany/{id}")
//	public ResponseEntity<Void> updateCompany (@PathVariable Long id, @RequestBody UsersDto usersDto) {
//		
//		usersService.updateCompany(usersDto, id);
//		
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
	
}