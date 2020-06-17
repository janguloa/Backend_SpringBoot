package com.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.modelo.Productos;
import com.modelo.ResourceNotFoundException;
import com.repositorio.ProductosRepositorio;

@Service("productosServicio")
@Transactional
public class ProductosServicioImpl implements ProductosServicio {
	
	@Autowired
	private ProductosRepositorio productosRepositorio;

	@Override
	public List<Productos> findAllActives() {
		
		List<Productos> lis = productosRepositorio.findAllByEstado("0");
		
		return lis;	
	}

	@Override
	public List<Productos> findCodigo() {

		List<Productos> lis = productosRepositorio.findAllByCodproducto("CONSOL1");
		
		return lis;
	}

	@Override
	public Productos CreateUser(Productos productos) {

		return productosRepositorio.save(productos);
		
	}

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
	}
}