package com.delivery.app.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.delivery.app.entity.Produto;
import com.delivery.app.entity.Restaurante;


public class RestauranteDTO {
	
	public RestauranteDTO(Restaurante restaurante){
		this.setNome(restaurante.getNome());
		
		List<Produto> produtos = restaurante.getProduto();
		
		for(int i = 0; i < produtos.size(); i++) {
			this.produtos.add(new ProdutoDTO(produtos.get(i)));
		}
		this.id = restaurante.getId();
		this.taxaFrete = restaurante.getTaxaFrete();
	}

	private Long id;
	
	private String nome;
	
	private BigDecimal taxaFrete;
	
	private List<ProdutoDTO> produtos = new ArrayList<ProdutoDTO>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getTaxaFrete() {
		return taxaFrete;
	}

	public void setTaxaFrete(BigDecimal taxaFrete) {
		this.taxaFrete = taxaFrete;
	}
}
