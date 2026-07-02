package com.prueba.springboot.gestion_hotel.models.DTOs;

import java.time.LocalDate;

public class ReservacionDto {
    private Long id;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private Double costoTotal;
    private ClienteDto cliente;
    private HabitacionDto habitacion;
    private EstadoDto estado;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getFechaEntrada() { return fechaEntrada; }
    public void setFechaEntrada(LocalDate fechaEntrada) { this.fechaEntrada = fechaEntrada; }

    public LocalDate getFechaSalida() { return fechaSalida; }
    public void setFechaSalida(LocalDate fechaSalida) { this.fechaSalida = fechaSalida; }

    public Double getCostoTotal() { return costoTotal; }
    public void setCostoTotal(Double costoTotal) { this.costoTotal = costoTotal; }

    public ClienteDto getCliente() { return cliente; }
    public void setCliente(ClienteDto cliente) { this.cliente = cliente; }

    public HabitacionDto getHabitacion() { return habitacion; }
    public void setHabitacion(HabitacionDto habitacion) { this.habitacion = habitacion; }

    public EstadoDto getEstado() { return estado; }
    public void setEstado(EstadoDto estado) { this.estado = estado; }
}
