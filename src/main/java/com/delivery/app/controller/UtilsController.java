package com.delivery.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.app.dto.EnderecoDTO;
import com.delivery.app.thirdparty.viacep.ConsultaCEPViaCepService;


@RestController
@RequestMapping("/utils")
public class UtilsController {
	
	@Autowired
	private ConsultaCEPViaCepService consultaCEPViaCepService;

	//GET - http://localhost:8081/utils/cep/82560435
	@GetMapping("/cep/{cep}")
	@ResponseStatus(code = HttpStatus.OK)
	public EnderecoDTO consultarCEP(@PathVariable String cep) {
		
		return consultaCEPViaCepService.consultar(cep);
		
	}
	
}