package com.consultoria.reportes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.consultoria.reportes.dto.DashBoardDTO;
import com.consultoria.reportes.service.ReporteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/reportes")
@Tag(name = "Reportes y Métricas", description = "Operaciones para la consolidación, generación y lectura de tableros de control (Dashboards)")
public class ReporteController {

    @Autowired
    private ReporteService service;

    @Operation(summary = "Generar un nuevo reporte de Dashboard", description = "Consolida las métricas e indicadores de negocio actuales y crea un nuevo registro de Dashboard en el sistema")
    @PostMapping("/generar")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Reporte de Dashboard consolidado y generado con éxito"),
        @ApiResponse(responseCode = "500", description = "Error interno al procesar o compilar las métricas")
    })
    public ResponseEntity<DashBoardDTO> generar() {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.generarReporte());
    }

    @Operation(summary = "Listar reportes consolidados", description = "Retorna el histórico completo de todos los tableros de control (Dashboards) almacenados")
    @GetMapping("/listar")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Histórico de Dashboards recuperado con éxito"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<DashBoardDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }
}