package com.example.back.service;

import com.example.back.exception.ResourceNotFoundException;
import com.example.back.model.Categoria;
import com.example.back.service.interfaces.CategoriaService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class CategoriaServiceImplTest {
    @Autowired
    CategoriaService categoriaService;

    private static Long idGuardado;

    @Test @Order(1)
    void guardarCategoria() {
        Categoria categoriaToSave = new Categoria();
        categoriaToSave.setTitulo("Hermitage Hotel");
        categoriaToSave.setDescripcion("hotel");
        categoriaToSave.setUrlImagen("https://unsplash.com/photos/oxeCZrodz78");

        Categoria categoriaSaved = categoriaService.guardarCategoria(categoriaToSave);

        assertNotNull(categoriaSaved);
        assertNotNull(categoriaSaved.getId());
        assertEquals("Hermitage Hotel", categoriaSaved.getTitulo());
        assertEquals("hotel", categoriaSaved.getDescripcion());
        assertEquals("https://unsplash.com/photos/oxeCZrodz78", categoriaSaved.getUrlImagen());

        idGuardado = categoriaSaved.getId();
    }

    @Test @Order(2)
    void buscarCategoria() throws ResourceNotFoundException {
        Optional<Categoria> categoriaSearched = categoriaService.buscarCategoria(idGuardado);

        assertNotNull(categoriaSearched);
    }

    @Test @Order(3)
    void buscarTodasLasCategorias() {
        List<Categoria> categoriaList = categoriaService.buscarTodasLasCategorias();

        assertTrue(categoriaList.size() >= 1);
    }

    @Test @Order(4)
    void actualizarCategoria() throws ResourceNotFoundException {
        Categoria categoriaToUpdate = new Categoria();
        categoriaToUpdate.setId(idGuardado);
        categoriaToUpdate.setTitulo("Breakfast Quien Pudiera");
        categoriaToUpdate.setDescripcion("bed and breakfast");
        categoriaToUpdate.setUrlImagen("https://unsplash.com/photos/AH8zKXqFITA");

        categoriaService.guardarCategoria(categoriaToUpdate);
        categoriaService.actualizarCategoria(categoriaToUpdate);
        Optional<Categoria> categoriaUpdated = categoriaService.buscarCategoria(categoriaToUpdate.getId());

        assertEquals("Breakfast Quien Pudiera", categoriaUpdated.get().getTitulo());
        assertEquals("bed and breakfast", categoriaUpdated.get().getDescripcion());
        assertEquals("https://unsplash.com/photos/AH8zKXqFITA", categoriaUpdated.get().getUrlImagen());
    }

    @Test @Order(5)
    void eliminarCategoria() throws ResourceNotFoundException {
        categoriaService.eliminarCategoria(idGuardado);

        ResourceNotFoundException thrown = assertThrows(
                ResourceNotFoundException.class,
                () -> {categoriaService.buscarCategoria(idGuardado);}
        );

        assertEquals(("No se encontró ninguna categoría con id="+ idGuardado), thrown.getMessage());
    }
}