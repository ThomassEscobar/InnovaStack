package com.consultoria.clientes.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consultoria.clientes.dto.ClienteDTO;
import com.consultoria.clientes.dto.ClienteResponseDTO;
import com.consultoria.clientes.model.Cliente;
import com.consultoria.clientes.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    public List<ClienteResponseDTO> findTodos() {
        return repo.findAll()
                .stream()
                .map(cli -> new ClienteResponseDTO(
                        cli.getId(),
                        cli.getNombreEmpresa(),
                        cli.getNit(),
                        cli.getSector()
                ))
                .collect(Collectors.toList());
    }

    public ClienteDTO guardar(ClienteDTO dto) {
        Cliente cli = new Cliente();
        cli.setId(dto.getId());
        cli.setNombreEmpresa(dto.getNombreEmpresa());
        cli.setNit(dto.getNit());
        cli.setSector(dto.getSector());

        Cliente guardar = repo.save(cli);

        return new ClienteDTO(
                guardar.getId(),
                guardar.getNombreEmpresa(),
                guardar.getNit(),
                guardar.getSector()
        );
    }

    public ClienteResponseDTO buscarxId(Long id) {
        Cliente cliente = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNombreEmpresa(),
                cliente.getNit(),
                cliente.getSector()
        );
    }

    public ClienteDTO buscarxIdCompleto(Long id) {
        Cliente cliente = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        return new ClienteDTO(
                cliente.getId(),
                cliente.getNombreEmpresa(),
                cliente.getNit(),
                cliente.getSector()
        );
    }
}