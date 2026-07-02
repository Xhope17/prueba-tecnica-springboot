package com.prueba.springboot.gestion_hotel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.springboot.gestion_hotel.models.DTOs.EstadoDto;
import com.prueba.springboot.gestion_hotel.entities.Estado;
import com.prueba.springboot.gestion_hotel.repositories.EstadoRepository;
import com.prueba.springboot.gestion_hotel.response.GenericResponse;
import com.prueba.springboot.gestion_hotel.response.ResponseHelper;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository repository;

    public GenericResponse<List<EstadoDto>> getAll() {
        List<Estado> estados = repository.findAll();
        List<EstadoDto> dtos = estados.stream().map(e -> this.map(e)).toList();
        return ResponseHelper.success(dtos, "Estados obtenidos correctamente", dtos.size());
    }

    public GenericResponse<EstadoDto> getById(Long id) {
        Estado estado = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));
        return ResponseHelper.success(map(estado));
    }

    public GenericResponse<EstadoDto> save(EstadoDto dto) {
        Estado estado = new Estado();
        estado.setNombre(dto.getNombre());
        Estado saved = repository.save(estado);
        return ResponseHelper.success(map(saved), "Estado creado correctamente");
    }

    public GenericResponse<EstadoDto> update(Long id, EstadoDto dto) {
        Estado estado = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));
        estado.setNombre(dto.getNombre());
        Estado saved = repository.save(estado);
        return ResponseHelper.success(map(saved), "Estado actualizado correctamente");
    }

    public GenericResponse<Void> delete(Long id) {
        if (!repository.existsById(id)) {
            return ResponseHelper.error(List.of("Estado no encontrado"));
        }
        repository.deleteById(id);
        return ResponseHelper.success(null, "Estado eliminado correctamente");
    }

    private EstadoDto map(Estado estado) {
        EstadoDto dto = new EstadoDto();
        dto.setId(estado.getId());
        dto.setNombre(estado.getNombre());
        return dto;
    }
}
