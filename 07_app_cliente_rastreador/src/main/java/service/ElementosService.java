package service;

import model.Elemento;
import reactor.core.publisher.Flux;

public interface ElementosService {
	Flux<Elemento> elementosPorPrecio(double precioMax);
}
