package com.consultoria.reuniones.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.consultoria.reuniones.dto.ProyectoDTO;

@FeignClient(name="proyecto-service",url="http://localhost:8083")
public interface ProyectoClient {
    @GetMapping("proyectos/{id}")
    ProyectoDTO obtenerProyecto(@PathVariable ("id") Long id);

}
