package com.example.back.dto;

import com.example.back.model.Ciudad;
import com.example.back.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioDTO {
    private Long id;
    private String nombre, apellido, email, password;
    private Role role;
    private Ciudad ciudad;

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", rol=" + role +
                ", ciudad=" + ciudad +
                '}';
    }
}
