package com.consultoria.reuniones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consultoria.reuniones.dto.ReunionesResponseDTO;
import com.consultoria.reuniones.service.ReunionesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/reuniones")
@Tag(name = "Reuniones", description = "Operaciones sobre Reuniones y Agendamientos")
public class ReunionesController {

    @Autowired
    private ReunionesService servi;

    @Operation(summary = "Busca todas las reuniones", description = "Retorna el listado completo de reuniones con sus datos de proyecto asociados")
    @GetMapping
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reuniones encontradas con éxito"),
        @ApiResponse(responseCode = "204", description = "No hay contenido de reuniones actualmente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<ReunionesResponseDTO>> listar(){
        List<ReunionesResponseDTO> si = servi.listar();
        if(si.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(si);
    }

    @Operation(summary = "Otorga reuniones por ID de Proyecto", description = "Busca y retorna un listado de reuniones agendadas asociadas al ID de un proyecto específico")
    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Listado de reuniones del proyecto encontrado"),
        @ApiResponse(responseCode = "404", description = "Proyecto o reuniones no encontradas"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<ReunionesResponseDTO>> buscarxProyectoId(
        @PathVariable 
        @Parameter(description = "ID del Proyecto", required = true, examples = @ExampleObject(value = "101")) Long id){
        try {
            List<ReunionesResponseDTO> si = servi.buscarxId(id);
            return ResponseEntity.ok(si);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}