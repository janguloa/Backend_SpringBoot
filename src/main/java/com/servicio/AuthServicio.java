package com.servicio;

import com.dto.AuthenticationResponse;
import com.dto.LoginRequest;
import com.dto.RegisterRequest;

public interface AuthServicio {
	
	public void signup (RegisterRequest registerRequest);
	
	public void verifyAccount(String token);
	
	public AuthenticationResponse login (LoginRequest loginRequest);
	
}
