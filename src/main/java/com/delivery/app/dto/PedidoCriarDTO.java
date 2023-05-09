package com.delivery.app.dto;

import java.util.List;


import lombok.Data;

@Data
public class PedidoCriarDTO {

	private Number clienteId;
	
	private List<PedidoItemCriarDTO> pedidoItem;
}
