package com.delivery.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delivery.app.entity.Restaurante;
import com.delivery.app.exception.NotFoundException;
import com.delivery.app.repository.RestauranteRepository;

@Service
public class RestauranteService {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	public List<Restaurante> listaRestaurante() {
		return restauranteRepository.findAll();
	}
	
<<<<<<< Updated upstream
	public Restaurante retornaRestaurante(Long id) {
		return restauranteRepository.findById(id).get();
=======
	public Restaurante retornaRestauranteId(Long id) {
		Optional<Restaurante> restaurante =  restauranteRepository.findById(id);
		
		if(restaurante.isEmpty()) {
			//return null;
			throw new NotFoundException("Restaurante nao encontrado");
		}
		return restaurante.get();
	}
	
	public Restaurante criarRestaurante(Restaurante restaurante) {
		
		return restauranteRepository.save(restaurante);
>>>>>>> Stashed changes
	}
}
