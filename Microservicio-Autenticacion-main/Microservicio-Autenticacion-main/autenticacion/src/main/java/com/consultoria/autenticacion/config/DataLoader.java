package com.consultoria.autenticacion.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.consultoria.autenticacion.model.Autenticacion;
import com.consultoria.autenticacion.repository.AutenticacionRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DataLoader {
    
    @Autowired
    private AutenticacionRepository repository;

    @PostConstruct
    public void cargarDatos() {

        if(repository.count() > 0){
            return;
        }

        Autenticacion u1 = new Autenticacion();
        u1.setUsername("juan.perez");
        u1.setEmail("juan@gmail.com");
        u1.setRut("11111111-1");
        u1.setRol("ADMIN");
        u1.setArea("SOPORTE");

        Autenticacion u2 = new Autenticacion();
        u2.setUsername("maria.lopez");
        u2.setEmail("maria@gmail.com");
        u2.setRut("22222222-2");
        u2.setRol("USER");
        u2.setArea("VENTAS");

        Autenticacion u3 = new Autenticacion();
        u3.setUsername("carlos.rojas");
        u3.setEmail("carlos@gmail.com");
        u3.setRut("33333333-3");
        u3.setRol("SUPERVISOR");
        u3.setArea("RRHH");

        Autenticacion u4 = new Autenticacion();
        u4.setUsername("ana.torres");
        u4.setEmail("ana@gmail.com");
        u4.setRut("44444444-4");
        u4.setRol("USER");
        u4.setArea("FINANZAS");

        Autenticacion u5 = new Autenticacion();
        u5.setUsername("pedro.soto");
        u5.setEmail("pedro@gmail.com");
        u5.setRut("55555555-5");
        u5.setRol("ADMIN");
        u5.setArea("TI");

        repository.saveAll(List.of(u1, u2, u3, u4, u5));

        System.out.println("Datos de autenticacion cargados correctamente");
    }
}
