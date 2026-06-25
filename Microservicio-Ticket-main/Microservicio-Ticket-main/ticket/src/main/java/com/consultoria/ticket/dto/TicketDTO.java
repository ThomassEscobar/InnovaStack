package com.consultoria.ticket.dto;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
    
    private Long id;
   
    private Long clienteId;
    @NotBlank
    private String asunto;
    @NotBlank
    private String descripcion;
    @NotBlank
    private String prioridad;
    @NotNull
    private Date fechaCreacion;


}
