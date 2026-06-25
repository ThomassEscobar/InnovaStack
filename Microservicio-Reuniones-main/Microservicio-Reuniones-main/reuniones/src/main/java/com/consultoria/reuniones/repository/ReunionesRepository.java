package com.consultoria.reuniones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.consultoria.reuniones.model.Reuniones;
@Repository
public interface ReunionesRepository extends JpaRepository<Reuniones, Long>{

    List<Reuniones> findByProyectoId(Long proyctoId);

}
