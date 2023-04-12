package com.example.back.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@DiscriminatorValue("Cliente")
public class Cliente extends Usuario {

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("cliente")
    private Set<Reserva> reservas = new HashSet<>();

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("cliente")
    private Set<Puntuacion> puntuaciones = new HashSet<>();

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("cliente")
    private Set<Favorito> favoritos = new HashSet<>();
}