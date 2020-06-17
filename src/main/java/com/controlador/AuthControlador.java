package com.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.HttpStatus.OK;

import com.dto.AuthenticationResponse;
import com.dto.LoginRequest;
import com.dto.RegisterRequest;
import com.servicio.AuthServicio;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthControlador {
	
	private final AuthServicio authServicio;
	
	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
		
		authServicio.signup(registerRequest);
		return new ResponseEntity<>("Usuario registrado satisfactoriamente",OK);
	}
	
	@PostMapping("/login")
	public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
		return authServicio.login(loginRequest);
	}
	
	@GetMapping("accountVerification/{token}")
	public ResponseEntity<String> verifyAccount(@PathVariable String token){
		authServicio.verifyAccount(token);
		return new ResponseEntity<>("Cuenta activada satisfactoriamente", OK);
	}
	
}