package com.delivery.app.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.delivery.app.dto.ProdutoCriarDTO;
import com.delivery.app.entity.Produto;


@Component
public class ProdutoInputMapper {
	
	@Autowired
	private ModelMapper mapper;
	
	public Produto map(ProdutoCriarDTO dto) {
		
		Produto produto = mapper.map(dto, Produto.class);
		
		return produto;
	}
	
	public List<Produto> map(List<ProdutoCriarDTO> dtos) {
		
		return dtos.stream().map(
				dto -> map(dto)
			).toList();
	}
}
