package com.example.back.repository;

import com.example.back.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PuntuacionRepository extends JpaRepository<Puntuacion, Long> {
    List<Puntuacion> findByProducto(Producto id);
    List<Puntuacion> findByCliente(Cliente id);
}
