package com.delivery.app.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@GetMapping(value = "/{id}")
	public Restaurante retornaRestaurante(@PathVariable Long id) {
		return restauranteService.retornaRestaurante(id);
	}
}
