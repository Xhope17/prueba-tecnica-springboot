package com.prueba.springboot.gestion_hotel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prueba.springboot.gestion_hotel.entities.Reservacion;

public interface EstadisticasRepository extends JpaRepository<Reservacion, Long> {

    @Query(value = "SELECT YEAR(fecha_entrada), MONTH(fecha_entrada), COUNT(*) "
            + "FROM reservaciones "
            + "WHERE (:anio IS NULL OR YEAR(fecha_entrada) = :anio) "
            + "AND (:mes IS NULL OR MONTH(fecha_entrada) = :mes) "
            + "GROUP BY YEAR(fecha_entrada), MONTH(fecha_entrada) "
            + "ORDER BY YEAR(fecha_entrada), MONTH(fecha_entrada)", nativeQuery = true)
    List<Object[]> countByMesYAnio(@Param("anio") Integer anio, @Param("mes") Integer mes);

    @Query(value = "SELECT YEAR(fecha_entrada), MONTH(fecha_entrada), AVG(costo_total) "
            + "FROM reservaciones "
            + "WHERE (:anio IS NULL OR YEAR(fecha_entrada) = :anio) "
            + "AND (:mes IS NULL OR MONTH(fecha_entrada) = :mes) "
            + "GROUP BY YEAR(fecha_entrada), MONTH(fecha_entrada) "
            + "ORDER BY YEAR(fecha_entrada), MONTH(fecha_entrada)", nativeQuery = true)
    List<Object[]> avgCostoByMesYAnio(@Param("anio") Integer anio, @Param("mes") Integer mes);
}
