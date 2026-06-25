package com.consultoria.ticket.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponseDTO {

    private Long clienteId;

    private String asunto;

    private String descripcion;

    private String prioridad;

    private Date fechaCreacion;

    private ClienteDTO cliente;

}
