package com.example.back.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "productos")
public class Producto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String titulo, direccion;

    @Column(columnDefinition = "mediumtext")
    private String descripcion;

    @Column
    private Float latitud, longitud;

    /** ONE TO MANY **/

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = {"producto"}, allowSetters = true)
    private Set<Politica> politicas = new HashSet<>();

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = {"producto"}, allowSetters = true)
    private Set<Imagen> imagenes = new HashSet<>();

    @OneToMany(mappedBy = "producto", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = {"producto"}, allowSetters = true)
    private Set<Puntuacion> puntuaciones = new HashSet<>();

    @OneToMany(mappedBy = "producto", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = {"producto"}, allowSetters = true)
    private Set<Favorito> favoritos = new HashSet<>();

    @OneToMany(mappedBy = "producto", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("producto")
    private Set<Reserva> reservas = new HashSet<>();

    /** MANY TO ONE **/

    @ManyToOne @JoinColumn(name = "categoria_id", nullable = false)
    @JsonIgnoreProperties("productos")
    private Categoria categoria;

    @ManyToOne @JoinColumn(name = "ciudad_id", nullable = false)
    @JsonIgnoreProperties({"productos", "usuarios"})
    private Ciudad ciudad ;

    /** MANY TO MANY **/

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "producto_caracteristica",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "caracteristica_id")
    )
    @JsonIgnoreProperties("productos")
    private Set<Caracteristica> caracteristicas = new HashSet<>();

    @Transient
    private Integer promedio = calcularPromedio();

    public Integer calcularPromedio(){
        Integer promedio = 0;
        if (getPuntuaciones().size() == 0){
            return 1;
        }
        for (Puntuacion puntuacion: getPuntuaciones()) {
            promedio += puntuacion.getPuntuacion();
        }
        return (promedio/getPuntuaciones().size());
    }

    public void asignarProductoAEntidades(){
        if (imagenes == null || politicas == null) {
            return;
        }
        for (Imagen i : imagenes) {
            i.setProducto(this);
        }
        for (Politica p : politicas) {
            p.setProducto(this);
        }
    }
}
