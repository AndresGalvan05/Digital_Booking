package com.example.back.dto;

import com.example.back.model.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductoDTO {
    private Long id;
    private String titulo, descripcion, direccion;
    private Float latitud, longitud;
    private Categoria categoria;
    private Ciudad ciudad;
    private Set<Imagen> imagenes;
    private Set<Politica> politicas;
    private Set<Caracteristica> caracteristicas;
    private Set<Reserva> reservas;
    private Integer promedio;
}
