package com.fiesta.fiesta.controller;

import com.fiesta.fiesta.entity.Habilidades;
import com.fiesta.fiesta.entity.Persona;
import com.fiesta.fiesta.repository.HabilidadesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/habilidades")
public class HabilidadesController {
    
    @Autowired
    private HabilidadesRepository habilidadesRepository;
    
    @GetMapping
    public ResponseEntity<Collection<Habilidades>> getAll() {
        return ResponseEntity.ok(habilidadesRepository.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Habilidades> getOneById(@PathVariable("id") Long id) {
        
        Optional<Habilidades> optionalHabilidades = habilidadesRepository.findById(id);
        
        if(optionalHabilidades.isEmpty()) return ResponseEntity.notFound().build();
        
        return ResponseEntity.ok(optionalHabilidades.get());
    }
    
    @PostMapping
    public ResponseEntity<?> saveOne(@RequestBody Habilidades habilidades) {
        return new ResponseEntity<>(habilidadesRepository.save(habilidades), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Habilidades> updateOneById(@PathVariable("id") Long id, @RequestBody Habilidades habilidades) {
        
        Optional<Habilidades> optionalHabilidades = habilidadesRepository.findById(id);
        
        if(optionalHabilidades.isEmpty()) return ResponseEntity.notFound().build();
    
        habilidades.setId(optionalHabilidades.get().getId());
        habilidadesRepository.save(habilidades);
        return ResponseEntity.ok(habilidades);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOneById(@PathVariable("id") Long id) {
        habilidadesRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
    
}
