package com.servicio;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.dto.ProductosDto;
import com.exceptions.SubredditNotFoundException;
import com.modelo.Productos;
import com.repositorio.ProductosRepositorio;
import lombok.AllArgsConstructor;
import static java.time.Instant.now;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class ProductosServicioImpl implements ProductosServicio {
	
	private ProductosRepositorio productosRepositorio;
	private AuthServicio authServicio;
	
	@Override
	@Transactional(readOnly = true)
	public List<ProductosDto> getAllActives() {
		
		return productosRepositorio.findAll()
				.stream()
				.map(this::mapToDto)
				.collect(toList());
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public ProductosDto getProductos(Long id) {
		Productos productos = productosRepositorio.findById(id)
				.orElseThrow(() -> new SubredditNotFoundException("Subreddit not found with id -" + id));
	    return mapToDto(productos);
	}
	
	@Transactional
	@Override
	public ProductosDto save(ProductosDto productosDto) {
		
		Productos productos = productosRepositorio.save(mapToProductos(productosDto));
		productosDto.setId(productos.getId());
		
		return productosDto;
	}
	
	
	//Enlazar el Dto con el Model, para consultar los productos
	
	private ProductosDto mapToDto(Productos productos) {
        return ProductosDto.builder().codproducto(productos.getCodproducto())
                .id(productos.getId())
                .descripcion(productos.getDescripcion())
                .estado(productos.getEstado())
                .build();
    }
	
	//Enlazar el Dto con el Model, para consultar crear los productos
	
	private Productos mapToProductos(ProductosDto productosDto) {
        return Productos.builder().codproducto(productosDto.getCodproducto())
        		.descripcion(productosDto.getDescripcion())
        		.estado(productosDto.getEstado())
        		.usuario_registro(authServicio.getCurrentUser())
        		.fecha_registro(now()).build();
        	
    }
	
	/*
	@Override
	public Productos UpdateUser(Productos productos) {

		Optional <Productos> productosDb = this.productosRepositorio.findById(productos.getCodproducto());
		
			if (productosDb.isPresent()) {
				
				Productos productoUpdate = productosDb.get();
				productoUpdate.setDescripcion(productos.getDescripcion());
				productoUpdate.setEstado(productos.getEstado());
				productoUpdate.setUsuario_modifico(productos.getUsuario_modifico());
				productoUpdate.setFecha_registro(productos.getFecha_registro());
				
				productosRepositorio.save(productoUpdate);
				
				return productoUpdate;
								
			}
			
			else {
				
				throw new ResourceNotFoundException("Record not found with id : " + productos.getCodproducto());
			}
	}

	@Override
	public void DeleteUser(String codigo) {

		Optional <Productos> productoDb = this.productosRepositorio.findById(codigo);
		
		if (productoDb.isPresent()) {
			
			this.productosRepositorio.delete(productoDb.get());
			
		} else {
			
			throw new ResourceNotFoundException("Record not foud with id: " + codigo);
			
		}
	}*/
}