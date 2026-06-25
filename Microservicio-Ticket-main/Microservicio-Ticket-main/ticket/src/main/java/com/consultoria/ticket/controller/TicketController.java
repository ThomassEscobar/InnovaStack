package com.consultoria.ticket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consultoria.ticket.dto.TicketDTO;
import com.consultoria.ticket.dto.TicketResponseDTO;
import com.consultoria.ticket.service.TicketService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tickets")
@Tag(name = "Tickets", description = "Operaciones sobre tickets")
public class TicketController {

    @Autowired
    private TicketService servi;
    @Operation(summary = "Busca todos los tickets", description = "Retorna todos los tickets registrados")
    @GetMapping
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tickets encontrados"),
        @ApiResponse(responseCode = "404", description = "Tickets No Encontrados"),
        @ApiResponse(responseCode = "500", description = "Error Interno Del Servidor")
    
    })
    public ResponseEntity<List<TicketResponseDTO>> listar(){
        List<TicketResponseDTO> lista = servi.findTodos();
        if(lista.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }
    @Operation(summary = "Guardar Ticket", description = "Guardar los Tickets si los datos son correctos")
    @PostMapping
    public ResponseEntity<TicketDTO> guardar(@Valid @RequestBody TicketDTO dto){
        try {
            TicketDTO si = servi.guardar(dto);
            return ResponseEntity.ok(si);
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }
    @Operation(summary = "Otorga datos de Ticket por ID",description = "Busca y retorna los datos de un ticket por su ID")
    @GetMapping("/{clienteId}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tickets encontrados"),
        @ApiResponse(responseCode = "404", description = "Tickets No encontrados"),
        @ApiResponse(responseCode = "500", description = "Error Interno del servidor")
    })
    public ResponseEntity<List<TicketResponseDTO>> buscar(@PathVariable Long clienteId){
        try {
            List<TicketResponseDTO> si = servi.buscarxId(clienteId);
            return ResponseEntity.ok(si);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{id}/full")
    public ResponseEntity<TicketResponseDTO> obtenerTicketFull(@PathVariable Long id) {
        try {
            TicketResponseDTO ticket = servi.obtenerDetalleCompleto(id);
            return ResponseEntity.ok(ticket);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
