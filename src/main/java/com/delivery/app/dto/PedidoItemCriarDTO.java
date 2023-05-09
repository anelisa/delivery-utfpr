package com.delivery.app.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PedidoItemCriarDTO {
	
	private Long produtoId;
	
	private BigDecimal quantidade;

}
