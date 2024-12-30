package service;

import java.util.List;

import model.Producto;

public interface ProductosService {
	List<Producto> catalogo();
	List<Producto> productosCategoria(String categoria);
	Producto productoCodigo(int cod);
	void altaProducto(Producto producto);
	Producto eliminarProducto(int cod);
	Producto actualizarPrecio(int cod, double precio);
}
