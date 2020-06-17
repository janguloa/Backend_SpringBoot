package com.modelo;

import static javax.persistence.GenerationType.SEQUENCE;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="productosinventario")
public class ProductosInventario {
	
	@Id
	@Column(name="id")
	@SequenceGenerator(name = "PRODUC_SEQ", sequenceName = "PRODUC_SEQ", initialValue = 1, allocationSize=100)
	@GeneratedValue(strategy = SEQUENCE, generator = "PRODUC_SEQ")
	private Long id;
	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codproducto")
	private Productos codproducto;
	
	@NotNull
	@Column(name="cantidad")
	private int cantidad;
	
	@NotNull
	@Column(name="total_stock")
	private int total_stock;
	
	@NotNull
	@Column(name="precio_venta")
	private Double precio_venta;
	
	@NotNull
	@Column(name="costo")
	private Double costo;
	
	@NotNull
	@Column(name="estado")
	private String estado;
	
	@Column(name="total_defectuosos")
	private int total_defectuosos;
	
	@Column(name="porcentaje_descuento")
	private Double porcentaje_descuento;
	
	@Column(name="fecha_modificacion")
	private LocalDateTime fecha_modificacion;
	
	@Column(name="fecha_registro")
	private LocalDateTime fecha_registro;
	
	@Column(name="usuario_registro")
	private String usuario_registro;
	
	@Column(name="usuario_modifico")
	private String usuario_modifico;
	
	@Column(name="cod_empresa")
	private String cod_empresa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getTotal_stock() {
		return total_stock;
	}

	public void setTotal_stock(int total_stock) {
		this.total_stock = total_stock;
	}

	public Double getPrecio_venta() {
		return precio_venta;
	}

	public void setPrecio_venta(Double precio_venta) {
		this.precio_venta = precio_venta;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getTotal_defectuosos() {
		return total_defectuosos;
	}

	public void setTotal_defectuosos(int total_defectuosos) {
		this.total_defectuosos = total_defectuosos;
	}

	public LocalDateTime getFecha_modificacion() {
		return fecha_modificacion;
	}

	public void setFecha_modificacion(LocalDateTime fecha_modificacion) {
		this.fecha_modificacion = fecha_modificacion;
	}

	public LocalDateTime getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(LocalDateTime fecha_registro) {
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

	public Productos getProducto() {
		return codproducto;
	}

	public void setProducto(Productos codproducto) {
		this.codproducto = codproducto;
	}

	public Double getPorcentaje_descuento() {
		return porcentaje_descuento;
	}

	public void setPorcentaje_descuento(Double porcentaje_descuento) {
		this.porcentaje_descuento = porcentaje_descuento;
	}

	public String getCod_empresa() {
		return cod_empresa;
	}

	public void setCod_empresa(String cod_empresa) {
		this.cod_empresa = cod_empresa;
	}
}