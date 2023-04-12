package com.example.back.model;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "puntuaciones")
public class Puntuacion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer puntuacion;

    @Column(columnDefinition = "mediumtext")
    private String comentario;

    @ManyToOne @JoinColumn(name = "producto_id", nullable = false)
    @JsonIncludeProperties("id")
    private Producto producto;

    @ManyToOne @JoinColumn(name = "usuario_id", nullable = false)
    @JsonIncludeProperties("id")
    private Cliente cliente;
}