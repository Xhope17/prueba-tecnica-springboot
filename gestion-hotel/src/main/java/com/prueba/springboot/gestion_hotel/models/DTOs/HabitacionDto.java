package com.prueba.springboot.gestion_hotel.models.DTOs;

public class HabitacionDto {
    private Long id;
    private String numero;
    private String descripcion;
    private Integer capacidad;
    private Double costo;
    private TipoHabitacionDto tipo;
    private EstadoDto estado;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Integer getCapacidad() { return capacidad; }
    public void setCapacidad(Integer capacidad) { this.capacidad = capacidad; }

    public Double getCosto() { return costo; }
    public void setCosto(Double costo) { this.costo = costo; }

    public TipoHabitacionDto getTipo() { return tipo; }
    public void setTipo(TipoHabitacionDto tipo) { this.tipo = tipo; }

    public EstadoDto getEstado() { return estado; }
    public void setEstado(EstadoDto estado) { this.estado = estado; }
}
