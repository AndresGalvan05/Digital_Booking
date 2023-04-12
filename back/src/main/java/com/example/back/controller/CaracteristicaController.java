package com.example.back.controller;

import com.example.back.exception.ResourceNotFoundException;
import com.example.back.model.Caracteristica;
import com.example.back.service.implementations.CaracteristicaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @CrossOrigin
@RequestMapping("/caracteristicas")
public class CaracteristicaController {
    private final CaracteristicaServiceImpl caracteristicaServiceImpl;

    @Autowired
    public CaracteristicaController(CaracteristicaServiceImpl caracteristicaServiceImpl) {
        this.caracteristicaServiceImpl = caracteristicaServiceImpl;
    }

    @PostMapping
    public ResponseEntity<Caracteristica> registrarCaracteristica(@RequestBody Caracteristica caracteristica){
        return new ResponseEntity<>(caracteristicaServiceImpl.guardarCaracteristica(caracteristica), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Caracteristica>> searchAll(){
        return ResponseEntity.ok(caracteristicaServiceImpl.buscarTodasLasCaracteristicas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Caracteristica> buscarCaracteristica(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(caracteristicaServiceImpl.buscarCaracteristica(id).get(), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity("Ocurrió un error inesperado: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<String> actualizarCaracteristica(@RequestBody Caracteristica caracteristica) {
        try {
            caracteristicaServiceImpl.actualizarCaracteristica(caracteristica);
            return new ResponseEntity<>("Se actualizó la característica con id=" + caracteristica.getId(), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity("Ocurrió un error inesperado: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCaracteristica(@PathVariable Long id) {
        try {
            caracteristicaServiceImpl.eliminarCaracteristica(id);
            return new ResponseEntity<>("La característica se eliminó correctamente" + id, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity("Ocurrió un error inesperado: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
