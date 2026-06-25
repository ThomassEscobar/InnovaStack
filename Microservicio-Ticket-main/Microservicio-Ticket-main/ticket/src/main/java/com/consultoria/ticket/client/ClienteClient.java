package com.consultoria.ticket.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.consultoria.ticket.dto.ClienteDTO;

@FeignClient(name = "cliente-service",url= "http://localhost:8081" )
public interface ClienteClient {
 // Apunta al endpoint que SÍ tiene todos los datos
    @GetMapping("clientes/{id}") 
    ClienteDTO obtenerCliente(@PathVariable("id") Long id);

    @GetMapping("/{id}/full") 
    ClienteDTO obtenerClienteCompleto(@PathVariable("id") Long id);
}
