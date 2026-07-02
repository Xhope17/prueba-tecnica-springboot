package com.prueba.springboot.gestion_hotel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.springboot.gestion_hotel.models.DTOs.EstadoDto;
import com.prueba.springboot.gestion_hotel.models.DTOs.HabitacionDto;
import com.prueba.springboot.gestion_hotel.models.DTOs.TipoHabitacionDto;
import com.prueba.springboot.gestion_hotel.models.request.habitacion.CreateHabitacionRequest;
import com.prueba.springboot.gestion_hotel.models.request.habitacion.UpdateHabitacionRequest;
import com.prueba.springboot.gestion_hotel.entities.Estado;
import com.prueba.springboot.gestion_hotel.entities.Habitacion;
import com.prueba.springboot.gestion_hotel.entities.TipoHabitacion;
import com.prueba.springboot.gestion_hotel.repositories.EstadoRepository;
import com.prueba.springboot.gestion_hotel.repositories.HabitacionRepository;
import com.prueba.springboot.gestion_hotel.repositories.TipoHabitacionRepository;
import com.prueba.springboot.gestion_hotel.response.GenericResponse;
import com.prueba.springboot.gestion_hotel.response.ResponseHelper;

@Service
public class HabitacionService {

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Autowired
    private TipoHabitacionRepository tipoHabitacionRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public GenericResponse<List<HabitacionDto>> getAll() {
        List<Habitacion> habitaciones = habitacionRepository.findAll();
        List<HabitacionDto> dtos = habitaciones.stream().map(h -> this.map(h)).toList();
        return ResponseHelper.success(dtos, "Habitaciones obtenidas correctamente", dtos.size());
    }

    public GenericResponse<HabitacionDto> getById(Long id) {
        Habitacion habitacion = habitacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada"));
        return ResponseHelper.success(map(habitacion));
    }

    public GenericResponse<List<HabitacionDto>> findDisponibles() {
        Estado disponible = estadoRepository.findByNombre("DISPONIBLE")
                .orElseThrow(() -> new RuntimeException("Estado DISPONIBLE no encontrado"));
        List<Habitacion> habitaciones = habitacionRepository.findByEstadoId(disponible.getId());
        List<HabitacionDto> dtos = habitaciones.stream().map(h -> this.map(h)).toList();
        return ResponseHelper.success(dtos, "Habitaciones disponibles obtenidas", dtos.size());
    }

    public GenericResponse<HabitacionDto> save(CreateHabitacionRequest request) {
        TipoHabitacion tipo = tipoHabitacionRepository.findById(request.getTipoId())
                .orElseThrow(() -> new RuntimeException("Tipo de habitación no encontrado"));
        Estado estado = estadoRepository.findById(request.getEstadoId())
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

        Habitacion habitacion = new Habitacion();
        habitacion.setNumero(request.getNumero());
        habitacion.setDescripcion(request.getDescripcion());
        habitacion.setCapacidad(request.getCapacidad());
        habitacion.setCosto(request.getCosto());
        habitacion.setTipo(tipo);
        habitacion.setEstado(estado);

        Habitacion saved = habitacionRepository.save(habitacion);
        return ResponseHelper.success(map(saved), "Habitación creada correctamente");
    }

    public GenericResponse<HabitacionDto> update(Long id, UpdateHabitacionRequest request) {
        Habitacion habitacion = habitacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada"));

        TipoHabitacion tipo = tipoHabitacionRepository.findById(request.getTipoId())
                .orElseThrow(() -> new RuntimeException("Tipo de habitación no encontrado"));
        Estado estado = estadoRepository.findById(request.getEstadoId())
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

        habitacion.setNumero(request.getNumero());
        habitacion.setDescripcion(request.getDescripcion());
        habitacion.setCapacidad(request.getCapacidad());
        habitacion.setCosto(request.getCosto());
        habitacion.setTipo(tipo);
        habitacion.setEstado(estado);

        Habitacion saved = habitacionRepository.save(habitacion);
        return ResponseHelper.success(map(saved), "Habitación actualizada correctamente");
    }

    public GenericResponse<Void> delete(Long id) {
        if (!habitacionRepository.existsById(id)) {
            return ResponseHelper.error(List.of("Habitación no encontrada"));
        }
        habitacionRepository.deleteById(id);
        return ResponseHelper.success(null, "Habitación eliminada correctamente");
    }

    private HabitacionDto map(Habitacion habitacion) {
        HabitacionDto dto = new HabitacionDto();
        dto.setId(habitacion.getId());
        dto.setNumero(habitacion.getNumero());
        dto.setDescripcion(habitacion.getDescripcion());
        dto.setCapacidad(habitacion.getCapacidad());
        dto.setCosto(habitacion.getCosto());

        TipoHabitacionDto tipoDto = new TipoHabitacionDto();
        tipoDto.setId(habitacion.getTipo().getId());
        tipoDto.setNombre(habitacion.getTipo().getNombre());
        dto.setTipo(tipoDto);

        EstadoDto estadoDto = new EstadoDto();
        estadoDto.setId(habitacion.getEstado().getId());
        estadoDto.setNombre(habitacion.getEstado().getNombre());
        dto.setEstado(estadoDto);

        return dto;
    }
}
