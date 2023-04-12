package com.example.back.model;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "politicas")
public class Politica {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String titulo, descripcion;

    @ManyToOne @JoinColumn(name = "producto_id", nullable = false)
    @JsonIncludeProperties("id")
    private Producto producto;

    public void removerPoliticaDeProducto(){
        producto.getPoliticas().remove(this);
        setProducto(null);
    }
}