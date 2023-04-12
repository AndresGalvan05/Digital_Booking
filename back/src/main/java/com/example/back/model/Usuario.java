package com.example.back.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="user_type",
        discriminatorType = DiscriminatorType.STRING)
public abstract class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre, apellido, email, password;

    @Column
    private boolean estaHabilitado;

    /** MANY TO ONE **/

    @ManyToOne @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne @JoinColumn(name = "ciudad_id")
    @JsonIgnoreProperties({"usuarios","productos"})
    private Ciudad ciudad;

    public Usuario(String nombre, String apellido, String email, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.estaHabilitado = false;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}