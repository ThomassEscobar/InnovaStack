package com.consultoria.reuniones.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consultoria.reuniones.client.ProyectoClient;
import com.consultoria.reuniones.dto.ProyectoDTO;
import com.consultoria.reuniones.dto.ReunionesDTO;
import com.consultoria.reuniones.dto.ReunionesResponseDTO;
import com.consultoria.reuniones.model.Reuniones;
import com.consultoria.reuniones.repository.ReunionesRepository;
@Service
public class ReunionesService {

    @Autowired
    private ProyectoClient client;

    @Autowired
    private ReunionesRepository repo;

    public List<ReunionesResponseDTO> listar(){
        return repo.findAll()
        .stream()
        .map(pro ->{
            ProyectoDTO proyecto = client.obtenerProyecto(pro.getProyectoId());
            return new ReunionesResponseDTO(
                pro.getId(),
                pro.getProyectoId(),
                pro.getConsultorId(),
                pro.getFechaHora(),
                pro.getLinkZoom(),
                proyecto
            );
        }).collect(Collectors.toList());
    }
    public ReunionesDTO guardar(ReunionesDTO dto){
        Reuniones reu = new Reuniones();
        reu.setId(dto.getId());
        reu.setConsultorId(dto.getConsultorId());
        reu.setProyectoId(dto.getProyectoId());
        reu.setFechaHora(dto.getFechaHora());
        reu.setLinkZoom(dto.getLinkZoom());
        Reuniones guardar = repo.save(reu);
        return new ReunionesDTO(
            guardar.getId(),
            guardar.getConsultorId(),
            guardar.getProyectoId(),
            guardar.getFechaHora(),
            guardar.getLinkZoom()
        );
    }
    public List<ReunionesResponseDTO> buscarxId(Long proyectoId){
        List<Reuniones> listar = repo.findByProyectoId(proyectoId);
        ProyectoDTO proyecto = client.obtenerProyecto(proyectoId);
        return listar.stream().map(pro ->
            new ReunionesResponseDTO(
                pro.getId(),
                pro.getConsultorId(),
                pro.getProyectoId(),
                pro.getFechaHora(),
                pro.getLinkZoom(),
                proyecto
            )
        ).toList();
    }
}

