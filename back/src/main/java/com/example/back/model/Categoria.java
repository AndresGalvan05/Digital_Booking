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
@Table(name = "categorias")
public class Categoria {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String titulo, descripcion, urlImagen;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<Producto> productos;

    public Categoria(String titulo, String descripcion, String urlImagen) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.urlImagen = urlImagen;
    }
}
