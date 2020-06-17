package com.servicio;

import java.util.List;

import com.modelo.Usuarios;

public interface UsuariosServicio {
	
	List<Usuarios> findUser(Usuarios usuarios);

}