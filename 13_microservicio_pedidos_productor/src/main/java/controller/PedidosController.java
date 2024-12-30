package controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import model.Pedido;
import service.PedidosService;

@RestController
@RequiredArgsConstructor
public class PedidosController {
	
	private final PedidosService pedidosService;
	
	@PostMapping(value="alta",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> nuevoPedido(@RequestBody Pedido pedido){
		try {
			pedidosService.registrarPedido(pedido);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception ex){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
