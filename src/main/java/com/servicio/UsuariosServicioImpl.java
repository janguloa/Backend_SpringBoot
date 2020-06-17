package com.servicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.modelo.Usuarios;
import com.repositorio.UsuarioRepositorio;

@Service("servicioUsuario")
@Transactional
public class UsuariosServicioImpl implements UsuariosServicio {
	 
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Override
	public List<Usuarios> findUser(Usuarios usuarios) {
		
	//	List<Usuarios> lista = usuarioRepositorio.findByUsuario(usuarios.getUsuario());
		
		return null;

	}
}