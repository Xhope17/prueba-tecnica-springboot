package com.prueba.springboot.gestion_hotel.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prueba.springboot.gestion_hotel.entities.Reservacion;

public interface ReservacionRepository extends JpaRepository<Reservacion, Long> {

        List<Reservacion> findByClienteId(Long clienteId);

        List<Reservacion> findByHabitacionId(Long habitacionId);

        //solo para reservas nuevas
        @Query("SELECT r.habitacion.id FROM Reservacion r WHERE "
                        + "r.fechaEntrada < :fechaSalida AND r.fechaSalida > :fechaEntrada")
        List<Long> findHabitacionesOcupadas(@Param("fechaEntrada") LocalDate fechaEntrada,
                        @Param("fechaSalida") LocalDate fechaSalida);

        // si se tiene id
        @Query("SELECT r.habitacion.id FROM Reservacion r WHERE "
                        + "r.id <> :excluirId AND "
                        + "r.fechaEntrada < :fechaSalida AND r.fechaSalida > :fechaEntrada")
        List<Long> findHabitacionesOcupadasIgnorando(@Param("excluirId") Long excluirId,
                        @Param("fechaEntrada") LocalDate fechaEntrada,
                        @Param("fechaSalida") LocalDate fechaSalida);
}
