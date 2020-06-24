package com.servicio;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modelo.Productos;
import com.modelo.ProductosInventario;
import com.repositorio.ProductosInventarioRepositorio;

@Service("productoInventario")
@Transactional
public class ProductosInventarioServicioImpl implements ProductosInventarioServicio {
	
	@Autowired
	private ProductosInventarioRepositorio productosInventarioRepositorio;
	
	@Override
	public List<ProductosInventario> findAllActives() {
		
		List <ProductosInventario> lista = productosInventarioRepositorio.findAllByEstado("0");
		
		return lista;
	}

	@Override
	public List<ProductosInventario> findByCodigo() {
		
	//	List <ProductosInventario> lista = productosInventarioRepositorio.findAllByCodproducto(new Productos("CONSOL1"));
		
		return null;
	}

	@Override
	public ProductosInventario CreateRegistry(ProductosInventario productoInventario) {

		return productosInventarioRepositorio.save(productoInventario);
		
	}

	@Override
	public ProductosInventario UpdateRegistry(ProductosInventario productoInventario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRegistry(Long codigo) {
		// TODO Auto-generated method stub

	}
}