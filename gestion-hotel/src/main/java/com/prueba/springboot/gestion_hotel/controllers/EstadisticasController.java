package com.prueba.springboot.gestion_hotel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.springboot.gestion_hotel.models.DTOs.ReservacionStatsDto;
import com.prueba.springboot.gestion_hotel.response.GenericResponse;
import com.prueba.springboot.gestion_hotel.services.EstadisticasService;

@RestController
@RequestMapping("/api/reservaciones/estadisticas")
public class EstadisticasController {

    @Autowired
    private EstadisticasService service;

    @GetMapping("/cantidad")
    public ResponseEntity<GenericResponse<List<ReservacionStatsDto>>> getCantidadPorMesYAnio(
            @RequestParam(required = false) Integer anio,
            @RequestParam(required = false) Integer mes) {
        return ResponseEntity.ok(service.getCantidadPorMesYAnio(anio, mes));
    }

    @GetMapping("/costo-promedio")
    public ResponseEntity<GenericResponse<List<ReservacionStatsDto>>> getCostoPromedioPorMesYAnio(
            @RequestParam(required = false) Integer anio,
            @RequestParam(required = false) Integer mes) {
        return ResponseEntity.ok(service.getCostoPromedioPorMesYAnio(anio, mes));
    }
}
