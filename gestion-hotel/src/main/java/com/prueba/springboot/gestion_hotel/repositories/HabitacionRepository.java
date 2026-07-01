package com.prueba.springboot.gestion_hotel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.springboot.gestion_hotel.models.Habitacion;

public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {

    List<Habitacion> findByEstado(String estado);
    List<Habitacion> findByTipo(String tipo);

    List<Habitacion> findByCapacidad(Integer capacidad);


}
