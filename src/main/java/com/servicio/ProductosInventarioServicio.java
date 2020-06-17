package com.servicio;

import java.util.List;

import com.modelo.ProductosInventario;

public interface ProductosInventarioServicio {
	
	List <ProductosInventario> findAllActives();
	
	List <ProductosInventario> findByCodigo();
	
	ProductosInventario CreateRegistry (ProductosInventario productoInventario);
	
	ProductosInventario UpdateRegistry (ProductosInventario productoInventario);
	
	void deleteRegistry (Long codigo);

}