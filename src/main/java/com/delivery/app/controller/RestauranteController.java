package com.delivery.app.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.app.dto.RestauranteResumidoDTO;
import com.delivery.app.entity.Restaurante;
import com.delivery.app.mapper.RestauranteResumidoMapper;
import com.delivery.app.service.RestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private RestauranteResumidoMapper restauranteResumidoMapper;
	
	@GetMapping
	@ResponseBody
	public List<RestauranteResumidoDTO> listarRestaurantes() {
		
		List<Restaurante> restaurantes = restauranteService.listaRestaurante();
		
		List<RestauranteResumidoDTO> restauranteDTO = restauranteResumidoMapper.map(restaurantes);
		
		return restauranteDTO;
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Restaurante> retornaRestaurante(@PathVariable Long id) {
		
		Restaurante restaurante = restauranteService.retornaRestauranteId(id);
		
		if(restaurante == null) {

			return ResponseEntity.notFound().build();	
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(restaurante);
	}
	
	@PostMapping("/criar")
	@ResponseStatus(code = HttpStatus.CREATED)
	private Restaurante criarRestaurante(@RequestBody Restaurante restaurante) {
		
		restaurante = restauranteService.criarRestaurante(restaurante);
		return restaurante;
	}
	
	@PutMapping("/atualizar/{id}")
	@ResponseBody
	private Restaurante atualizarRestaurante(@PathVariable Long id, @RequestBody Restaurante restaurante) {
		
		restaurante = restauranteService.atualizarRestaurante(id, restaurante);
		return restaurante;
	}
}
