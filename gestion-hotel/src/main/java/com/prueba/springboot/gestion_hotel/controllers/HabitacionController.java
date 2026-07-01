package com.prueba.springboot.gestion_hotel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.springboot.gestion_hotel.dtos.HabitacionDto;
import com.prueba.springboot.gestion_hotel.services.HabitacionService;


@RestController
@RequestMapping("/api/habitaciones")
public class HabitacionController {

    @Autowired
    private HabitacionService habitacionService;

    @GetMapping("/disponibles")
    public ResponseEntity<List<HabitacionDto>> listarDisponibles() {
        List<HabitacionDto> disponibles = habitacionService.findDisponibles();
        return ResponseEntity.ok(disponibles);
    }

    @PostMapping
    public ResponseEntity<HabitacionDto> crearHabitacion(@RequestBody HabitacionDto habitacionDto) {
        HabitacionDto nuevaHabitacion = habitacionService.saveHabitacion(habitacionDto);
        
        
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaHabitacion);
    }
}
