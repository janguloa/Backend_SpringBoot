package com.dto; // Objeto de Transferencia de Data

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
	
	private String usuario;
	private String correo;
	private String contrasena;
	private String estado;
	private String cod_cia;

}