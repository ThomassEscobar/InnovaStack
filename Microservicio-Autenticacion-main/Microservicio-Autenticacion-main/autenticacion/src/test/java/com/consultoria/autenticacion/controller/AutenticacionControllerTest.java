package com.consultoria.autenticacion.controller;

import java.util.List;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.consultoria.autenticacion.DTOs.AutenticacionDTO;
import com.consultoria.autenticacion.DTOs.AutenticacionResponseDTO;
import com.consultoria.autenticacion.service.AutenticacionService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AutenticacionController.class)
class AutenticacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AutenticacionService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void listar_ok() throws Exception {

        when(service.findAll()).thenReturn(
                List.of(new AutenticacionResponseDTO("user", "ADMIN", "TI"))
        );

        mockMvc.perform(get("/autenticacion"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("user"))
                .andExpect(jsonPath("$[0].rol").value("ADMIN"))
                .andExpect(jsonPath("$[0].area").value("TI"));
    }

    @Test
    void listar_empty_404() throws Exception {
        when(service.findAll()).thenReturn(List.of());

        mockMvc.perform(get("/autenticacion"))
                .andExpect(status().isNotFound());
    }

    @Test
    void save_ok() throws Exception {

        AutenticacionDTO dto = new AutenticacionDTO(
                1L, "user", "mail@test.com", "123", "ADMIN", "TI"
        );

        when(service.sabe(any())).thenReturn(dto);

        mockMvc.perform(post("/autenticacion")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("user"));
    }

    @Test
    void buscar_por_username_ok() throws Exception {

        when(service.findByUser("john")).thenReturn(
                List.of(new AutenticacionResponseDTO("john", "ADMIN", "TI"))
        );

        mockMvc.perform(get("/autenticacion/john"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("john"));
    }
}