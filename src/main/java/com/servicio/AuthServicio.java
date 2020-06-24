package com.servicio;

import com.dto.AuthenticationResponse;
import com.dto.LoginRequest;
import com.dto.RegisterRequest;
import com.modelo.Usuarios;

public interface AuthServicio {
	
	public void signup (RegisterRequest registerRequest);
	
	public void verifyAccount(String token);
	
	public AuthenticationResponse login (LoginRequest loginRequest);
	
	public Usuarios getCurrentUser();
	
}
