package com.repositorio;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.modelo.Usuarios;

@Repository
public interface UsuarioRepositorio extends CrudRepository<Usuarios, Long> {
	
	Optional <Usuarios> findByUsuario(String usuario);
	
	//@Query("select 'E' from usuarios where usuario = :user")
	//List<Usuarios> findByUsuario(@Param("user") String usuario);
	
}