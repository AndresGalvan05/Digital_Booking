package com.example.back.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "imagenes")
public class Imagen {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String titulo, url;

    @ManyToOne @JoinColumn(name = "producto_id", nullable = false, referencedColumnName = "id")
    @JsonIncludeProperties("id")
    private Producto producto;

    public void removerImagenDeProducto(){
        producto.getImagenes().remove(this);
        setProducto(null);
    }
}