package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.PruebaEstudiante;
import com.example.demo.service.PruebaEstudianteService;

@RestController
@RequestMapping("/api/estudiantes")
public class PruebaEstudianteController {

    @Autowired
    private PruebaEstudianteService service;

    @GetMapping
    public List<PruebaEstudiante> obtenerTodos() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PruebaEstudiante> obtenerPorId(@PathVariable Integer id) {
        Optional<PruebaEstudiante> estudiante = service.obtenerPorId(id);
        return estudiante.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public PruebaEstudiante guardarEstudiante(@RequestBody PruebaEstudiante estudiante) {
        return service.guardarEstudiante(estudiante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PruebaEstudiante> actualizarEstudiante(@PathVariable Integer id, @RequestBody PruebaEstudiante detallesEstudiante) {
        Optional<PruebaEstudiante> estudianteExistente = service.obtenerPorId(id);
        if (estudianteExistente.isPresent()) {
            PruebaEstudiante estudiante = estudianteExistente.get();
            estudiante.setNombre(detallesEstudiante.getNombre());
            estudiante.setEspecialidad(detallesEstudiante.getEspecialidad());
            estudiante.setGrado(detallesEstudiante.getGrado());
            return new ResponseEntity<>(service.guardarEstudiante(estudiante), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstudiante(@PathVariable Integer id) {
        Optional<PruebaEstudiante> estudiante = service.obtenerPorId(id);
        if (estudiante.isPresent()) {
            service.eliminarPorId(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
