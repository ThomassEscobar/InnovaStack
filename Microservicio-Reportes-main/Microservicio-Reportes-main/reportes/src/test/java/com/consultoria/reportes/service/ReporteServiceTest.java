package com.consultoria.reportes.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import com.consultoria.reportes.client.FacturacionFeign;
import com.consultoria.reportes.dto.DashBoardDTO;
import com.consultoria.reportes.model.Reportes;
import com.consultoria.reportes.repository.ReporteRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ReporteServiceTest {

    @Mock
    private ReporteRepository repo;

    @Mock
    private FacturacionFeign feign;

    @InjectMocks
    private ReporteService service;

    private Reportes reporteMock;

    @BeforeEach
    public void setUp() {
        reporteMock = new Reportes();
        reporteMock.setId(1L);
        reporteMock.setTicketsPendientes(5);
        reporteMock.setTotalProyectosActivos(12);
        reporteMock.setIngresosMes(75000.0);
    }

    // 1. TEST GENERAR REPORTE (Con llamadas externas Feign)
    @Test
    public void testGenerarReporte() {
        // Arrange
        when(feign.obtenerPendientes()).thenReturn(5);
        when(feign.obtenerPagadas()).thenReturn(12);
        when(feign.obtenerIngresos()).thenReturn(75000.0);
        
        when(repo.save(any(Reportes.class))).thenReturn(reporteMock);

        // Act
        DashBoardDTO resultado = service.generarReporte();

        // Assert
        assertNotNull(resultado);
        assertEquals(12, resultado.getTotalProyectosActivos());
        assertEquals(75000.0, resultado.getIngresosMes());
        assertEquals(5, resultado.getTicketsPendientes());

        // Verificar interacciones con el cliente Feign y el repositorio
        verify(feign, times(1)).obtenerPendientes();
        verify(feign, times(1)).obtenerPagadas();
        verify(feign, times(1)).obtenerIngresos();
        verify(repo, times(1)).save(any(Reportes.class));
    }

    // 2. TEST LISTAR REPORTES HISTÓRICOS
    @Test
    public void testListar() {
        // Arrange
        when(repo.findAll()).thenReturn(List.of(reporteMock));

        // Act
        List<DashBoardDTO> resultado = service.listar();

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(12, resultado.get(0).getTotalProyectosActivos());
        assertEquals(75000.0, resultado.get(0).getIngresosMes());
        assertEquals(5, resultado.get(0).getTicketsPendientes());

        verify(repo, times(1)).findAll();
    }
}