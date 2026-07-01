package com.prueba.springboot.gestion_hotel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.springboot.gestion_hotel.models.Cliente;
import com.prueba.springboot.gestion_hotel.repositories.ClienteRepository;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.save(cliente);

    }

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }
}
