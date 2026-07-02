package com.prueba.springboot.gestion_hotel.repositories;

import com.prueba.springboot.gestion_hotel.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    
}

