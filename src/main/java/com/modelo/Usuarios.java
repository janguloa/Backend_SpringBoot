package com.modelo;

import static javax.persistence.GenerationType.SEQUENCE;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Usuarios {
	
	@Id
	@Column(name="id")
	@SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_SEQ", initialValue = 1, allocationSize=100)
	@GeneratedValue(strategy = SEQUENCE, generator = "USER_SEQ")
	private Long id;
	
	@Column(name="cod_cia") // Hacer relación
	@NotNull
	private String cod_cia;
	
	@Column(name="usuario")
	@NotNull
	private String usuario;
	
	@Column(name="contrasena")
	@NotNull
	private String contrasena;
	
	@Column(name="correo")
	@NotNull
	private String correo;
	
	@Column(name="estado") // Hacer relación
	@NotNull
	private String estado;
	
	@Column(name="nombres")
	private String nombres;
	
	@Column(name="fecha_nacimiento")
	private LocalDate fecha_nacimiento;
	
	@Column(name="fecha_registro")
	private LocalDateTime fecha_registro;
	
	@Column(name="fecha_modificacion")
	private LocalDateTime fecha_modificacion;
	
	@Column(name="usuario_registro")
	private String usuario_registro;
	
	@Column(name="usuario_modifico")
	private String usuario_modifico;
	
	@Column(name="rol") // Cambiar luego por la clase ROL
	private String rol;
	
	@Column(name="imagen_url")
	private String imagen_url;
	
	private String token;
	
	private Instant created;
	
	private boolean enabled;
}