package com.example.back.service.interfaces;

import com.example.back.exception.ResourceNotFoundException;
import com.example.back.model.Caracteristica;

import java.util.List;
import java.util.Optional;

public interface CaracteristicaService {
    Caracteristica guardarCaracteristica(Caracteristica caracteristica);
    Optional<Caracteristica> buscarCaracteristica(Long id) throws ResourceNotFoundException;
    List<Caracteristica> buscarTodasLasCaracteristicas();
    void actualizarCaracteristica(Caracteristica Caracteristica) throws ResourceNotFoundException;
    void eliminarCaracteristica(Long id) throws ResourceNotFoundException;
}
