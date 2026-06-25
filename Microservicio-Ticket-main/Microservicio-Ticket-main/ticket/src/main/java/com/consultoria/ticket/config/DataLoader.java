package com.consultoria.ticket.config;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.consultoria.ticket.model.Ticket;
import com.consultoria.ticket.repository.TicketRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DataLoader {

    @Autowired
    private TicketRepository repository;

    @PostConstruct
    public void cargarDatos() {

        if(repository.count() > 0){
            return;
        }

        Ticket t1 = new Ticket();
        t1.setClienteId(1L);
        t1.setAsunto("Error login");
        t1.setDescripcion("No se puede iniciar sesion");
        t1.setPrioridad("ALTA");
        t1.setFechaCreacion(new Date());

        Ticket t2 = new Ticket();
        t2.setClienteId(2L);
        t2.setAsunto("Problema pago");
        t2.setDescripcion("Falla en pago con tarjeta");
        t2.setPrioridad("MEDIA");
        t2.setFechaCreacion(new Date());

        Ticket t3 = new Ticket();
        t3.setClienteId(3L);
        t3.setAsunto("Error interfaz");
        t3.setDescripcion("La pagina se ve desordenada");
        t3.setPrioridad("BAJA");
        t3.setFechaCreacion(new Date());

        Ticket t4 = new Ticket();
        t4.setClienteId(4L);
        t4.setAsunto("Servidor lento");
        t4.setDescripcion("El sistema responde lentamente");
        t4.setPrioridad("ALTA");
        t4.setFechaCreacion(new Date());

        Ticket t5 = new Ticket();
        t5.setClienteId(5L);
        t5.setAsunto("Acceso denegado");
        t5.setDescripcion("No tiene permisos suficientes");
        t5.setPrioridad("MEDIA");
        t5.setFechaCreacion(new Date());

        repository.saveAll(List.of(t1, t2, t3, t4, t5));

        System.out.println("Datos de tickets cargados correctamente");
    }
}
