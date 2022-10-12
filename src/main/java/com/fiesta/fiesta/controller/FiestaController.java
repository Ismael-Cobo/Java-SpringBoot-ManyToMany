package com.fiesta.fiesta.controller;

import com.fiesta.fiesta.entity.Fiesta;
import com.fiesta.fiesta.entity.Persona;
import com.fiesta.fiesta.repository.FiestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/fiestas")
public class FiestaController {
    
    @Autowired
    private FiestaRepository fiestaRepository;
    
    @GetMapping
    public ResponseEntity<Collection<Fiesta>> getAll() {
        return ResponseEntity.ok(fiestaRepository.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Fiesta> getOneById(@PathVariable("id") Long id) {
        
        Optional<Fiesta> optionalFiesta = fiestaRepository.findById(id);
        
        if(optionalFiesta.isEmpty()) return ResponseEntity.notFound().build();
        
        return ResponseEntity.ok(optionalFiesta.get());
    }
    
    @PostMapping
    public ResponseEntity<?> saveOne(@RequestBody Fiesta fiesta) {
        return new ResponseEntity<>(fiestaRepository.save(fiesta), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Fiesta> updateOneById(@PathVariable("id") Long id, @RequestBody Fiesta fiesta) {
        
        Optional<Fiesta> optionalFiesta = fiestaRepository.findById(id);
        
        if(optionalFiesta.isEmpty()) return ResponseEntity.notFound().build();
        
        fiesta.setId(optionalFiesta.get().getId());
        fiestaRepository.save(fiesta);
        return ResponseEntity.ok(fiesta);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOneById(@PathVariable("id") Long id) {
        fiestaRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/{id}/personas")
    public ResponseEntity<Collection<Persona>> getAllPersonasByFiestaId(@PathVariable("id") Long id){
        
        Optional<Fiesta> optionalFiesta = fiestaRepository.findById(id);
        
        if(optionalFiesta.isEmpty()) return ResponseEntity.notFound().build();
        
        return ResponseEntity.ok(optionalFiesta.get().getPersonas());


    }
}
