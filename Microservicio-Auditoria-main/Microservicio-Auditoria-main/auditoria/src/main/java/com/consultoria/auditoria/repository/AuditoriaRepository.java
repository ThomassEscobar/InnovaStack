package com.consultoria.auditoria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.consultoria.auditoria.model.Auditoria;

@Repository
public interface AuditoriaRepository extends JpaRepository<Auditoria, Object> {

}
