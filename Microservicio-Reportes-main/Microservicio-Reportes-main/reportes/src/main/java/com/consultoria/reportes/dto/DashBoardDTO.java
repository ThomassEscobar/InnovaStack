package com.consultoria.reportes.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashBoardDTO {
    @NotNull
    private Integer totalProyectosActivos;
    @NotNull
    private Double ingresosMes;
    @NotNull
    private Integer ticketsPendientes;

}