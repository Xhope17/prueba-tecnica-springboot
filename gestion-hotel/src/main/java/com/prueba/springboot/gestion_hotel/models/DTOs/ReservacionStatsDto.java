package com.prueba.springboot.gestion_hotel.models.DTOs;

public class ReservacionStatsDto {

    private int anio;
    private int mes;
    private double promedio;

    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }

    public int getMes() { return mes; }
    public void setMes(int mes) { this.mes = mes; }

    public double getPromedio() { return promedio; }
    public void setPromedio(double promedio) { this.promedio = promedio; }
}
