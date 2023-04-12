package com.example.back.model;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "favoritos")
public class Favorito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "producto_id", nullable = false)
    @JsonIncludeProperties("id")
    private Producto producto;

    @ManyToOne @JoinColumn(name = "usuario_id", nullable = false)
    @JsonIncludeProperties("id")
    private Cliente cliente;

    @Override
    public String toString() {
        return "Favorito{" +
                "id=" + id +
                ", producto=" + producto +
                ", cliente=" + cliente +
                '}';
    }
}