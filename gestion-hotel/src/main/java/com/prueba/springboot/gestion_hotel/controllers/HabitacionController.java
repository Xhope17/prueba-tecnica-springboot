package com.prueba.springboot.gestion_hotel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.springboot.gestion_hotel.models.DTOs.HabitacionDto;
import com.prueba.springboot.gestion_hotel.models.request.habitacion.CreateHabitacionRequest;
import com.prueba.springboot.gestion_hotel.models.request.habitacion.UpdateHabitacionRequest;
import com.prueba.springboot.gestion_hotel.response.GenericResponse;
import com.prueba.springboot.gestion_hotel.services.HabitacionService;

@RestController
@RequestMapping("/api/habitaciones")
public class HabitacionController {

    @Autowired
    private HabitacionService habitacionService;

    @GetMapping
    public ResponseEntity<GenericResponse<List<HabitacionDto>>> getAll() {
        return ResponseEntity.ok(habitacionService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<HabitacionDto>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(habitacionService.getById(id));
    }

    @GetMapping("/disponibles")
    public ResponseEntity<GenericResponse<List<HabitacionDto>>> findDisponibles() {
        return ResponseEntity.ok(habitacionService.findDisponibles());
    }

    @PostMapping
    public ResponseEntity<GenericResponse<HabitacionDto>> save(@RequestBody CreateHabitacionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(habitacionService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<HabitacionDto>> update(@PathVariable Long id, @RequestBody UpdateHabitacionRequest request) {
        return ResponseEntity.ok(habitacionService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<Void>> delete(@PathVariable Long id) {
        return ResponseEntity.ok(habitacionService.delete(id));
    }
}
