package com.delivery.app.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProdutoCriarDTO {

	private String nome;
	
	private String descricao;
	
	private BigDecimal preco;
}
