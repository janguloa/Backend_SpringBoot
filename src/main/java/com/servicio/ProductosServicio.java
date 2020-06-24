package com.servicio;

import java.util.List;
import com.dto.ProductosDto;

public interface ProductosServicio {
	
	List<ProductosDto> getAllActives();
	
	public ProductosDto getProductos(Long id);
	
	ProductosDto save (ProductosDto productosDto);
	
/*	ProductosDto UpdateUser (ProductosDto ProductosDto);
	
	void DeleteUser (String codigo); */

}
