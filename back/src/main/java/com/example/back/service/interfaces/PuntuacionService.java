package com.example.back.service.interfaces;

import com.example.back.exception.BadRequestException;
import com.example.back.exception.ResourceNotFoundException;
import com.example.back.model.Cliente;
import com.example.back.model.Producto;
import com.example.back.model.Puntuacion;

import java.util.List;
import java.util.Optional;

public interface PuntuacionService {
    Puntuacion guardarPuntuacion(Puntuacion puntuacion) throws BadRequestException;
    Optional<Puntuacion> buscarPuntuacion(Long id) throws ResourceNotFoundException;
    List<Puntuacion> buscarPuntuacionesPorProducto(Producto producto);
    List<Puntuacion> buscarPuntuacionesPorUsuario(Cliente usuario);
    List<Puntuacion> buscarTodasLasPuntuaciones();
    void actualizarPuntuacion(Puntuacion puntuacion) throws ResourceNotFoundException;
    void eliminarPuntuacion(Long id) throws ResourceNotFoundException;
}
