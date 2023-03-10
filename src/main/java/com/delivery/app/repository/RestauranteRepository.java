package com.delivery.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delivery.app.entity.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

}
