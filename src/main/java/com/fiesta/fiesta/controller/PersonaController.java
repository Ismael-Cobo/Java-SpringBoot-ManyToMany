package com.fiesta.fiesta.controller;

import com.fiesta.fiesta.entity.Fiesta;
import com.fiesta.fiesta.entity.Persona;
import com.fiesta.fiesta.repository.PersonaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {
    
    private static Logger logger = LoggerFactory.getLogger(PersonaController.class);
    @Autowired
    private PersonaRepository personaRepository;
    
    @GetMapping
    public ResponseEntity<Collection<Persona>> getAll() {
        return ResponseEntity.ok(personaRepository.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Persona> getOneById(@PathVariable("id") Long id) {
    
        Optional<Persona> optionalPersona = personaRepository.findById(id);
        
        if(optionalPersona.isEmpty()) return ResponseEntity.notFound().build();
    
        return ResponseEntity.ok(optionalPersona.get());
    }
    
    @PostMapping
    public ResponseEntity<?> saveOne(@RequestBody Persona persona) {
        return new ResponseEntity<>(personaRepository.save(persona), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Persona> updateOneById(@PathVariable("id") Long id, @RequestBody Persona persona) {
        
        Optional<Persona> optionalPersona = personaRepository.findById(id);
        
        if(optionalPersona.isEmpty()) return ResponseEntity.notFound().build();
        
        persona.setId(optionalPersona.get().getId());
        personaRepository.save(persona);
        return ResponseEntity.ok(persona);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOneById(@PathVariable("id") Long id) {
        personaRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/{id}/fiestas")
    public ResponseEntity<Collection<Fiesta>> getAllFiestasByPersonaId(@PathVariable("id") Long id){
        
        Optional<Persona> optionalPersona = personaRepository.findById(id);

        if(optionalPersona.isEmpty()) return ResponseEntity.notFound().build();
    
        return ResponseEntity.ok(optionalPersona.get().getFiestas());
        
        
    }
}
