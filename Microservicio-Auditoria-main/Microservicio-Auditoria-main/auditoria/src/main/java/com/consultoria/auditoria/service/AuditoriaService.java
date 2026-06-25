package com.consultoria.auditoria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consultoria.auditoria.model.Auditoria;
import com.consultoria.auditoria.repository.AuditoriaRepository;

@Service
public class AuditoriaService {

    @Autowired
    private AuditoriaRepository repository;

    public List<Auditoria> listar() {
        return repository.findAll();
    }

    public Auditoria guardar(Auditoria auditoria) {
        return repository.save(auditoria);
    }

    public Auditoria buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}