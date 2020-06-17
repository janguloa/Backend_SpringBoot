package com.modelo;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="productos")
public class Productos {
	
	@Id
	@Column(name="codproducto")
	private String codproducto;
	
	@NotNull
	@Column(name="descripcion")
	private String descripcion;
	
	@NotNull
	@Column(name="estado")
	private String estado;
	
	@Column(name="fecha_modificacion")
	private LocalDate fecha_modificacion;
	
	@Column(name="fecha_registro")
	private LocalDate fecha_registro;
	
	@Column(name="usuario_registro")
	private String usuario_registro;
	
	@Column(name="usuario_modifico")
	private String usuario_modifico;
	
	@Column(name="cod_empresa")
	private String cod_empresa;
	
	public Productos() {
		
	}
	
	public Productos(String codproducto, @NotNull String descripcion, @NotNull String estado,
			LocalDate fecha_modificacion, LocalDate fecha_registro, String usuario_registro, String usuario_modifico,
			String cod_empresa) {
		this.codproducto = codproducto;
		this.descripcion = descripcion;
		this.estado = estado;
		this.fecha_modificacion = fecha_modificacion;
		this.fecha_registro = fecha_registro;
		this.usuario_registro = usuario_registro;
		this.usuario_modifico = usuario_modifico;
		this.cod_empresa = cod_empresa;
	}

	public Productos(String codproducto) {
		this.codproducto = codproducto;
	}

	public String getCodproducto() {
		return codproducto;
	}
	public void setCodproducto(String codproducto) {
		this.codproducto = codproducto;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public LocalDate getFecha_modificacion() {
		return fecha_modificacion;
	}
	public void setFecha_modificacion(LocalDate fecha_modificacion) {
		this.fecha_modificacion = fecha_modificacion;
	}
	public LocalDate getFecha_registro() {
		return fecha_registro;
	}
	public void setFecha_registro(LocalDate fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	public String getUsuario_registro() {
		return usuario_registro;
	}
	public void setUsuario_registro(String usuario_registro) {
		this.usuario_registro = usuario_registro;
	}
	public String getUsuario_modifico() {
		return usuario_modifico;
	}
	public void setUsuario_modifico(String usuario_modifico) {
		this.usuario_modifico = usuario_modifico;
	}

	public String getCod_empresa() {
		return cod_empresa;
	}

	public void setCod_empresa(String cod_empresa) {
		this.cod_empresa = cod_empresa;
	}
}