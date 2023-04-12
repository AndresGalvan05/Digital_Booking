package com.example.back.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "caracteristicas")
public class Caracteristica {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre, icono;

    @ManyToMany(mappedBy = "caracteristicas")
    @JsonIgnore
    private Set<Producto> productos = new HashSet<>();
}