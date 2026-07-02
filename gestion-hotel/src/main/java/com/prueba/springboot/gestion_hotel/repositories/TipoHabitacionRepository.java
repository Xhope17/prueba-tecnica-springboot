package com.prueba.springboot.gestion_hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.springboot.gestion_hotel.entities.TipoHabitacion;

public interface TipoHabitacionRepository extends JpaRepository<TipoHabitacion, Long> {
}

