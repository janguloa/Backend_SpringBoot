package com.servicio;

import java.util.Collection;
import java.util.Optional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.modelo.Usuarios;
import com.repositorio.UsuarioRepositorio;
import static java.util.Collections.singletonList;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private final UsuarioRepositorio usuarioRepositorio;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Usuarios> usuariosOptional = usuarioRepositorio.findByUsuario(username);
		
		Usuarios usuarios = usuariosOptional.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado "
				+ " con nombre de usuario " + username));
		
		return new org.springframework.security.core.userdetails.User(	usuarios.getUsuario(), 
																		usuarios.getContrasena(), 
																		usuarios.isEnabled(),true,true,
																		true, getAuthorities("USER"));
	}
	
	private Collection<? extends GrantedAuthority> getAuthorities (String role){
		return singletonList(new SimpleGrantedAuthority(role));
	}

}