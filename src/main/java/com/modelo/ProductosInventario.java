package com.modelo;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
	@ManyToOne(fetch = LAZY)
	private Usuarios usuario_registro;
	
	@Column(name="usuario_modifico")
	@ManyToOne(fetch = LAZY)
	private Usuarios usuario_modifico;
	
	@Column(name="cod_empresa")
	private String cod_empresa;
}