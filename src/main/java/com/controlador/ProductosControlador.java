package com.controlador;

import java.util.List;
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
import com.modelo.Productos;
import com.servicio.ProductosServicio;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/")
public class ProductosControlador {
	
	@Autowired
	private ProductosServicio productosServicio;
	
	@RequestMapping("/productos")
	@GetMapping
	public List<Productos> listAllProductos(){
		
		return productosServicio.findAllActives();
	}
	
	@RequestMapping("/productoscodigo")
	@GetMapping
	public List<Productos> listCodigoProducto(){
		
		return productosServicio.findCodigo();
	}
	
	@PostMapping("/crearproducto")
	public ResponseEntity <Productos> crearProducto(@RequestBody Productos productos) {
		
		return ResponseEntity.ok().body(this.productosServicio.CreateUser(productos));
		
	}
	
	@PutMapping("/productos/{codigo}")
	public ResponseEntity <Productos> updateProducto(@PathVariable String codigo, @RequestBody Productos productos){
		
		productos.setCodproducto(codigo);
		return ResponseEntity.ok().body(this.productosServicio.UpdateUser(productos));
	}
	
	@DeleteMapping("/productos/{codigo}")
	public ResponseEntity <String> deleteProducto(@PathVariable String codigo){
		
		this.productosServicio.DeleteUser(codigo);
		
		return new ResponseEntity<>(codigo, HttpStatus.OK);
		
	}
	
	
}