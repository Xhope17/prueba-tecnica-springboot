package com.prueba.springboot.gestion_hotel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.springboot.gestion_hotel.models.DTOs.ReservacionStatsDto;
import com.prueba.springboot.gestion_hotel.repositories.EstadisticasRepository;
import com.prueba.springboot.gestion_hotel.response.GenericResponse;
import com.prueba.springboot.gestion_hotel.response.ResponseHelper;

@Service
public class EstadisticasService {

    @Autowired
    private EstadisticasRepository estadisticasRepository;

    public GenericResponse<List<ReservacionStatsDto>> getCantidadPorMesYAnio(Integer anio, Integer mes) {
        if (anio == null && mes == null) {
            return ResponseHelper.error(List.of("Debe enviar año o mes"));
        }

        List<Object[]> datos = estadisticasRepository.countByMesYAnio(anio, mes);

        if (datos.isEmpty()) {
            return ResponseHelper.error(List.of("No se encontraron reservaciones para el filtro indicado"));
        }

        double promedio = anio != null && mes != null
                ? cantidadDe(datos.get(0))
                : datos.stream().mapToDouble(r -> cantidadDe(r)).average().orElse(0);

        ReservacionStatsDto dto = new ReservacionStatsDto();
        dto.setAnio(anio != null ? anio : 0);
        dto.setMes(mes != null ? mes : 0);
        dto.setPromedio(promedio);

        return ResponseHelper.success(List.of(dto), "Promedio calculado correctamente", 1);
    }

    public GenericResponse<List<ReservacionStatsDto>> getCostoPromedioPorMesYAnio(Integer anio, Integer mes) {
        if (anio == null && mes == null) {
            return ResponseHelper.error(List.of("Debe enviar año o mes"));
        }

        List<Object[]> datos = estadisticasRepository.avgCostoByMesYAnio(anio, mes);

        if (datos.isEmpty()) {
            return ResponseHelper.error(List.of("No se encontraron reservaciones para el filtro indicado"));
        }

        List<ReservacionStatsDto> dtos = datos.stream().map(r -> {
            ReservacionStatsDto dto = new ReservacionStatsDto();
            dto.setAnio(anioDe(r));
            dto.setMes(mesDe(r));
            dto.setPromedio(costoDe(r));
            return dto;
        }).toList();

        return ResponseHelper.success(dtos, "Costo promedio de reservaciones por mes y año", dtos.size());
    }

    private int anioDe(Object[] fila) {
        

        return ((Number) fila[0]).intValue();
    

    }
        
    private int mesDe(Object[] fila) {
        return ((Number) fila[1]).intValue();
    }

    private double cantidadDe(Object[] fila) {
        return ((Number) fila[2]).doubleValue();
    }

    private double costoDe(Object[] fila) {
        return ((Number) fila[2]).doubleValue();
    }
}
