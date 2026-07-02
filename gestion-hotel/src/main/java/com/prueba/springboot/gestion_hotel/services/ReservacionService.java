package com.prueba.springboot.gestion_hotel.services;

import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.springboot.gestion_hotel.models.DTOs.ClienteDto;
import com.prueba.springboot.gestion_hotel.models.DTOs.EstadoDto;
import com.prueba.springboot.gestion_hotel.models.DTOs.HabitacionDto;
import com.prueba.springboot.gestion_hotel.models.DTOs.ReservacionDto;
import com.prueba.springboot.gestion_hotel.models.request.reservacion.CreateReservacionRequest;
import com.prueba.springboot.gestion_hotel.models.request.reservacion.UpdateReservacionRequest;
import com.prueba.springboot.gestion_hotel.entities.Cliente;
import com.prueba.springboot.gestion_hotel.entities.Estado;
import com.prueba.springboot.gestion_hotel.entities.Habitacion;
import com.prueba.springboot.gestion_hotel.entities.Reservacion;
import com.prueba.springboot.gestion_hotel.repositories.ClienteRepository;
import com.prueba.springboot.gestion_hotel.repositories.EstadoRepository;
import com.prueba.springboot.gestion_hotel.repositories.HabitacionRepository;
import com.prueba.springboot.gestion_hotel.repositories.ReservacionRepository;
import com.prueba.springboot.gestion_hotel.response.GenericResponse;
import com.prueba.springboot.gestion_hotel.response.ResponseHelper;

@Service
public class ReservacionService {

    @Autowired
    private ReservacionRepository reservacionRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public GenericResponse<List<ReservacionDto>> getAll() {
        List<Reservacion> reservaciones = reservacionRepository.findAll();
        List<ReservacionDto> dtos = reservaciones.stream().map(r -> this.map(r)).toList();
        return ResponseHelper.success(dtos, "Reservaciones obtenidas correctamente", dtos.size());
    }

    public GenericResponse<ReservacionDto> getById(Long id) {
        Reservacion reservacion = reservacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservación no encontrada"));
        return ResponseHelper.success(map(reservacion));
    }

    public GenericResponse<ReservacionDto> save(CreateReservacionRequest request) {
        if (!request.getFechaSalida().isAfter(request.getFechaEntrada())) {
            return ResponseHelper.error(List.of("La fecha de salida debe ser posterior a la fecha de entrada"));
        }

        Long habitacionId = request.getHabitacionId();
        //indica si la habitación esta ocupada en esas fechas
        List<Long> ocupadas = reservacionRepository.findHabitacionesOcupadas(
                request.getFechaEntrada(), request.getFechaSalida());
        if (ocupadas.contains(habitacionId)) {
            return ResponseHelper.error(List.of("La habitación ya está reservada en esas fechas"));
        }

        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Habitacion habitacion = habitacionRepository.findById(habitacionId)
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada"));
        Estado estado = estadoRepository.findById(request.getEstadoId())
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

        long dias = ChronoUnit.DAYS.between(request.getFechaEntrada(), request.getFechaSalida());

        Reservacion reservacion = new Reservacion();
        reservacion.setFechaEntrada(request.getFechaEntrada());
        reservacion.setFechaSalida(request.getFechaSalida());
        reservacion.setCostoTotal(dias * habitacion.getCosto());
        reservacion.setCliente(cliente);
        reservacion.setHabitacion(habitacion);
        reservacion.setEstado(estado);

        Reservacion saved = reservacionRepository.save(reservacion);
        return ResponseHelper.success(map(saved), "Reservación creada correctamente");
    }

    public GenericResponse<ReservacionDto> update(Long id, UpdateReservacionRequest request) {
        Reservacion reservacion = reservacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservación no encontrada"));

        if (!request.getFechaSalida().isAfter(request.getFechaEntrada())) {
            return ResponseHelper.error(List.of("La fecha de salida debe ser posterior a la fecha de entrada"));
        }

        Long habitacionId = request.getHabitacionId();

        //ignora las fechas ocupadas ya que se esta actualizando asi mismo
        List<Long> ocupadas = reservacionRepository.findHabitacionesOcupadasIgnorando(
                id, request.getFechaEntrada(), request.getFechaSalida());
        if (ocupadas.contains(habitacionId)) {
            return ResponseHelper.error(List.of("La habitación ya está reservada en esas fechas"));
        }

        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Habitacion habitacion = habitacionRepository.findById(habitacionId)
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada"));
        Estado estado = estadoRepository.findById(request.getEstadoId())
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

        long dias = ChronoUnit.DAYS.between(request.getFechaEntrada(), request.getFechaSalida());

        reservacion.setFechaEntrada(request.getFechaEntrada());
        reservacion.setFechaSalida(request.getFechaSalida());
        reservacion.setCostoTotal(dias * habitacion.getCosto());
        reservacion.setCliente(cliente);
        reservacion.setHabitacion(habitacion);
        reservacion.setEstado(estado);

        Reservacion saved = reservacionRepository.save(reservacion);
        return ResponseHelper.success(map(saved), "Reservación actualizada correctamente");
    }

    public GenericResponse<Void> delete(Long id) {
        if (!reservacionRepository.existsById(id)) {
            return ResponseHelper.error(List.of("Reservación no encontrada"));
        }
        reservacionRepository.deleteById(id);
        return ResponseHelper.success(null, "Reservación eliminada correctamente");
    }

    private ReservacionDto map(Reservacion reservacion) {
        ReservacionDto dto = new ReservacionDto();
        dto.setId(reservacion.getId());
        dto.setFechaEntrada(reservacion.getFechaEntrada());
        dto.setFechaSalida(reservacion.getFechaSalida());
        dto.setCostoTotal(reservacion.getCostoTotal());

        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setId(reservacion.getCliente().getId());
        clienteDto.setNombre(reservacion.getCliente().getNombre());
        dto.setCliente(clienteDto);

        HabitacionDto habitacionDto = new HabitacionDto();
        habitacionDto.setId(reservacion.getHabitacion().getId());
        habitacionDto.setNumero(reservacion.getHabitacion().getNumero());
        dto.setHabitacion(habitacionDto);

        EstadoDto estadoDto = new EstadoDto();
        estadoDto.setId(reservacion.getEstado().getId());
        estadoDto.setNombre(reservacion.getEstado().getNombre());
        dto.setEstado(estadoDto);

        return dto;
    }
}
