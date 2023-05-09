package com.delivery.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.delivery.app.entity.Produto;
import com.delivery.app.exception.NotFoundException;
import com.delivery.app.repository.ProdutoRepository;
import com.delivery.app.util.Utils;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<Produto> listaProduto(Long idRestaurante) {
		return produtoRepository.encontrarProduto(idRestaurante.toString());
	}
	
	public Produto retornaProdutoId(Long idRestaurante, Long id) {
		
		List<Produto> produto = produtoRepository.encontrarRestauranteProduto(idRestaurante.toString(), id.toString());
		
		if(produto.isEmpty()) {
			throw new NotFoundException("Produto nao encontrado");
		}
		
		return produto.get(0);	
	}
	
	public Produto criarProduto(Produto produto) {
		
		return produtoRepository.save(produto);
	}
	
	public Produto atualizarProduto(Long idRestaurante, Long id, Produto produto) {
		
		Produto produtoAtual = this.retornaProdutoId(idRestaurante, id);
		
		produto.setRestaurante(produtoAtual.getRestaurante());
		
		BeanUtils.copyProperties(produto, produtoAtual);
			
		return produtoRepository.save(produtoAtual);
	}
	
	public Produto ajustesProduto(Long idRestaurante, Long id, Map<String, Object> campos) {
		
		Produto produtoAtual = this.retornaProdutoId(idRestaurante, id);
		
		Utils.merge(produtoAtual, campos);
		
		return produtoRepository.save(produtoAtual);
	}
	
	public void deletarProduto(Long idRestaurante, Long id) {
		
		try {
			produtoRepository.deletarProduto(idRestaurante.toString(), id.toString());
	
		}catch (EmptyResultDataAccessException ex) {
			throw new NotFoundException(ex.getLocalizedMessage());
		}
	}
}
