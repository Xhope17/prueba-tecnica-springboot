package com.prueba.springboot.gestion_hotel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.springboot.gestion_hotel.models.DTOs.TipoHabitacionDto;
import com.prueba.springboot.gestion_hotel.entities.TipoHabitacion;
import com.prueba.springboot.gestion_hotel.repositories.TipoHabitacionRepository;
import com.prueba.springboot.gestion_hotel.response.GenericResponse;
import com.prueba.springboot.gestion_hotel.response.ResponseHelper;

@Service
public class TipoHabitacionService {

    @Autowired
    private TipoHabitacionRepository repository;

    public GenericResponse<List<TipoHabitacionDto>> getAll() {
        List<TipoHabitacion> tipos = repository.findAll();
        List<TipoHabitacionDto> dtos = tipos.stream().map(tipo -> this.map(tipo)).toList();
        return ResponseHelper.success(dtos, "Tipos obtenidos correctamente", dtos.size());
    }

    public GenericResponse<TipoHabitacionDto> getById(Long id) {
        TipoHabitacion tipo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de habitación no encontrado"));
        return ResponseHelper.success(map(tipo));
    }

    public GenericResponse<TipoHabitacionDto> save(TipoHabitacionDto dto) {
        TipoHabitacion tipo = new TipoHabitacion();
        tipo.setNombre(dto.getNombre());
        tipo.setDescripcion(dto.getDescripcion());
        TipoHabitacion saved = repository.save(tipo);
        return ResponseHelper.success(map(saved), "Tipo de habitación creado correctamente");
    }

    public GenericResponse<TipoHabitacionDto> update(Long id, TipoHabitacionDto dto) {
        TipoHabitacion tipo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de habitación no encontrado"));
        tipo.setNombre(dto.getNombre());
        tipo.setDescripcion(dto.getDescripcion());
        TipoHabitacion saved = repository.save(tipo);
        return ResponseHelper.success(map(saved), "Tipo de habitación actualizado correctamente");
    }

    public GenericResponse<Void> delete(Long id) {
        if (!repository.existsById(id)) {
            return ResponseHelper.error(List.of("Tipo de habitación no encontrado"));
        }
        repository.deleteById(id);
        return ResponseHelper.success(null, "Tipo de habitación eliminado correctamente");
    }

    private TipoHabitacionDto map(TipoHabitacion tipo) {
        TipoHabitacionDto dto = new TipoHabitacionDto();
        dto.setId(tipo.getId());
        dto.setNombre(tipo.getNombre());
        dto.setDescripcion(tipo.getDescripcion());
        return dto;
    }
}
