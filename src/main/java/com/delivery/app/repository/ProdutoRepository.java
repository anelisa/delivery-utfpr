package com.delivery.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.delivery.app.entity.Produto;

import jakarta.transaction.Transactional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	@Query(value = "SELECT * FROM produto p WHERE p.restaurante_id = :idRestaurante", nativeQuery = true)
	List<Produto> encontrarProduto(String idRestaurante);
	
	@Query(value = "SELECT * FROM produto p WHERE p.restaurante_id = :idRestaurante AND p.id = :id", nativeQuery = true)
	List<Produto> encontrarRestauranteProduto(String idRestaurante, String id);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM produto p WHERE p.restaurante_id = :idRestaurante AND p.id = :id", nativeQuery = true)
	void deletarProduto(String idRestaurante, String id);
}
