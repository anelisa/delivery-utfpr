package com.delivery.app.dto;

import lombok.Data;

@Data
public class EnderecoDTO {

	private String cep;

	private String logradouro;

	private String bairro;

	private String localidade;

	private String uf;

}