package com.prueba.springboot.gestion_hotel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.springboot.gestion_hotel.models.Horario;

public interface HorarioRepository extends JpaRepository<Horario, Long> {

    List<Horario> findByHabitacionId(Long habitacionId);
}
