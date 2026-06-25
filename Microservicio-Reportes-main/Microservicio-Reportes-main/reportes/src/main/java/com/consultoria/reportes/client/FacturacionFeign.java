package com.consultoria.reportes.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "facturacion-service", url = "http://localhost:8085")
public interface FacturacionFeign {

    @GetMapping("/facturas/pendientes")
    Integer obtenerPendientes();

    @GetMapping("/facturas/pagadas")
    Integer obtenerPagadas();

    @GetMapping("/facturas/ingresos")
    Double obtenerIngresos();

}
