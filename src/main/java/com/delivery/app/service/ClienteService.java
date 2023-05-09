package com.delivery.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delivery.app.entity.Cliente;
import com.delivery.app.exception.NotFoundException;
import com.delivery.app.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	public Cliente getById(Long id) {
		Optional<Cliente> cliente =  clienteRepository.findById(id);
		
		if(cliente.isEmpty()) {
			throw new NotFoundException("Cliente nao encontrado");
		}
		return cliente.get();

	}

}
