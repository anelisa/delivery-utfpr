package com.delivery.app.entity;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Data
@Entity
@Table(name="produto")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name= "nome")
	private String nome;
	
	@Column(name= "descricao")
	private String descricao;
	
	@Column(name="preco")
	private BigDecimal preco;
	
	@JsonBackReference
	@ManyToOne(optional=false)
	@JoinColumn(name="restaurante_id", nullable = false, referencedColumnName = "id")
	private Restaurante restaurante;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "produto")
	private List<PedidoItem> pedidoItem;

}
