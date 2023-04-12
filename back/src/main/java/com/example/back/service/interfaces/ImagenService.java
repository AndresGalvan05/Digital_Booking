package com.example.back.service.interfaces;

import com.example.back.exception.ResourceNotFoundException;
import com.example.back.model.Imagen;
import com.example.back.model.Producto;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ImagenService {
    Imagen guardarImagen(Imagen imagen);
    Optional<Imagen> buscarImagen(Long id) throws ResourceNotFoundException;
    List<Imagen> buscarTodasLasImagenes();
    void actualizarImagen(Imagen imagen) throws ResourceNotFoundException;
    void eliminarImagen(Long id) throws ResourceNotFoundException;
    void guardarImagenes(Set<Imagen> imagenes, Producto producto);
}
