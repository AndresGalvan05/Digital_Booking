package com.example.back.model;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "reservas")
public class Reserva {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hora_reserva", nullable = false)
    private Time horaReserva;

    @Column(name = "fecha_inicial", nullable = false)
    private LocalDate fechaInicial;

    @Column(name = "fecha_final", nullable = false)
    private LocalDate fechaFinal;

    @ManyToOne @JoinColumn(name = "producto_id", nullable = false)
    @JsonIncludeProperties("id")
    private Producto producto;

    @ManyToOne @JoinColumn(name = "usuario_id", nullable = false)
    @JsonIncludeProperties("id")
    private Cliente cliente;
}
