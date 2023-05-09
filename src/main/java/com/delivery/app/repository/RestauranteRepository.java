package com.delivery.app.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import com.delivery.app.entity.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

	//usar a palavra chave "default" para criar uma implementação dentro da interface
	//permite criar um método implementado dentro da interface
	
	default void deleteByIdWithException(Long id) throws EmptyResultDataAccessException {

		Restaurante restaurante = findById(id).orElseThrow(()
				-> new EmptyResultDataAccessException
				("Restaurante não encontrado com a id: " + id, 1));

		delete(restaurante);

	}
}
