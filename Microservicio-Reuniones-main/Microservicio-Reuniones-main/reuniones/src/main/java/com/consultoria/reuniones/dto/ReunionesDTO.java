package com.consultoria.reuniones.dto;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReunionesDTO {

    private Long id;

    private Long proyectoId;
    
    private Long consultorId;
    @NotNull
    private Date fechaHora;
    @NotBlank
    private String linkZoom;


}
