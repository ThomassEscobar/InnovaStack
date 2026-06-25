package com.consultoria.clientes.controller;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

import com.consultoria.clientes.dto.ClienteDTO;
import com.consultoria.clientes.dto.ClienteResponseDTO;
import com.consultoria.clientes.service.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Mockeamos el servicio con el mismo tipo que usa tu controlador
    @MockitoBean
    private ClienteService servi;

    @Autowired
    private ObjectMapper objectMapper;

    private ClienteResponseDTO responseDTO;
    private ClienteDTO clienteDTO;

    @BeforeEach
    public void setUp() {
        responseDTO = new ClienteResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setNombreEmpresa("Tech Solutions");
        responseDTO.setNit(1001L);

        clienteDTO = new ClienteDTO(1L, "Tech Solutions", 1001L, "Tecnologia");
    }

    // ==========================================
    // 1. TESTS PARA GET /clientes (listar)
    // ==========================================
    @Test
    public void testListar_ConElementos() throws Exception {
        // Arrange
        when(servi.findTodos()).thenReturn(List.of(responseDTO));

        // Act & Assert
        mockMvc.perform(get("/clientes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombreEmpresa").value("Tech Solutions"));

        verify(servi, times(1)).findTodos();
    }

    @Test
    public void testListar_Vacio() throws Exception {
        // Arrange
        when(servi.findTodos()).thenReturn(List.of());

        // Act & Assert
        // Tu controlador devuelve 'noContent()' (204) si la lista está vacía
        mockMvc.perform(get("/clientes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(servi, times(1)).findTodos();
    }

    // ==========================================
    // 2. TEST PARA GET /clientes/{id}/full
    // ==========================================
    @Test
    public void testObtenerClienteCompleto_Exitoso() throws Exception {
        // Arrange
        when(servi.buscarxIdCompleto(1L)).thenReturn(clienteDTO);

        // Act & Assert
        mockMvc.perform(get("/clientes/1/full")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombreEmpresa").value("Tech Solutions"))
                .andExpect(jsonPath("$.sector").value("Tecnologia"));

        verify(servi, times(1)).buscarxIdCompleto(1L);
    }

    // ==========================================
    // 3. TESTS PARA POST /clientes (guardar)
    // ==========================================
    @Test
    public void testGuardar_Exitoso() throws Exception {
        // Arrange
        ClienteDTO dtoInput = new ClienteDTO(null, "Tech Solutions", 1001L, "Tecnologia");
        when(servi.guardar(any(ClienteDTO.class))).thenReturn(clienteDTO);

        // Act & Assert
        mockMvc.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dtoInput)))
                .andExpect(status().isOk()) // Devuelve 200 OK según tu código
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombreEmpresa").value("Tech Solutions"));

        verify(servi, times(1)).guardar(any(ClienteDTO.class));
    }

    @Test
    public void testGuardar_Error() throws Exception {
        // Arrange
        ClienteDTO dtoInput = new ClienteDTO(null, "Tech Solutions", 1001L, "Tecnologia");
        when(servi.guardar(any(ClienteDTO.class))).thenThrow(new RuntimeException("Error al guardar"));

        // Act & Assert
        // Tu catch en el controlador retorna 'noContent()' (204) en caso de excepción
        mockMvc.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dtoInput)))
                .andExpect(status().isNoContent());

        verify(servi, times(1)).guardar(any(ClienteDTO.class));
    }

    // ==========================================
    // 4. TESTS PARA GET /clientes/{id} (buscar)
    // ==========================================
    @Test
    public void testBuscar_Exitoso() throws Exception {
        // Arrange
        when(servi.buscarxId(1L)).thenReturn(responseDTO);

        // Act & Assert
        mockMvc.perform(get("/clientes/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombreEmpresa").value("Tech Solutions"));

        verify(servi, times(1)).buscarxId(1L);
    }

    @Test
    public void testBuscar_NoEncontrado() throws Exception {
        // Arrange
        when(servi.buscarxId(2L)).thenThrow(new RuntimeException("Cliente no encontrado"));

        // Act & Assert
        // El catch de tu método buscar() maneja la excepción retornando 'notFound()' (404)
        mockMvc.perform(get("/clientes/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(servi, times(1)).buscarxId(2L);
    }
}