package com.controlador;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dto.ProductosDto;
import com.modelo.Productos;
import com.servicio.ProductosServicio;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/api/productos")
public class ProductosControlador {
	
	private ProductosServicio productosServicio;
	
	@GetMapping
	public List<ProductosDto> listAllProductos(){
		
		return productosServicio.getAllActives();
	}
	
	@GetMapping("/{id}")
	public ProductosDto getProductos(@PathVariable Long id){
		
		return productosServicio.getProductos(id);
	}
	
	@PostMapping
	public ProductosDto crearProducto(@RequestBody @Valid ProductosDto productosDto) {
		
		return productosServicio.save(productosDto);
		
	}
	
/*	@PutMapping("/productos/{codigo}")
	public ResponseEntity <Productos> updateProducto(@PathVariable String codigo, @RequestBody Productos productos){
		
		productos.setCodproducto(codigo);
		return ResponseEntity.ok().body(this.productosServicio.UpdateUser(productos));
	}
	
	@DeleteMapping("/productos/{codigo}")
	public ResponseEntity <String> deleteProducto(@PathVariable String codigo){
		
		this.productosServicio.DeleteUser(codigo);
		
		return new ResponseEntity<>(codigo, HttpStatus.OK);
		
	}*/
	
	
}