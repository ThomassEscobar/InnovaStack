package com.consultoria.autenticacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consultoria.autenticacion.DTOs.AutenticacionDTO;
import com.consultoria.autenticacion.DTOs.AutenticacionResponseDTO;
import com.consultoria.autenticacion.service.AutenticacionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
@RequestMapping("/autenticacion")
@RestController
@Tag(name = "Autenticacion", description = "Operaciones sobre Autenticacion")
public class AutenticacionController {

    @Autowired
    private AutenticacionService servi;
    @Operation(summary = "Lista Autenticaciones", description = "Retorna Todas Las Autenticaciones")
    @GetMapping
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Autenticaciones Encontradas"),
        @ApiResponse(responseCode = "404", description = "No se encontraron Autenticaciones"),
        @ApiResponse(responseCode = "500", description = "Error Interno Del Servidor")
    })
    public ResponseEntity<List<AutenticacionResponseDTO>> listar(){
        List<AutenticacionResponseDTO> lista = servi.findAll();
        if(lista.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    @Operation(summary = "Guarda Autenticacion", description = "Guarda una nueva Autenticacion Si Datos Son Validos")
    public ResponseEntity<AutenticacionDTO> save(@Valid @RequestBody AutenticacionDTO dto){
        try {
            AutenticacionDTO si = servi.sabe(dto);
            return ResponseEntity.ok(si);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{username}")
    @Operation(summary = "Busca Autenticacion por Usuario", description = "Retorna Autenticaciones por Usuario si Usuario es valido")
    public ResponseEntity<List<AutenticacionResponseDTO>> buscarxUser(@PathVariable String username){
        try {
            List<AutenticacionResponseDTO> si = servi.findByUser(username);
            return ResponseEntity.ok(si);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
