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

import com.prueba.springboot.gestion_hotel.models.DTOs.ReservacionDto;
import com.prueba.springboot.gestion_hotel.models.request.reservacion.CreateReservacionRequest;
import com.prueba.springboot.gestion_hotel.models.request.reservacion.UpdateReservacionRequest;
import com.prueba.springboot.gestion_hotel.response.GenericResponse;
import com.prueba.springboot.gestion_hotel.services.ReservacionService;

@RestController
@RequestMapping("/api/reservaciones")
public class ReservacionController {

    @Autowired
    private ReservacionService service;

    @GetMapping
    public ResponseEntity<GenericResponse<List<ReservacionDto>>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<ReservacionDto>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<GenericResponse<ReservacionDto>> save(@RequestBody CreateReservacionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<ReservacionDto>> update(@PathVariable Long id, @RequestBody UpdateReservacionRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<Void>> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }

}
