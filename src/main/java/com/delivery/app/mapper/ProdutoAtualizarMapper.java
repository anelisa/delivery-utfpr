package com.delivery.app.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.delivery.app.dto.ProdutoAtulizarDTO;
import com.delivery.app.entity.Produto;


@Component
public class ProdutoAtualizarMapper {
	
	@Autowired
	private ModelMapper mapper;
	
	public Produto map(ProdutoAtulizarDTO dto) {
		
		Produto produto = mapper.map(dto, Produto.class);
		
		return produto;
	}
	
	public List<Produto> map(List<ProdutoAtulizarDTO> dtos) {
		
		return dtos.stream().map(
				dto -> map(dto)
			).toList();
	}
}
