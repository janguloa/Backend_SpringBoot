package com.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductosDto {
	
	private Long id;
	private String codproducto;
	private String descripcion;
	private String estado;
	private String cantProductos;
	
}