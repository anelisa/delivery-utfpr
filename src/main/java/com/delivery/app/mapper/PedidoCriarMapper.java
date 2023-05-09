package com.delivery.app.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.delivery.app.dto.PedidoCriarDTO;
import com.delivery.app.dto.PedidoItemCriarDTO;
import com.delivery.app.entity.Pedido;
import com.delivery.app.entity.PedidoItem;
import com.delivery.app.entity.Produto;
import com.delivery.app.service.ProdutoService;

@Component
public class PedidoCriarMapper {
	
	@Autowired
	private ProdutoService produtoService;
	
	public Pedido map(PedidoCriarDTO dto, Long restauranteId) {
		
		Pedido pedido = new Pedido();
		
		pedido.setClienteId(dto.getClienteId().longValue());
		
		List<PedidoItem> pedidoItens = new ArrayList<>();
		
		for(PedidoItemCriarDTO item : dto.getPedidoItem()) {
			Produto produto = produtoService.retornaProdutoId(restauranteId, item.getProdutoId());
			
			PedidoItem pedidoItem = new PedidoItem();
			
			pedidoItem.setProduto(produto);
			pedidoItem.setProdutoId(produto.getId());
			pedidoItem.setQuantidade(item.getQuantidade());
			
			pedidoItens.add(pedidoItem);
		}
		
		pedido.setPedidoItem(pedidoItens);
		
		return pedido;
	}
	
}
