package com.fiesta.fiesta.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Fiesta")
public class Fiesta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fiesta_id")
    private Long id;
    
    @Column(name = "fiesta_fecha")
    @JsonFormat(pattern = "YYYY-MM-DD")
    private Date fecha;
    
    private String ubicacion;
    
    @ManyToMany(mappedBy = "fiestas",fetch = FetchType.LAZY )
    @JsonBackReference
//    @JoinTable(
//            name = "persona_fiestas",
//            joinColumns = @JoinColumn(name = "fiesta_id", referencedColumnName = "fiesta_id"),
//            inverseJoinColumns = @JoinColumn(name = "persona_id", referencedColumnName = "persona_id")
//    )
    private Set<Persona> personas = new HashSet<>();
    
   
    
    public Fiesta() {
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Date getFecha() {
        return fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public String getUbicacion() {
        return ubicacion;
    }
    
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    public Set<Persona> getPersonas() {
        return personas;
    }
    
    public void setPersonas(Set<Persona> personas) {
        this.personas = personas;
    }
}
