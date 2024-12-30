package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Envio;
import reactor.core.publisher.Flux;
import service.EnviosService;

@RestController
public class EnviosController {
	@Autowired
	EnviosService enviosService;
	
	@GetMapping(value="/")
	public ResponseEntity<Flux<Envio>> enviosPendientes(){
		return new ResponseEntity<>(enviosService.pendientes(),HttpStatus.OK);
	}
}
