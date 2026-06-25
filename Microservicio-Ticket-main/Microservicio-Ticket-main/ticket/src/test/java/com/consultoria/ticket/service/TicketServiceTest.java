package com.consultoria.ticket.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.consultoria.ticket.client.ClienteClient;
import com.consultoria.ticket.dto.ClienteDTO;
import com.consultoria.ticket.dto.TicketDTO;
import com.consultoria.ticket.dto.TicketResponseDTO;
import com.consultoria.ticket.model.Ticket;
import com.consultoria.ticket.repository.TicketRepository;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

    @Mock
    private TicketRepository repo;

    @Mock
    private ClienteClient client;

    @InjectMocks
    private TicketService service;

    private Ticket ticket;
    private TicketDTO ticketDTO;
    private ClienteDTO cliente;

    @BeforeEach
    void setUp() {
        Date fecha = new Date();

        ticket = new Ticket(
                1L,
                1L,
                "Problema Login",
                "No puede acceder",
                "Alta",
                fecha
        );

        ticketDTO = new TicketDTO(
                1L,
                1L,
                "Problema Login",
                "No puede acceder",
                "Alta",
                fecha
        );

        cliente = new ClienteDTO(
                1L,
                "Empresa Test",
                123456789L,
                "Tecnologia",
                "Santiago",
                "test@empresa.com"
        );
    }

    @Test
    void testFindTodos() {
        when(repo.findAll()).thenReturn(List.of(ticket));
        when(client.obtenerCliente(1L)).thenReturn(cliente);

        List<TicketResponseDTO> resultado = service.findTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Problema Login", resultado.get(0).getAsunto());
    }

    @Test
    void testGuardar() {
        when(repo.save(any(Ticket.class))).thenReturn(ticket);

        TicketDTO resultado = service.guardar(ticketDTO);

        assertNotNull(resultado);
        assertEquals(ticket.getId(), resultado.getId());
        assertEquals(ticket.getAsunto(), resultado.getAsunto());
        assertEquals(ticket.getDescripcion(), resultado.getDescripcion());
    }

    @Test
    void testBuscarxId() {
        when(repo.findByClienteId(1L)).thenReturn(List.of(ticket));
        when(client.obtenerCliente(1L)).thenReturn(cliente);

        List<TicketResponseDTO> resultado = service.buscarxId(1L);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Alta", resultado.get(0).getPrioridad());
    }

    @Test
    void testObtenerDetalleCompleto() {
        when(repo.findById(1L)).thenReturn(Optional.of(ticket));
        when(client.obtenerClienteCompleto(1L)).thenReturn(cliente);

        TicketResponseDTO resultado = service.obtenerDetalleCompleto(1L);

        assertNotNull(resultado);
        assertEquals("Problema Login", resultado.getAsunto());
        assertEquals(cliente, resultado.getCliente());
    }
}