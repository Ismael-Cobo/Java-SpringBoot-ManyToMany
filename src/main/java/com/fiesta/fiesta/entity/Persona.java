package com.fiesta.fiesta.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "personas")
public class Persona {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "persona_id")
    private Long Id;
    
    private Integer edad;
    private String nombre;
    
    @OneToMany(mappedBy = "persona")
    @JsonManagedReference
    private Set<Habilidades> habilidades = new HashSet<>();
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "persona_fiestas",
            joinColumns = @JoinColumn(name = "persona_id", referencedColumnName = "persona_id"),
            inverseJoinColumns = @JoinColumn(name = "fiesta_id", referencedColumnName = "fiesta_id")
    )
    @JsonManagedReference
    private Set<Fiesta> fiestas = new HashSet<>();
    
    public Persona() {
    }
    
    public Long getId() {
        return Id;
    }
    
    public void setId(Long id) {
        Id = id;
    }
    
    public Integer getEdad() {
        return edad;
    }
    
    public void setEdad(Integer edad) {
        this.edad = edad;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public Set<Habilidades> getHabilidades() {
        return habilidades;
    }
    
    public void setHabilidades(Set<Habilidades> habilidades) {
        this.habilidades = habilidades;
    }
    
    public Set<Fiesta> getFiestas() {
        return fiestas;
    }
    
    public void setFiestas(Set<Fiesta> fiestas) {
        this.fiestas = fiestas;
    }
}
