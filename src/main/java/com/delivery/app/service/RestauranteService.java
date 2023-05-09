package com.delivery.app.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.delivery.app.entity.Restaurante;
import com.delivery.app.exception.NotFoundException;
import com.delivery.app.repository.RestauranteRepository;
import com.delivery.app.util.Utils;

@Service
public class RestauranteService {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	public List<Restaurante> listaRestaurante() {
		return restauranteRepository.findAll();
	}
	
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
	}
	
	public Restaurante atualizarRestaurante(Long id, Restaurante restaurante) {
		
		//validar se o restaurante que esta sendo buscado existe no banco de dados
		Restaurante restauranteAtual = this.retornaRestauranteId(id);
		
		BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
		
		return restauranteRepository.save(restauranteAtual);

	}
	
	public Restaurante ajustarRestaurante(Long id, Map<String, Object> campos) {
		
		Restaurante restauranteAtual = this.retornaRestauranteId(id);
		Utils.merge(restauranteAtual, campos);
		return restauranteRepository.save(restauranteAtual);
	}
	
	public void deletarRestaurante(Long id) {

		try {

			restauranteRepository.deleteByIdWithException(id);

		} catch (EmptyResultDataAccessException ex) {
			throw new NotFoundException(ex.getLocalizedMessage());
		}

	}
}
