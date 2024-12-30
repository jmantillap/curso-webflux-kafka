package runners;

import org.springframework.boot.CommandLineRunner;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import model.Producto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class TestRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		WebClient client=WebClient.create("http://localhost:8000");
		/*
		Flux<Producto> flux=client
		.get()
		.uri("/productos")
		.accept(MediaType.APPLICATION_JSON)
		.retrieve()
		.bodyToFlux(Producto.class);
		flux.subscribe(p->System.out.println(p));
		*/
		
		/*
		Producto pro=new Producto(400,"prueba3","categoria test",5.0,20,true);		
		client
		.post()
		.uri("/alta")
		.body(Mono.just(pro), Producto.class)
		.retrieve()
		.bodyToMono(Void.class)
		.doOnTerminate(()->System.out.println("Se ha dado de alta el producto, o no"))
		.block();
		*/
		
		/*
		Mono<Producto> monoFind=client
		.get()
		.uri("/producto?cod=100")
		.accept(MediaType.APPLICATION_JSON)
		.retrieve()
		.bodyToMono(Producto.class);
		
		monoFind.subscribe(p->System.out.println(p));
		monoFind.switchIfEmpty(Mono.just(new Producto()).map(p->
				{
					System.out.println("No se ha encontrado producto");
					return p;
				}
			)).block();
		*/
		/*
		Mono<Producto> monoDelete=client
		.delete()
		.uri("/eliminarProducto?cod=4001")
		.accept(MediaType.APPLICATION_JSON)
		.retrieve()
		.onStatus(h->h.is4xxClientError(), t->{
			System.out.println("No se encontró el producto");
			return Mono.empty();
		})
		.bodyToMono(Producto.class);
		monoDelete
		.subscribe(p->System.out.println(p));
		monoDelete.block();
		*/
		
		client
		.put()
		.uri("/actualizar?cod=105&precio=2.6")
		.accept(MediaType.APPLICATION_JSON)
		.retrieve()
		.bodyToMono(Producto.class)
		.subscribe(p->System.out.println(p));
		
	}

}
