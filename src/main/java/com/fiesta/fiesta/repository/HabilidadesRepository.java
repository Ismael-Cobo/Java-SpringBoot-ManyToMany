package com.fiesta.fiesta.repository;

import com.fiesta.fiesta.entity.Habilidades;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface HabilidadesRepository extends CrudRepository<Habilidades, Long> {

    Collection<Habilidades> findAll();

}
