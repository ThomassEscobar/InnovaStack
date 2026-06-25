package com.consultoria.reuniones.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Date;
import java.util.List;

import com.consultoria.reuniones.client.ProyectoClient;
import com.consultoria.reuniones.dto.ProyectoDTO;
import com.consultoria.reuniones.dto.ReunionesDTO;
import com.consultoria.reuniones.dto.ReunionesResponseDTO;
import com.consultoria.reuniones.model.Reuniones;
import com.consultoria.reuniones.repository.ReunionesRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ReunionesServiceTest {

    @Mock
    private ReunionesRepository repo;

    @Mock
    private ProyectoClient client;

    @InjectMocks
    private ReunionesService service;

    private Reuniones reunion;
    private ProyectoDTO proyectoMock;
    private Date fechaPrueba;

    @BeforeEach
    public void setUp() {
        fechaPrueba = new Date();

        // Configuración de la entidad Reuniones
        reunion = new Reuniones();
        reunion.setId(1L);
        reunion.setProyectoId(101L);
        reunion.setConsultorId(50L);
        reunion.setFechaHora(fechaPrueba);
        reunion.setLinkZoom("https://zoom.us/j/123456789");

        // Configuración del mock de ProyectoDTO (Feign Client)
        proyectoMock = new ProyectoDTO();
        proyectoMock.setId(101L);
        proyectoMock.setClienteId(10L);
        proyectoMock.setNombre("Sistema de Auditoría");
        proyectoMock.setPresupuesto(50000); // Integer acorde al DTO que vimos
        proyectoMock.setFechaInicio(fechaPrueba);
    }

    // 1. TEST LISTAR
    @Test
    public void testListar() {
        // Arrange
        when(repo.findAll()).thenReturn(List.of(reunion));
        when(client.obtenerProyecto(101L)).thenReturn(proyectoMock);

        // Act
        List<ReunionesResponseDTO> resultado = service.listar();

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(1L, resultado.get(0).getId());
        assertEquals(101L, resultado.get(0).getProyectoId());
        assertEquals("https://zoom.us/j/123456789", resultado.get(0).getLinkZoom());
        assertNotNull(resultado.get(0).getProyecto());
        assertEquals("Sistema de Auditoría", resultado.get(0).getProyecto().getNombre());

        verify(repo, times(1)).findAll();
        verify(client, times(1)).obtenerProyecto(101L);
    }

    // 2. TEST GUARDAR
    @Test
    public void testGuardar() {
        // Arrange
        ReunionesDTO dtoInput = new ReunionesDTO();
        dtoInput.setId(null);
        dtoInput.setProyectoId(101L);
        dtoInput.setConsultorId(50L);
        dtoInput.setFechaHora(fechaPrueba);
        dtoInput.setLinkZoom("https://zoom.us/j/123456789");

        when(repo.save(any(Reuniones.class))).thenReturn(reunion);

        // Act
        ReunionesDTO resultado = service.guardar(dtoInput);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals(101L, resultado.getProyectoId());
        assertEquals(50L, resultado.getConsultorId());
        assertEquals("https://zoom.us/j/123456789", resultado.getLinkZoom());

        verify(repo, times(1)).save(any(Reuniones.class));
    }

    // 3. TEST BUSCAR POR PROYECTO ID
    @Test
    public void testBuscarxId() {
        // Arrange
        when(repo.findByProyectoId(101L)).thenReturn(List.of(reunion));
        when(client.obtenerProyecto(101L)).thenReturn(proyectoMock);

        // Act
        List<ReunionesResponseDTO> resultado = service.buscarxId(101L);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(1L, resultado.get(0).getId());
        assertNotNull(resultado.get(0).getProyecto());
        assertEquals("Sistema de Auditoría", resultado.get(0).getProyecto().getNombre());

        verify(repo, times(1)).findByProyectoId(101L);
        verify(client, times(1)).obtenerProyecto(101L);
    }
}