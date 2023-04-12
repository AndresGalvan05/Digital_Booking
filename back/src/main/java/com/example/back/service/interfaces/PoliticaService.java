package com.example.back.service.interfaces;

import com.example.back.exception.ResourceNotFoundException;
import com.example.back.model.Politica;
import com.example.back.model.Producto;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PoliticaService {
    Politica guardarPolitica(Politica politica);
    Optional<Politica> buscarPolitica(Long id) throws ResourceNotFoundException;
    List<Politica> buscarTodasLasPoliticas();
    void actualizarPolitica(Politica politica) throws ResourceNotFoundException;
    void eliminarPolitica(Long id) throws ResourceNotFoundException;
    void guardarPoliticas(Set<Politica> politicas, Producto producto);
}
