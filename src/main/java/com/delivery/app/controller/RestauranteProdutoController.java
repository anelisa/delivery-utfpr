package com.delivery.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.app.dto.ProdutoAtulizarDTO;
import com.delivery.app.dto.ProdutoCriarDTO;
import com.delivery.app.dto.ProdutoDTO;
import com.delivery.app.entity.Produto;
import com.delivery.app.entity.Restaurante;
import com.delivery.app.mapper.ProdutoAtualizarMapper;
import com.delivery.app.mapper.ProdutoInputMapper;
import com.delivery.app.service.ProdutoService;
import com.delivery.app.service.RestauranteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/")
public class RestauranteProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired(required=true)
	private ProdutoInputMapper produtoInputMapper;
	
	@Autowired
	private ProdutoAtualizarMapper produtoAtualizarMapper;
	
	@GetMapping("restaurante/{idRestaurante}/produto")
	@ResponseBody
	public List<Produto> listarProdutos(@PathVariable Long idRestaurante) {
		
		
		List<Produto> produtos = produtoService.listaProduto(idRestaurante);
		
		return produtos;
	}
	
	@GetMapping("restaurante/{idRestaurante}/produto/{id}")
	@ResponseBody
	public ResponseEntity<ProdutoDTO> retornaProdutoId(@PathVariable Long idRestaurante,@PathVariable Long id){
		
		Produto produto = produtoService.retornaProdutoId(idRestaurante, id);
		
		if(produto == null) {
			
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(new ProdutoDTO(produto));
	}
	
	@PostMapping("restaurante/{idRestaurante}/produto")
	@ResponseStatus(code = HttpStatus.CREATED)
	private Produto criarProduto(@RequestBody @Valid ProdutoCriarDTO produtoCriarDTO, @PathVariable Long idRestaurante) {
		
		Produto produto = produtoInputMapper.map(produtoCriarDTO);
		
		Restaurante restaurante = restauranteService.retornaRestauranteId(idRestaurante);
		
		produto.setRestaurante(restaurante);
		
		produto = produtoService.criarProduto(produto);
		
		return produto;
	}
	
	@PutMapping("/restaurante/{idRestaurante}/produto/{id}")
	@ResponseBody
	private Produto atualizarProduto(@PathVariable Long idRestaurante, @PathVariable Long id, @RequestBody @Valid ProdutoAtulizarDTO produtoAtualizarDTO){
		
		Produto produto = produtoAtualizarMapper.map(produtoAtualizarDTO);
		
		produto.setId(id);
		
		Produto novoProduto = produtoService.atualizarProduto(idRestaurante, id, produto);
		
		return novoProduto;
	}
	
	@PatchMapping("/restaurante/{idRestaurante}/produto/{id}")
	@ResponseBody
	private Produto ajustesProduto(@PathVariable Long idRestaurante, @PathVariable Long id, @RequestBody Map<String, Object> campos){
		
		Produto produtoAjuste = produtoService.ajustesProduto(idRestaurante, id, campos);
		
		return produtoAjuste;
	}
	
	@DeleteMapping("/restaurante/{idRestaurante}/produto/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	private void deletarProduto(@PathVariable Long idRestaurante,@PathVariable Long id) {

		produtoService.deletarProduto(idRestaurante, id);
	}
}
