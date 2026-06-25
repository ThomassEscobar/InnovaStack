package com.consultoria.auditoria.config;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.consultoria.auditoria.model.Auditoria;
import com.consultoria.auditoria.repository.AuditoriaRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DataLoader {

    @Autowired
    private AuditoriaRepository repository;

    @PostConstruct
    public void cargarDatos() {

        // Evita duplicados
        if (repository.count() > 0) {
            return;
        }

        Auditoria a1 = new Auditoria();
        a1.setDestinatario("juan@gmail.com");
        a1.setCanal("EMAIL");
        a1.setAccion("FACTURA_ENVIADA");
        a1.setEstado("EXITOSO");
        a1.setTimestamp(LocalDateTime.now());

        Auditoria a2 = new Auditoria();
        a2.setDestinatario("maria@gmail.com");
        a2.setCanal("SMS");
        a2.setAccion("REUNION_PROGRAMADA");
        a2.setEstado("EXITOSO");
        a2.setTimestamp(LocalDateTime.now());

        Auditoria a3 = new Auditoria();
        a3.setDestinatario("carlos@gmail.com");
        a3.setCanal("EMAIL");
        a3.setAccion("TICKET_CREADO");
        a3.setEstado("FALLIDO");
        a3.setTimestamp(LocalDateTime.now());

        Auditoria a4 = new Auditoria();
        a4.setDestinatario("ana@gmail.com");
        a4.setCanal("WHATSAPP");
        a4.setAccion("PROYECTO_ACTUALIZADO");
        a4.setEstado("EXITOSO");
        a4.setTimestamp(LocalDateTime.now());

        Auditoria a5 = new Auditoria();
        a5.setDestinatario("pedro@gmail.com");
        a5.setCanal("EMAIL");
        a5.setAccion("PAGO_CONFIRMADO");
        a5.setEstado("EXITOSO");
        a5.setTimestamp(LocalDateTime.now());

        repository.saveAll(List.of(a1, a2, a3, a4, a5));

        System.out.println("Datos de auditoria cargados correctamente");
    }
}