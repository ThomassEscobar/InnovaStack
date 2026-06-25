package com.consultoria.ticket.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consultoria.ticket.client.ClienteClient;
import com.consultoria.ticket.dto.ClienteDTO;
import com.consultoria.ticket.dto.TicketDTO;
import com.consultoria.ticket.dto.TicketResponseDTO;
import com.consultoria.ticket.model.Ticket;
import com.consultoria.ticket.repository.TicketRepository;
@Service
public class TicketService {

    @Autowired
    private TicketRepository repo;

    @Autowired
    private ClienteClient client;


    public List<TicketResponseDTO> findTodos(){
        return repo.findAll()
                .stream()
                .map(ti ->{
                    ClienteDTO cliente = client.obtenerCliente(ti.getClienteId());
                    return new TicketResponseDTO(
                    ti.getClienteId(),
                    ti.getAsunto(),
                    ti.getDescripcion(),
                    ti.getPrioridad(),
                    ti.getFechaCreacion(),
                    cliente
                    );
                }).collect(Collectors.toList());
    }
    public TicketDTO guardar(TicketDTO dto){
        Ticket ti = new Ticket();
        ti.setId(dto.getId());
        ti.setClienteId(dto.getClienteId());
        ti.setAsunto(dto.getAsunto());
        ti.setDescripcion(dto.getDescripcion());
        ti.setPrioridad(dto.getPrioridad());
        ti.setFechaCreacion(dto.getFechaCreacion());
        Ticket guardar = repo.save(ti);
        return new TicketDTO(
            guardar.getId(),
            guardar.getClienteId(),
            guardar.getAsunto(),
            guardar.getDescripcion(),
            guardar.getPrioridad(),
            guardar.getFechaCreacion()

        );
    }
    public List<TicketResponseDTO> buscarxId(Long clienteId){
        List<Ticket> listar = repo.findByClienteId(clienteId);
        ClienteDTO cliente = client.obtenerCliente(clienteId);
        return listar.stream().map(ticket ->
            new TicketResponseDTO(
                ticket.getClienteId(),
                ticket.getAsunto(),
                ticket.getDescripcion(),
                ticket.getPrioridad(),
                ticket.getFechaCreacion(),
                cliente
            )
        ).toList();
    }
    public TicketResponseDTO obtenerDetalleCompleto(Long ticketId) {
        Ticket ticket = repo.findById(ticketId).orElseThrow();

        ClienteDTO clienteFull = client.obtenerClienteCompleto(ticket.getClienteId());

        TicketResponseDTO response = new TicketResponseDTO();
        response.setAsunto(ticket.getAsunto());
        response.setDescripcion(ticket.getDescripcion());
        response.setPrioridad(ticket.getPrioridad());
        response.setFechaCreacion(ticket.getFechaCreacion());
        response.setClienteId(ticket.getClienteId());
        response.setCliente(clienteFull); 

        return response;
    }

   
}
