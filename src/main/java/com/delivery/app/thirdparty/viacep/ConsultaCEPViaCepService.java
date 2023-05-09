package com.delivery.app.thirdparty.viacep;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.delivery.app.dto.EnderecoDTO;
import com.delivery.app.exception.NotFoundException;

@Service
public class ConsultaCEPViaCepService {

	public EnderecoDTO consultar(String cep) {
		
		String url = "https://viacep.com.br/ws/" + cep + "/json/";
		
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			ResponseEntity<EnderecoDTO> response =
					restTemplate.getForEntity(url, EnderecoDTO.class);
			
			System.out.println(response.getStatusCode());
			
			return response.getBody();
			
		} catch (RestClientException ex) {
			throw new NotFoundException("CEP não encontrado...");
		}
		
	}
	
}