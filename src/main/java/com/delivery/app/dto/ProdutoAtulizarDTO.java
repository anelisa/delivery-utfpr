package com.delivery.app.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProdutoAtulizarDTO {
	
	private Long id;
	
	private String nome;
	
	private String descricao;
	
	private BigDecimal preco;
}
