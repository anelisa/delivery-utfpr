package com.delivery.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delivery.app.entity.Pedido;
import com.delivery.app.entity.PedidoItem;
import com.delivery.app.repository.PedidoItemRepository;

@Service
public class RestaurantePedidoItemService {
	
	@Autowired
	private PedidoItemRepository pedidoItemRepository;

	public PedidoItem criarPedidoItem(PedidoItem pedidoItem, Pedido pedido) {
		
		pedidoItem.setPedido(pedido);
		
		return pedidoItemRepository.save(pedidoItem);
	}
}
