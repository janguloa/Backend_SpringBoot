package com.modelo;

import static javax.persistence.GenerationType.IDENTITY;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import static javax.persistence.FetchType.LAZY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="productos")
public class Productos {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;
	
	@NotBlank(message = "El código del producto es requerido")
	@Column(name="codproducto")
	private String codproducto;
	
	@NotBlank(message = "La descripción es requerido")
	@Column(name="descripcion")
	private String descripcion;
	
	@NotBlank(message = "El estado es requerido")
	@Column(name="estado")
	private String estado;
	
	@Column(name="fecha_modificacion")
	private Instant fecha_modificacion;
	
	@Column(name="fecha_registro")
	private Instant fecha_registro;
	
	@ManyToOne(fetch = LAZY)
	private Usuarios usuario_registro;
	
	@ManyToOne(fetch = LAZY)
	private Usuarios usuario_modifico;
	
	@Column(name="cod_empresa")
	private String cod_empresa;
}