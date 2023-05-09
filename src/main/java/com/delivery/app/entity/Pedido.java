package com.delivery.app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="cliente_id", insertable=false, updatable=false)
	private Long clienteId;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="cliente_id", referencedColumnName = "id")
	private Cliente cliente;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.PERSIST)
	private List<PedidoItem> pedidoItem;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="restaurante_pedido_id", referencedColumnName = "id")
	private Restaurante restaurante;
}
