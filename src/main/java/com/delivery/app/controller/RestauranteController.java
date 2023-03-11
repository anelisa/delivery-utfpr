package com.delivery.app.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.app.entity.Restaurante;
import com.delivery.app.service.RestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
	
	@Autowired
	private RestauranteService restauranteService;
	
	@GetMapping
	@ResponseBody
	public List<Restaurante> listarRestaurantes() {
		
		return restauranteService.listaRestaurante();
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
		return restauranteService.criarRestaurante(restaurante);
	}
}
