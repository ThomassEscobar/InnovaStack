package com.consultoria.auditoria.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.consultoria.auditoria.model.Auditoria;
import com.consultoria.auditoria.service.AuditoriaService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AuditoriaController.class)
public class AuditoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuditoriaService service;

    @Autowired
    private ObjectMapper objectMapper;

    private Auditoria auditoria;

    @BeforeEach
    public void setUp() {
        auditoria = new Auditoria();
        auditoria.setId(1L);
        auditoria.setDestinatario("juan@gmail.com");
        auditoria.setCanal("EMAIL");
        auditoria.setAccion("FACTURA_ENVIADA");
        auditoria.setEstado("EXITOSO");
        auditoria.setTimestamp(LocalDateTime.now());
    }

    @Test
    public void testListar() throws Exception {
        List<Auditoria> lista = List.of(auditoria);

        when(service.listar()).thenReturn(lista);

        mockMvc.perform(get("/auditorias")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].destinatario").value("juan@gmail.com"));

        verify(service, times(1)).listar();
    }

    @Test
    public void testBuscarPorId() throws Exception {
        when(service.buscarPorId(1L)).thenReturn(auditoria);

        mockMvc.perform(get("/auditorias/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.accion").value("FACTURA_ENVIADA"));

        verify(service, times(1)).buscarPorId(1L);
    }

    @Test
    public void testGuardar() throws Exception {
        Auditoria nuevaAuditoria = new Auditoria();
        nuevaAuditoria.setDestinatario("juan@gmail.com");
        nuevaAuditoria.setCanal("EMAIL");
        nuevaAuditoria.setAccion("FACTURA_ENVIADA");
        nuevaAuditoria.setEstado("EXITOSO");
        nuevaAuditoria.setTimestamp(LocalDateTime.now());

        when(service.guardar(any(Auditoria.class))).thenReturn(auditoria);

        mockMvc.perform(post("/auditorias")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuevaAuditoria)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));

        verify(service, times(1)).guardar(any(Auditoria.class));
    }

    @Test
    public void testEliminar() throws Exception {
        doNothing().when(service).eliminar(1L);

        mockMvc.perform(delete("/auditorias/1"))
                .andExpect(status().isNoContent());

        verify(service, times(1)).eliminar(1L);
    }
}