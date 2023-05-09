package com.delivery.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.app.dto.PedidoCriarDTO;
import com.delivery.app.entity.Pedido;
import com.delivery.app.entity.Restaurante;
import com.delivery.app.mapper.PedidoCriarMapper;
import com.delivery.app.service.RestaurantePedidoService;
import com.delivery.app.service.RestauranteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/")
public class RestaurantePedidoController {
	
	@Autowired
	private PedidoCriarMapper pedidoCriarMapper;
	
	@Autowired
	private RestaurantePedidoService pedidoService;
	
	@Autowired
	private RestauranteService restauranteService;

	@PostMapping("restaurante/{idRestaurante}/pedido")
	@ResponseStatus(code = HttpStatus.CREATED)
	private Pedido criarPedido(@RequestBody @Valid PedidoCriarDTO pedidoCriarDTO, @PathVariable Long idRestaurante) {
		
		Pedido pedido = pedidoCriarMapper.map(pedidoCriarDTO, idRestaurante);
		
		Restaurante restaurante = restauranteService.retornaRestauranteId(idRestaurante);
		
		pedido.setRestaurante(restaurante);
		
		Pedido novoPedido = pedidoService.criarPedido(pedido);
		
		return novoPedido;
	}
}
