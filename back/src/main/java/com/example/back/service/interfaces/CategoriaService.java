package com.example.back.service.interfaces;

import com.example.back.exception.ResourceNotFoundException;
import com.example.back.model.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    Categoria guardarCategoria(Categoria categoria);
    Optional<Categoria> buscarCategoria(Long id) throws ResourceNotFoundException;
    List<Categoria> buscarTodasLasCategorias();
    void actualizarCategoria(Categoria Categoria) throws ResourceNotFoundException;
    void eliminarCategoria(Long id) throws ResourceNotFoundException;
}
