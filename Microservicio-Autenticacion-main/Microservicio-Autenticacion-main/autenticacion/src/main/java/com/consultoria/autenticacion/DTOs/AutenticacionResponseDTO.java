package com.consultoria.autenticacion.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutenticacionResponseDTO {
    
    private String username;

    private String rol;

    private String area;
    

}
