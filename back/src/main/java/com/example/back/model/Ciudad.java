package com.example.back.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "ciudades")
public class Ciudad {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre, pais;

    @OneToMany(mappedBy = "ciudad", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<Producto> productos;

    @OneToMany(mappedBy = "ciudad", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<Usuario> usuarios;

    public Ciudad(String nombre, String pais) {
        this.nombre = nombre;
        this.pais = pais;
    }
}
