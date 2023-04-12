package com.example.back.service;

import com.example.back.exception.ResourceNotFoundException;
import com.example.back.model.Ciudad;
import com.example.back.service.interfaces.CiudadService;
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
public class CiudadServiceImplTest {
    @Autowired
    CiudadService ciudadService;

    private static Long idGuardado;
    private static String ciudadStatic;

    @Test @Order(1)
    void guardarCiudad() {
        Ciudad ciudadToSave = new Ciudad();
        ciudadToSave.setNombre("Tokyo");
        ciudadToSave.setPais("Japón");

        Ciudad ciudadSaved = ciudadService.guardarCiudad(ciudadToSave);

        assertNotNull(ciudadSaved);
        assertNotNull(ciudadSaved.getId());
        assertEquals("Tokyo", ciudadSaved.getNombre());
        assertEquals("Japón", ciudadSaved.getPais());

        idGuardado = ciudadSaved.getId();
        ciudadStatic = ciudadSaved.getNombre();
    }

    @Test @Order(2)
    void buscarCiudad() throws ResourceNotFoundException {
        Optional<Ciudad> ciudadSearched = ciudadService.buscarCiudad(idGuardado);

        assertNotNull(ciudadSearched);
    }

    @Test @Order(3)
    void buscarCiudadPorNombre() throws ResourceNotFoundException {
        Optional<Ciudad> ciudadSearched = ciudadService.buscarCiudadPorNombre(ciudadStatic);

        assertNotNull(ciudadSearched);
    }

    @Test @Order(4)
    void buscarTodasLasCiudads() {
        List<Ciudad> ciudadList = ciudadService.buscarTodasLasCiudades();

        assertTrue(ciudadList.size() >= 1);
    }

    @Test @Order(5)
    void actualizarCiudad() throws ResourceNotFoundException {
        Ciudad ciudadToUpdate = new Ciudad();
        ciudadToUpdate.setId(idGuardado);
        ciudadToUpdate.setNombre("Montevideo");
        ciudadToUpdate.setPais("Uruguay");

        ciudadService.guardarCiudad(ciudadToUpdate);
        ciudadService.actualizarCiudad(ciudadToUpdate);
        Optional<Ciudad> ciudadUpdated = ciudadService.buscarCiudad(ciudadToUpdate.getId());

        assertEquals("Montevideo", ciudadUpdated.get().getNombre());
        assertEquals("Uruguay", ciudadUpdated.get().getPais());
    }

    @Test @Order(6)
    void eliminarCiudad() throws ResourceNotFoundException {
        ciudadService.eliminarCiudad(idGuardado);

        ResourceNotFoundException thrown = assertThrows(
                ResourceNotFoundException.class,
                () -> {ciudadService.buscarCiudad(idGuardado);}
        );

        assertEquals(("No se encontró ninguna ciudad con id="+ idGuardado), thrown.getMessage());
    }
}