package com.controlador;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.modelo.Usuarios;
import com.servicio.UsuariosServicio;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class UsuariosControlador {
	
	@Autowired
	private UsuariosServicio usuarioServicio;
	
	@PostMapping("/usuario")
	public Usuarios login (@RequestParam("user") String nombusuario, @RequestParam("password") String contrasena) {
		
		Usuarios usuario = new Usuarios();
	//	usuario.setUsuario(nombusuario);
	//	usuario.setContrasena(contrasena);
		
		return usuario;
		
	}
}