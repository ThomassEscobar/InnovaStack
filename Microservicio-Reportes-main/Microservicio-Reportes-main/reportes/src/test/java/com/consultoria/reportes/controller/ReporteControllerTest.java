package com.consultoria.reportes.controller;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

import com.consultoria.reportes.dto.DashBoardDTO;
import com.consultoria.reportes.service.ReporteService;

@WebMvcTest(ReporteController.class)
public class ReporteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ReporteService service;

    @Test
    @DisplayName("POST /reportes/generar -> debe generar reporte correctamente")
    public void testGenerarReporte() throws Exception {

        DashBoardDTO reporte = new DashBoardDTO(12, 75000.0, 5);

        when(service.generarReporte()).thenReturn(reporte);

        mockMvc.perform(post("/reportes/generar")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.totalProyectosActivos").value(12))
                .andExpect(jsonPath("$.ingresosMes").value(75000.0))
                .andExpect(jsonPath("$.ticketsPendientes").value(5));
    }

    @Test
    @DisplayName("GET /reportes/listar -> debe listar reportes correctamente")
    public void testListarReportes() throws Exception {

        List<DashBoardDTO> lista = List.of(
                new DashBoardDTO(12, 75000.0, 5),
                new DashBoardDTO(8, 50000.0, 3)
        );

        when(service.listar()).thenReturn(lista);

        mockMvc.perform(get("/reportes/listar")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].totalProyectosActivos").value(12))
                .andExpect(jsonPath("$[0].ingresosMes").value(75000.0))
                .andExpect(jsonPath("$[0].ticketsPendientes").value(5))
                .andExpect(jsonPath("$[1].totalProyectosActivos").value(8))
                .andExpect(jsonPath("$[1].ingresosMes").value(50000.0))
                .andExpect(jsonPath("$[1].ticketsPendientes").value(3));
    }
}