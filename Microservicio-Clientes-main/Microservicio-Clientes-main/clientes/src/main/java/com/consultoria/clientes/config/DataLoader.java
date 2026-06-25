package com.consultoria.clientes.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.consultoria.clientes.model.Cliente;
import com.consultoria.clientes.repository.ClienteRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DataLoader {

    @Autowired
    private ClienteRepository repository;

    @PostConstruct
    public void cargarDatos() {

        // Evita duplicados
        if (repository.count() > 0) {
            return;
        }

        // CLIENTE 1
        Cliente c1 = new Cliente();
        c1.setNombreEmpresa("Tech Solutions");
        c1.setNit(1001L);
        c1.setSector("Tecnologia");

        // CLIENTE 2
        Cliente c2 = new Cliente();
        c2.setNombreEmpresa("InnovaSoft");
        c2.setNit(1002L);
        c2.setSector("Software");

        // CLIENTE 3
        Cliente c3 = new Cliente();
        c3.setNombreEmpresa("Global Finance");
        c3.setNit(1003L);
        c3.setSector("Finanzas");

        // CLIENTE 4
        Cliente c4 = new Cliente();
        c4.setNombreEmpresa("HealthCare Plus");
        c4.setNit(1004L);
        c4.setSector("Salud");

        // CLIENTE 5
        Cliente c5 = new Cliente();
        c5.setNombreEmpresa("Logistica Express");
        c5.setNit(1005L);
        c5.setSector("Logistica");

        repository.saveAll(List.of(c1, c2, c3, c4, c5));

        System.out.println("Datos de clientes cargados correctamente");
    }
}