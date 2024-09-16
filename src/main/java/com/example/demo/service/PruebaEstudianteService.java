package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.PruebaEstudiante;
import com.example.demo.repository.PruebaEstudianteRepository;

@Service
public class PruebaEstudianteService {

    @Autowired
    private PruebaEstudianteRepository repository;

    public List<PruebaEstudiante> obtenerTodos() {
        return repository.findAll();
    }

    public PruebaEstudiante guardarEstudiante(PruebaEstudiante estudiante) {
        return repository.save(estudiante);
    }

    public Optional<PruebaEstudiante> obtenerPorId(Integer id) {
        return repository.findById(id);
    }

    public void eliminarPorId(Integer id) {
        repository.deleteById(id);
    }
}
