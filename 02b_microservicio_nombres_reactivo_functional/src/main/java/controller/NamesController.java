package controller;

import java.time.Duration;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;

@Configuration
public class NamesController {
	@Bean
	public RouterFunction<ServerResponse> getNames(){
		List<String> names=List.of("one","two","three","four");
		return RouterFunctions.route(RequestPredicates.GET("names"), 
				req->ServerResponse.ok() //BodyBuilder
					.body(Flux.fromIterable(names)
							.delayElements(Duration.ofSeconds(2)), String.class)//Mono<ServerResponse>
				);//RouterFunction<ServerResponse>
				
	}
}
