package com.prueba.springboot.gestion_hotel.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.springboot.gestion_hotel.entities.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

    Optional<Estado> findByNombre(String nombre);
}

