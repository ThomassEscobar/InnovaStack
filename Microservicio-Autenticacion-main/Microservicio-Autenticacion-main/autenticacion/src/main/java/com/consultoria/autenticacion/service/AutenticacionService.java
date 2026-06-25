package com.consultoria.autenticacion.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consultoria.autenticacion.DTOs.AutenticacionDTO;
import com.consultoria.autenticacion.DTOs.AutenticacionResponseDTO;
import com.consultoria.autenticacion.model.Autenticacion;
import com.consultoria.autenticacion.repository.AutenticacionRepository;
@Service
public class AutenticacionService {

    @Autowired
    private AutenticacionRepository repo;

    public List<AutenticacionResponseDTO> findAll(){
        return repo.findAll()
            .stream()
            .map(au->{
                return new AutenticacionResponseDTO(
                    au.getUsername(),
                    au.getRol(),
                    au.getArea()
                );
            }).collect(Collectors.toList());
    }
    public AutenticacionDTO sabe(AutenticacionDTO dto){
    Autenticacion au = new Autenticacion();
    au.setId(dto.getId());
    au.setArea(dto.getArea());
    au.setEmail(dto.getEmail());
    au.setUsername(dto.getUsername());
    au.setRol(dto.getRol());
    au.setRut(dto.getRut());

    Autenticacion guardar = repo.save(au);

    return new AutenticacionDTO(
        guardar.getId(),
        guardar.getUsername(),
        guardar.getEmail(),
        guardar.getRut(),
        guardar.getRol(),
        guardar.getArea()
    );
}
    public List<AutenticacionResponseDTO> findByUser(String username){
    List<Autenticacion> buscar = repo.findByUsername(username);

    return buscar.stream().map(au ->
        new AutenticacionResponseDTO(
            au.getUsername(),
            au.getRol(),
            au.getArea()
        )
    ).toList();
}

}
