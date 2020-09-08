package com.controller;

import static org.springframework.http.HttpStatus.OK;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.RegisterRequest;
import com.service.AuthService;
import com.service.RefreshTokenService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth/")
@AllArgsConstructor
public class AuthController {
	
	private final AuthService authService;
	private final RefreshTokenService refreshTokenService;
	
	@PostMapping("/signup")
	public ResponseEntity signup(@RequestBody RegisterRequest registerRequest) {
		
		authService.signup(registerRequest);
		return new ResponseEntity("Se ha registrado el usuario satisfactoriamente", OK);
	}
	
}