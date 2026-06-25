package com.consultoria.reuniones.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReunionesResponseDTO {

    private Long id;

    private Long proyectoId;

    private Long consultorId;

    private Date fechaHora;

    private String linkZoom;

    private ProyectoDTO proyecto;

}
