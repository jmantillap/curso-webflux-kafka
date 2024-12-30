package controller;

import java.time.Duration;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class NamesController {
	@GetMapping(value="names")
	public Flux<String> getNames(){
		List<String> names=List.of("one,","two,","three,","four");
		return Flux.fromIterable(names)
				.delayElements(Duration.ofSeconds(2));
	}
	
	@GetMapping(value="names1")
	public Mono<List<String>> getNames1(){
		List<String> names=List.of("one,","two,","three,","four");
		return Flux.fromIterable(names)
				.delayElements(Duration.ofSeconds(2))
				.flatMap(name->this.getNameUpperCase(name))
				.collectList()
				;
	}	
	
	@GetMapping(value="names2")
	public Flux<String> getNames2(){
		List<String> names=List.of("one,","two,","three,","four");
		return Flux.fromIterable(names)
				.delayElements(Duration.ofSeconds(2))
				.flatMap(name	->this.getNameUpperCase(name));
	}
	
	private Mono<String> getNameUpperCase(String name) {
		return Mono.just(name.toUpperCase());
	}
	
	
}
