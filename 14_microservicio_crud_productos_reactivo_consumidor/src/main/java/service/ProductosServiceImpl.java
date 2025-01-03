package service;

import java.time.Duration;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import model.Pedido;
import model.Producto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import repository.ProductosRepository;
@Service
@RequiredArgsConstructor
@Log4j2
public class ProductosServiceImpl implements ProductosService {
	
	private final ProductosRepository productosRepository;
	@Override
	public Flux<Producto> catalogo() {
		return productosRepository.findAll()//Flux<Producto>
				.delayElements(Duration.ofMillis(500));//Flux<Producto>
	}

	@Override
	public Flux<Producto> productosCategoria(String categoria) {
		return productosRepository.findByCategoria(categoria);
	}

	@Override
	public Mono<Producto> productoCodigo(int cod) {
		return productosRepository.findById(cod);
	}
	@Override
	public Mono<Void> altaProducto(Producto producto) {
		return productoCodigo(producto.getCodProducto())//Mono<Producto>
				.switchIfEmpty(Mono.just(producto).flatMap(p->productosRepository.save(p)))//Mono<Producto>
				.then();//Mono<Void>
	}

	@Override
	public Mono<Producto> eliminarProducto(int cod) {
		return productoCodigo(cod) //Mono<Producto>
				.flatMap(p->productosRepository.deleteById(cod) //Mono<Void>
						.then(Mono.just(p))//Mono<Producto>
				);//Mono<Producto>
				
	}

	@Override
	public Mono<Producto> actualizarPrecio(int cod, double precio) {
		return productoCodigo(cod) //Mono<Producto>
				.flatMap(p->{
					p.setPrecioUnitario(precio);
					return productosRepository.save(p);//Mono<Producto>
				});//Mono<Producto>
	}
	
	@KafkaListener(topics = "pedidosTopic",groupId = "myGroup1")
	public void gestionPedido(Pedido pedido) {
		productoCodigo(pedido.getCodProducto()) //Mono<Producto>
		.flatMap(pr->{
			int newStok = pr.getStock()-pedido.getUnidades();
			pr.setStock(newStok);
			return productosRepository.save(pr); //Mono<Producto>
		})//Mono<Producto>
		.map(pr -> {
				log.info("Se ha gestionado el pedido " + pedido.getUnidades() + " de " + pr.getNombre()
				+ " en el tópico pedidosTopic");					
				return pr;
		})// Mono<Product>
		.flatMap(pr->{					
					log.info("El producto " + pr.getNombre() + " tiene unidades en stock: " + pr.getStock());					
					return Mono.just(pr);
		})//Mono<Producto>
		.subscribe();
	}
}
