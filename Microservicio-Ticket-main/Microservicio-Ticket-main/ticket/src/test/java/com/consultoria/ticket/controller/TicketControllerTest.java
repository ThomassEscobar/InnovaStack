package com.consultoria.ticket.controller;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.consultoria.ticket.dto.ClienteDTO;
import com.consultoria.ticket.dto.TicketDTO;
import com.consultoria.ticket.dto.TicketResponseDTO;
import com.consultoria.ticket.service.TicketService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(TicketController.class)
public class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TicketService servi;

    @Autowired
    private ObjectMapper objectMapper;

    private TicketDTO ticketDTO;
    private TicketResponseDTO ticketResponseDTO;

    @BeforeEach
    void setUp() {

        Date fecha = new Date();

        // =========================
        // TicketDTO (SIN CONSTRUCTOR)
        // =========================
        ticketDTO = new TicketDTO();
        ticketDTO.setId(1L);
        ticketDTO.setClienteId(1L);
        ticketDTO.setAsunto("Problema Login");
        ticketDTO.setDescripcion("No puede acceder");
        ticketDTO.setPrioridad("Alta");
        ticketDTO.setFechaCreacion(fecha);

        // =========================
        // ClienteDTO
        // =========================
        ClienteDTO cliente = new ClienteDTO();
        cliente.setId(1L);
        cliente.setNombreEmpresa("Empresa Test");
        cliente.setNit(123456789L);
        cliente.setSector("Tecnologia");
        cliente.setDireccion("Santiago");
        cliente.setEmailEmpresa("test@empresa.com");

        // =========================
        // TicketResponseDTO
        // =========================
        ticketResponseDTO = new TicketResponseDTO();
        ticketResponseDTO.setClienteId(1L);
        ticketResponseDTO.setAsunto("Problema Login");
        ticketResponseDTO.setDescripcion("No puede acceder");
        ticketResponseDTO.setPrioridad("Alta");
        ticketResponseDTO.setFechaCreacion(fecha);
        ticketResponseDTO.setCliente(cliente);
    }

    // =========================
    // LISTAR
    // =========================
    @Test
    void listar_ok() throws Exception {
        when(servi.findTodos()).thenReturn(List.of(ticketResponseDTO));

        mockMvc.perform(get("/tickets"))
                .andExpect(status().isOk());
    }

    @Test
    void listar_notFound() throws Exception {
        when(servi.findTodos()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/tickets"))
                .andExpect(status().isNotFound());
    }

    // =========================
    // GUARDAR
    // =========================
    @Test
    void guardar_ok() throws Exception {
        when(servi.guardar(any(TicketDTO.class))).thenReturn(ticketDTO);

        mockMvc.perform(post("/tickets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ticketDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void guardar_noContent() throws Exception {
        when(servi.guardar(any(TicketDTO.class)))
                .thenThrow(new RuntimeException());

        mockMvc.perform(post("/tickets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ticketDTO)))
                .andExpect(status().isNoContent());
    }

    // =========================
    // BUSCAR POR ID
    // =========================
    @Test
    void buscar_ok() throws Exception {
        when(servi.buscarxId(1L)).thenReturn(List.of(ticketResponseDTO));

        mockMvc.perform(get("/tickets/1"))
                .andExpect(status().isOk());
    }

    @Test
    void buscar_notFound() throws Exception {
        when(servi.buscarxId(1L)).thenThrow(new RuntimeException());

        mockMvc.perform(get("/tickets/1"))
                .andExpect(status().isNotFound());
    }

    // =========================
    // FULL
    // =========================
    @Test
    void obtenerTicketFull_ok() throws Exception {
        when(servi.obtenerDetalleCompleto(1L)).thenReturn(ticketResponseDTO);

        mockMvc.perform(get("/tickets/1/full"))
                .andExpect(status().isOk());
    }

    @Test
    void obtenerTicketFull_notFound() throws Exception {
        when(servi.obtenerDetalleCompleto(1L)).thenThrow(new RuntimeException());

        mockMvc.perform(get("/tickets/1/full"))
                .andExpect(status().isNotFound());
    }
}