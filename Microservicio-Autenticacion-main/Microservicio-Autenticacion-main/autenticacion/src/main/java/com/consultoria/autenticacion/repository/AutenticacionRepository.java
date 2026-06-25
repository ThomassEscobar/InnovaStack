package com.consultoria.autenticacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.consultoria.autenticacion.model.Autenticacion;

public interface AutenticacionRepository extends JpaRepository<Autenticacion, Long>{

    List<Autenticacion> findByUsername(String username);
}
