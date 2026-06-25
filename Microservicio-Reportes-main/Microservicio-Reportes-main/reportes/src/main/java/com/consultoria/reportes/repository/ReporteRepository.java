package com.consultoria.reportes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.consultoria.reportes.model.Reportes;


@Repository
public interface ReporteRepository extends JpaRepository<Reportes, Long> {

}