package com.consultoria.clientes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    private Long id;
    @NotBlank(message="El caracter es obligatorio")
    private String nombreEmpresa;
    @NotNull(message="El caracter es obligatorio")
    private Long nit;
    @NotBlank
    private String sector;
}
