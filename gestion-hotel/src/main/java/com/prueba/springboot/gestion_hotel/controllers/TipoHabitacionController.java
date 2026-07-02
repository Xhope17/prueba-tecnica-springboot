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

import com.prueba.springboot.gestion_hotel.models.DTOs.TipoHabitacionDto;
import com.prueba.springboot.gestion_hotel.response.GenericResponse;
import com.prueba.springboot.gestion_hotel.services.TipoHabitacionService;

@RestController
@RequestMapping("/api/tipos-habitacion")
public class TipoHabitacionController {

    @Autowired
    private TipoHabitacionService service;

    @GetMapping
    public ResponseEntity<GenericResponse<List<TipoHabitacionDto>>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<TipoHabitacionDto>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<GenericResponse<TipoHabitacionDto>> save(@RequestBody TipoHabitacionDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<TipoHabitacionDto>> update(@PathVariable Long id, @RequestBody TipoHabitacionDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<Void>> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
