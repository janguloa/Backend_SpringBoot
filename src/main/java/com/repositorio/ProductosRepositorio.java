package com.repositorio;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.modelo.Productos;

@Repository
public interface ProductosRepositorio extends JpaRepository <Productos, Long> {
	
	Optional <Productos> findByCodproducto (String codproducto);
	
	//List<ProductosDto> findAllByEstado(String estado);
	
	//List<ProductosDto> findAllByCodproducto(String codigo);
}