package com.consultoria.reuniones.config;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.consultoria.reuniones.model.Reuniones;
import com.consultoria.reuniones.repository.ReunionesRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DataLoader {

    @Autowired
    private ReunionesRepository repository;

    @PostConstruct
    public void cargarDatos() {

        if(repository.count() > 0){
            return;
        }

        Reuniones r1 = new Reuniones();
        r1.setProyectoId(1L);
        r1.setConsultorId(1L);
        r1.setFechaHora(new Date());
        r1.setLinkZoom("https://zoom.us/j/10001");

        Reuniones r2 = new Reuniones();
        r2.setProyectoId(2L);
        r2.setConsultorId(2L);
        r2.setFechaHora(new Date());
        r2.setLinkZoom("https://zoom.us/j/10002");

        Reuniones r3 = new Reuniones();
        r3.setProyectoId(3L);
        r3.setConsultorId(3L);
        r3.setFechaHora(new Date());
        r3.setLinkZoom("https://zoom.us/j/10003");

        Reuniones r4 = new Reuniones();
        r4.setProyectoId(4L);
        r4.setConsultorId(4L);
        r4.setFechaHora(new Date());
        r4.setLinkZoom("https://zoom.us/j/10004");

        Reuniones r5 = new Reuniones();
        r5.setProyectoId(5L);
        r5.setConsultorId(5L);
        r5.setFechaHora(new Date());
        r5.setLinkZoom("https://zoom.us/j/10005");

        repository.saveAll(List.of(r1, r2, r3, r4, r5));

        System.out.println("Datos de reuniones cargados correctamente");
    }
}
