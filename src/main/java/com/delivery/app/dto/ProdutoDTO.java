package com.delivery.app.dto;

import java.math.BigDecimal;

import com.delivery.app.entity.Produto;

import lombok.Data;

@Data
public class ProdutoDTO {
	
	public ProdutoDTO(Produto produto){
		
		this.nome = produto.getNome();
		this.id = produto.getId();
		this.preco = produto.getPreco();
		this.descricao = produto.getDescricao();
	}

	private Long id;
	private String nome;
	private String descricao;
	private BigDecimal preco;
	
}
