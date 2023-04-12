package com.example.back.service.interfaces;

import com.example.back.exception.ResourceNotFoundException;
import com.example.back.model.Ciudad;

import java.util.List;
import java.util.Optional;

public interface CiudadService {
    Ciudad guardarCiudad(Ciudad ciudad);
    Optional<Ciudad> buscarCiudad(Long id) throws ResourceNotFoundException;
    Optional<Ciudad> buscarCiudadPorNombre(String nombre) throws ResourceNotFoundException;
    List<Ciudad> buscarTodasLasCiudades();
    void actualizarCiudad(Ciudad ciudad) throws ResourceNotFoundException;
    void eliminarCiudad(Long id) throws ResourceNotFoundException;
}