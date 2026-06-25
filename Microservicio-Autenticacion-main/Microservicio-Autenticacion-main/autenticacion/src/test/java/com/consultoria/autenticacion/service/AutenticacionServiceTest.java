package com.consultoria.autenticacion.service;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.consultoria.autenticacion.DTOs.AutenticacionDTO;
import com.consultoria.autenticacion.model.Autenticacion;
import com.consultoria.autenticacion.repository.AutenticacionRepository;

@ExtendWith(MockitoExtension.class)
class AutenticacionServiceTest {

    @Mock
    private AutenticacionRepository repo;

    @InjectMocks
    private AutenticacionService service;

    @Test
    void findAll_ok() {
        Autenticacion au = new Autenticacion();
        au.setUsername("test");
        au.setRol("ADMIN");
        au.setArea("TI");

        when(repo.findAll()).thenReturn(List.of(au));

        var result = service.findAll();

        assertEquals(1, result.size());
        assertEquals("test", result.get(0).getUsername());
        assertEquals("ADMIN", result.get(0).getRol());
        assertEquals("TI", result.get(0).getArea());
    }

    @Test
    void save_ok() {
        AutenticacionDTO dto = new AutenticacionDTO(
            1L, "user", "mail@test.com", "123", "ADMIN", "TI"
        );

        Autenticacion saved = new Autenticacion();
        saved.setId(1L);
        saved.setUsername("user");
        saved.setEmail("mail@test.com");
        saved.setRut("123");
        saved.setRol("ADMIN");
        saved.setArea("TI");

        when(repo.save(any(Autenticacion.class))).thenReturn(saved);

        AutenticacionDTO result = service.sabe(dto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("user", result.getUsername());
        assertEquals("mail@test.com", result.getEmail());
        assertEquals("123", result.getRut());
        assertEquals("ADMIN", result.getRol());
        assertEquals("TI", result.getArea());

        verify(repo, times(1)).save(any(Autenticacion.class));
    }

    @Test
    void findByUser_ok() {
        Autenticacion au = new Autenticacion();
        au.setUsername("john");
        au.setRol("USER");
        au.setArea("FIN");

        when(repo.findByUsername("john")).thenReturn(List.of(au));

        var result = service.findByUser("john");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("john", result.get(0).getUsername());
        assertEquals("USER", result.get(0).getRol());
        assertEquals("FIN", result.get(0).getArea());
    }
}