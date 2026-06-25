package com.consultoria.clientes.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import com.consultoria.clientes.dto.ClienteDTO;
import com.consultoria.clientes.dto.ClienteResponseDTO;
import com.consultoria.clientes.model.Cliente;
import com.consultoria.clientes.repository.ClienteRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository repo;

    @InjectMocks
    private ClienteService service;

    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombreEmpresa("Tech Solutions");
        cliente.setNit(1001L);
        cliente.setSector("Tecnologia");
    }

    // 1. TEST FIND TODOS
    @Test
    public void testFindTodos() {
        // Arrange
        when(repo.findAll()).thenReturn(List.of(cliente));

        // Act
        List<ClienteResponseDTO> resultado = service.findTodos();

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Tech Solutions", resultado.get(0).getNombreEmpresa());
        assertEquals(1001L, resultado.get(0).getNit());
        
        verify(repo, times(1)).findAll();
    }

    // 2. TEST GUARDAR
    @Test
    public void testGuardar() {
        // Arrange
        ClienteDTO dtoInput = new ClienteDTO(null, "Tech Solutions", 1001L, "Tecnologia");
        when(repo.save(any(Cliente.class))).thenReturn(cliente);

        // Act
        ClienteDTO resultado = service.guardar(dtoInput);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Tech Solutions", resultado.getNombreEmpresa());
        assertEquals("Tecnologia", resultado.getSector());

        verify(repo, times(1)).save(any(Cliente.class));
    }

    // 3. TEST BUSCAR POR ID (Caso Exitoso)
    @Test
    public void testBuscarxId_Exitoso() {
        // Arrange
        when(repo.findById(1L)).thenReturn(Optional.of(cliente));

        // Act
        ClienteResponseDTO resultado = service.buscarxId(1L);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Tech Solutions", resultado.getNombreEmpresa());

        verify(repo, times(1)).findById(1L);
    }

    // 4. TEST BUSCAR POR ID (Caso Error - RuntimeException)
    @Test
    public void testBuscarxId_NoEncontrado() {
        // Arrange
        when(repo.findById(2L)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.buscarxId(2L);
        });

        assertEquals("Cliente no encontrado", exception.getMessage());
        verify(repo, times(1)).findById(2L);
    }

    // 5. TEST BUSCAR POR ID COMPLETO (Caso Exitoso)
    @Test
    public void testBuscarxIdCompleto_Exitoso() {
        // Arrange
        when(repo.findById(1L)).thenReturn(Optional.of(cliente));

        // Act
        ClienteDTO resultado = service.buscarxIdCompleto(1L);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Tech Solutions", resultado.getNombreEmpresa());
        assertEquals("Tecnologia", resultado.getSector());

        verify(repo, times(1)).findById(1L);
    }

    // 6. TEST BUSCAR POR ID COMPLETO (Caso Error - RuntimeException)
    @Test
    public void testBuscarxIdCompleto_NoEncontrado() {
        // Arrange
        when(repo.findById(2L)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.buscarxIdCompleto(2L);
        });

        assertEquals("Cliente no encontrado", exception.getMessage());
        verify(repo, times(1)).findById(2L);
    }
}