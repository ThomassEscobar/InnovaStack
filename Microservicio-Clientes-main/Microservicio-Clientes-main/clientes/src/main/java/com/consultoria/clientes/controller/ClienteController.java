package com.consultoria.clientes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consultoria.clientes.dto.ClienteDTO;
import com.consultoria.clientes.dto.ClienteResponseDTO;
import com.consultoria.clientes.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Clientes", description = "Operaciones sobre Clientes")
public class ClienteController {

    @Autowired
    private ClienteService servi;
    @Operation(summary = "Busca todos los clientes", description = "Retorna todos los clientes")
    @GetMapping
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Clientes encontrados"),
        @ApiResponse(responseCode = "404", description = "Clientes no encontrados"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<ClienteResponseDTO>> listar(){
        List<ClienteResponseDTO> listaa = servi.findTodos();
        if(listaa.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaa);
    }

    @GetMapping("/{id}/full")
    @Operation(summary = "Otorgar datos completos", description = "Retorna los datos completos de un cliente por su id")
    public ResponseEntity<ClienteDTO> obtenerClienteCompleto(@PathVariable Long id) {
        // IMPORTANTE: Aquí usamos ClienteDTO porque es el que tiene 
        // id, nombre, nit, sector, direccion Y emailEmpresa
        ClienteDTO cliente = servi.buscarxIdCompleto(id); 
        return ResponseEntity.ok(cliente);
    }
    @Operation(summary = "Guardar cliente", description = "Guarda los clientes si los datos ingresados son correctos")
    @PostMapping
    public ResponseEntity<ClienteDTO> guardar(@Valid @RequestBody ClienteDTO cliente){
        try {
            ClienteDTO si = servi.guardar(cliente);
            return ResponseEntity.ok(si);
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }
    @Operation(summary = "Otorga datos de cliente por id", description = "Busca y retorna los datos de un cliente segun su id")
    @GetMapping("/{id}")
    @ApiResponses(value ={
        @ApiResponse(responseCode= "200", description = "Cliente encontrado"),
        @ApiResponse(responseCode= "404", description = "Cliente no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })

    
    public ResponseEntity<ClienteResponseDTO> buscar(
        @PathVariable 
        @Parameter(description = "ID Cliente", required = true, examples = @ExampleObject(value = "1")) Long id){
        try {
            ClienteResponseDTO si = servi.buscarxId(id);
            return ResponseEntity.ok(si);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
