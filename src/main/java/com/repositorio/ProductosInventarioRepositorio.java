package com.repositorio;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.modelo.Productos;
import com.modelo.ProductosInventario;

@Repository
public interface ProductosInventarioRepositorio extends CrudRepository<ProductosInventario, Long> {

	List <ProductosInventario> findAllByCodproducto(Productos codproducto);
	
	List <ProductosInventario> findAllByEstado(String estado);
	
}