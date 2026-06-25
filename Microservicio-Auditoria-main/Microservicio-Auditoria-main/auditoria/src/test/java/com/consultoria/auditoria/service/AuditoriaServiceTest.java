package com.consultoria.auditoria.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.consultoria.auditoria.model.Auditoria;
import com.consultoria.auditoria.repository.AuditoriaRepository;

@ExtendWith(MockitoExtension.class)
public class AuditoriaServiceTest {

    @Mock
    private AuditoriaRepository repository;

    @InjectMocks
    private AuditoriaService service;

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

    // 1. TEST LISTAR
    @Test
    public void testListar() {
        // Arrange
        List<Auditoria> lista = List.of(auditoria);
        when(repository.findAll()).thenReturn(lista);

        // Act
        List<Auditoria> resultado = service.listar();

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(1L, resultado.get(0).getId());
        assertEquals("juan@gmail.com", resultado.get(0).getDestinatario());
        
        verify(repository, times(1)).findAll();
    }

    // 2. TEST BUSCAR POR ID (Caso Exitoso)
    @Test
    public void testBuscarPorId_Exitoso() {
        // Arrange
        when(repository.findById(1L)).thenReturn(Optional.of(auditoria));

        // Act
        // Nota: Si tu método devuelve un Optional o la entidad directamente, adáptalo aquí:
        Optional<Auditoria> resultado = Optional.ofNullable(service.buscarPorId(1L)); 

        // Assert
        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
        assertEquals("FACTURA_ENVIADA", resultado.get().getAccion());
        
        verify(repository, times(1)).findById(1L);
    }

    // 3. TEST BUSCAR POR ID (Caso No Encontrado)
    @Test
    public void testBuscarPorId_NoEncontrado() {
        // Arrange
        when(repository.findById(2L)).thenReturn(Optional.empty());

        // Act
        Auditoria resultado = service.buscarPorId(2L);

        // Assert
        assertNull(resultado);
        verify(repository, times(1)).findById(2L);
    }

    // 4. TEST GUARDAR / CREAR
    @Test
    public void testGuardar() {
        // Arrange
        Auditoria nuevaAuditoria = new Auditoria();
        nuevaAuditoria.setDestinatario("maria@gmail.com");
        nuevaAuditoria.setCanal("SMS");
        nuevaAuditoria.setAccion("REUNION_PROGRAMADA");
        nuevaAuditoria.setEstado("EXITOSO");

        Auditoria auditoriaGuardada = new Auditoria();
        auditoriaGuardada.setId(2L);
        auditoriaGuardada.setDestinatario("maria@gmail.com");
        auditoriaGuardada.setCanal("SMS");
        auditoriaGuardada.setAccion("REUNION_PROGRAMADA");
        auditoriaGuardada.setEstado("EXITOSO");

        when(repository.save(any(Auditoria.class))).thenReturn(auditoriaGuardada);

        // Act
        Auditoria resultado = service.guardar(nuevaAuditoria);

        // Assert
        assertNotNull(resultado);
        assertEquals(2L, resultado.getId());
        assertEquals("maria@gmail.com", resultado.getDestinatario());
        assertEquals("SMS", resultado.getCanal());
        
        verify(repository, times(1)).save(nuevaAuditoria);
    }

    // 5. TEST ELIMINAR
    @Test
    public void testEliminar() {
        // Arrange
        Long idEliminar = 1L;
        doNothing().when(repository).deleteById(idEliminar);

        // Act
        service.eliminar(idEliminar);

        // Assert
        verify(repository, times(1)).deleteById(idEliminar);
    }
}