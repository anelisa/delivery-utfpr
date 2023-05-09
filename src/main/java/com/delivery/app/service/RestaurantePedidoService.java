package com.delivery.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delivery.app.entity.Cliente;
import com.delivery.app.entity.Pedido;
import com.delivery.app.entity.PedidoItem;
import com.delivery.app.repository.PedidoRepository;

@Service
public class RestaurantePedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private RestaurantePedidoItemService pedidoItemService;
	
	@Autowired
	private ClienteService clienteService;
	
	public Pedido criarPedido(Pedido pedido) {
		
		Cliente cliente = clienteService.getById(pedido.getClienteId());
		pedido.setCliente(cliente);
		
		Pedido novoPedido = pedidoRepository.save(pedido);
		
		for (PedidoItem item : pedido.getPedidoItem()) {
			pedidoItemService.criarPedidoItem(item, novoPedido);
		}
		
		return novoPedido;
	}
}
