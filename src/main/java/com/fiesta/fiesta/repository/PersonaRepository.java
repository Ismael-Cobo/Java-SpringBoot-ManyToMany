package com.fiesta.fiesta.repository;

import com.fiesta.fiesta.entity.Persona;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PersonaRepository extends CrudRepository<Persona, Long> {

    Collection<Persona> findAll();

}
