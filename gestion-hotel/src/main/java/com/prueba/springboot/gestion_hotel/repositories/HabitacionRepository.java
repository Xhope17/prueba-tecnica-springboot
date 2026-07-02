package com.prueba.springboot.gestion_hotel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.springboot.gestion_hotel.entities.Habitacion;

public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {

    List<Habitacion> findByTipoId(Long tipoId);

    List<Habitacion> findByEstadoId(Long estadoId);
}

