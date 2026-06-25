package com.consultoria.autenticacion.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="autenticaion")
public class Autenticacion {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @NotBlank
    private Long id;
    @NotBlank
    private String username;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String rut;
    @NotBlank
    private String rol;
    @NotBlank
    private String area;


}
