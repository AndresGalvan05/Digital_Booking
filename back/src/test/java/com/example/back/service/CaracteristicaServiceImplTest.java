package com.example.back.service;

import com.example.back.exception.ResourceNotFoundException;
import com.example.back.model.Caracteristica;
import com.example.back.service.interfaces.CaracteristicaService;
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
public class CaracteristicaServiceImplTest {
    @Autowired
    CaracteristicaService caracteristicaService;

    private static Long idGuardado;

    @Test @Order(1)
    void guardarCaracteristica() {
        Caracteristica caracteristicaToSave = new Caracteristica();
        caracteristicaToSave.setNombre("Cocina");
        caracteristicaToSave.setIcono("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRma0hcq_a67lcEWjqmXfvlWIZn3ouGZ2yRMA&usqp=CAU");

        Caracteristica caracteristicaSaved = caracteristicaService.guardarCaracteristica(caracteristicaToSave);

        assertNotNull(caracteristicaSaved);
        assertNotNull(caracteristicaSaved.getId());
        assertEquals("Cocina", caracteristicaSaved.getNombre());
        assertEquals("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRma0hcq_a67lcEWjqmXfvlWIZn3ouGZ2yRMA&usqp=CAU", caracteristicaSaved.getIcono());

        idGuardado = caracteristicaSaved.getId();
    }

    @Test @Order(2)
    void buscarCaracteristica() throws ResourceNotFoundException {
        Optional<Caracteristica> caracteristicaSearched = caracteristicaService.buscarCaracteristica(idGuardado);

        assertNotNull(caracteristicaSearched);
    }

    @Test @Order(3)
    void buscarTodasLasCaracteristicas() {
        List<Caracteristica> caracteristicaList = caracteristicaService.buscarTodasLasCaracteristicas();

        assertTrue(caracteristicaList.size() >= 1);
    }

    @Test @Order(4)
    void actualizarCaracteristica() throws ResourceNotFoundException {
        Caracteristica caracteristicaToUpdate = new Caracteristica();
        caracteristicaToUpdate.setId(idGuardado);
        caracteristicaToUpdate.setNombre("Televisor");
        caracteristicaToUpdate.setIcono("https://cdn-icons-png.flaticon.com/512/49/49672.png");

        caracteristicaService.guardarCaracteristica(caracteristicaToUpdate);
        caracteristicaService.actualizarCaracteristica(caracteristicaToUpdate);
        Optional<Caracteristica> caracteristicaUpdated = caracteristicaService.buscarCaracteristica(caracteristicaToUpdate.getId());

        assertEquals("Televisor", caracteristicaUpdated.get().getNombre());
        assertEquals("https://cdn-icons-png.flaticon.com/512/49/49672.png", caracteristicaUpdated.get().getIcono());
    }

    @Test @Order(5)
    void eliminarCaracteristica() throws ResourceNotFoundException {
        caracteristicaService.eliminarCaracteristica(idGuardado);

        ResourceNotFoundException thrown = assertThrows(
                ResourceNotFoundException.class,
                () -> {caracteristicaService.buscarCaracteristica(idGuardado);}
        );

        assertEquals(("No se encontró ninguna característica con id="+ idGuardado), thrown.getMessage());
    }
}