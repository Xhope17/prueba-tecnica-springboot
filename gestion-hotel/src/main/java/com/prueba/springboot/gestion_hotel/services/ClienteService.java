package com.prueba.springboot.gestion_hotel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.springboot.gestion_hotel.models.DTOs.ClienteDto;
import com.prueba.springboot.gestion_hotel.entities.Cliente;
import com.prueba.springboot.gestion_hotel.repositories.ClienteRepository;
import com.prueba.springboot.gestion_hotel.response.GenericResponse;
import com.prueba.springboot.gestion_hotel.response.ResponseHelper;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public GenericResponse<ClienteDto> save(ClienteDto dto) {
        Cliente cliente = new Cliente();
        cliente.setNombre(dto.getNombre());
        cliente.setCorreo(dto.getCorreo());
        cliente.setTelefono(dto.getTelefono());
        Cliente saved = clienteRepository.save(cliente);
        return ResponseHelper.success(map(saved), "Cliente creado correctamente");
    }

    public GenericResponse<List<ClienteDto>> getAll() {
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteDto> dtos = clientes.stream().map(c -> this.map(c)).toList();
        return ResponseHelper.success(dtos, "Clientes obtenidos correctamente", dtos.size());
    }

    public GenericResponse<ClienteDto> update(Long id, ClienteDto dto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        cliente.setNombre(dto.getNombre());
        cliente.setCorreo(dto.getCorreo());
        cliente.setTelefono(dto.getTelefono());

        Cliente saved = clienteRepository.save(cliente);
        return ResponseHelper.success(map(saved), "Cliente actualizado correctamente");
    }

    public GenericResponse<Void> delete(Long id) {
        if (!clienteRepository.existsById(id)) {
            return ResponseHelper.error(List.of("Cliente no encontrado"));
        }
        clienteRepository.deleteById(id);
        return ResponseHelper.success(null, "Cliente eliminado correctamente");
    }

    private ClienteDto map(Cliente cliente) {
        ClienteDto dto = new ClienteDto();
        dto.setId(cliente.getId());
        dto.setNombre(cliente.getNombre());
        dto.setCorreo(cliente.getCorreo());
        dto.setTelefono(cliente.getTelefono());
        return dto;
    }
}
