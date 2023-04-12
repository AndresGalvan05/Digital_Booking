package com.example.back.service.implementations;

import com.example.back.exception.ResourceNotFoundException;
import com.example.back.model.Categoria;
import com.example.back.repository.CategoriaRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service @Log4j
public class CategoriaServiceImpl implements com.example.back.service.interfaces.CategoriaService {
    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Categoria guardarCategoria(Categoria categoria) {
        log.info("Se guardó una nueva categoría");
        return categoriaRepository.save(categoria);
    }

    @Override
    public Optional<Categoria> buscarCategoria(Long id) throws ResourceNotFoundException {
        Optional<Categoria> categoriaBuscada = categoriaRepository.findById(id);
        if (categoriaBuscada.isPresent()){
            log.info("Se encontró una categoría con id="+id);
            return Optional.of(categoriaBuscada.get());
        } else {
            log.info("No se encontró ninguna categoría con id="+id);
            throw new ResourceNotFoundException(("No se encontró ninguna categoría con id="+id));
        }
    }

    @Override
    public List<Categoria> buscarTodasLasCategorias() {
        log.info("Se realizó una búsqueda de todas las categorías");
        return categoriaRepository.findAll();
    }

    @Override
    public void actualizarCategoria (Categoria categoria) throws ResourceNotFoundException {
        buscarCategoria(categoria.getId());
        categoriaRepository.save(categoria);
        log.info("Se actualizó la categoría con id="+categoria.getId());
    }

    @Override
    public void eliminarCategoria(Long id) throws ResourceNotFoundException {
        buscarCategoria(id);
        categoriaRepository.deleteById(id);
        log.warn("Se eliminó la categoría con id="+id);
    }
}
