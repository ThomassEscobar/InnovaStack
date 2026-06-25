package com.consultoria.reuniones.model;
//Model (Reserva): id, proyectoId, fechaHora, linkZoom, consultorId.

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="reuniones")
public class Reuniones {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long proyectoId;

    @NotNull
    private Long consultorId;
    @NotNull
    private Date fechaHora;
    @NotBlank
    private String linkZoom;


}
