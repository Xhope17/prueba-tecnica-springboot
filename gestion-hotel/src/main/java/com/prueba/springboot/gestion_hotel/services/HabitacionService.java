package com.prueba.springboot.gestion_hotel.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.springboot.gestion_hotel.dtos.HabitacionDto;
import com.prueba.springboot.gestion_hotel.models.Habitacion;
import com.prueba.springboot.gestion_hotel.repositories.HabitacionRepository;

@Service
public class HabitacionService {

    @Autowired
    private HabitacionRepository habitacionRepository;

    public List<Habitacion> findByEstado(String estado) {
        return habitacionRepository.findByEstado(estado);
    }
//HABITACIONES DISPONIBLES
    public List<HabitacionDto> findDisponibles() {
        List<Habitacion> habitaciones = habitacionRepository.findByEstado("DISPONIBLE");

        List<HabitacionDto> mapped = new ArrayList<>();

        for (Habitacion habitacion : habitaciones) {
            mapped.add(map(habitacion));
        }

        return mapped;
    }

    public HabitacionDto saveHabitacion(HabitacionDto dto) {

        Habitacion habitacion = new Habitacion();
        habitacion.setNumero(dto.getNumero());
        habitacion.setTipo(dto.getTipo());
        habitacion.setEstado(dto.getEstado());
        habitacion.setCapacidad(dto.getCapacidad());

        Habitacion habitacionGuardada= habitacionRepository.save(habitacion);

        return map(habitacionGuardada);
    }
    // mapper
    public HabitacionDto map(Habitacion habitacion) {
        HabitacionDto dto = new HabitacionDto();
        dto.setNumero(habitacion.getNumero());
        dto.setTipo(habitacion.getTipo());
        dto.setEstado(habitacion.getEstado());
        dto.setCapacidad(habitacion.getCapacidad());
        return dto;
    }
}
