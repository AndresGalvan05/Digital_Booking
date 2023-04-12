package com.example.back.controller;

import com.example.back.exception.BadRequestException;
import com.example.back.exception.ResourceNotFoundException;
import com.example.back.model.Ciudad;
import com.example.back.service.implementations.CiudadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @CrossOrigin
@RequestMapping("/ciudades")
public class CiudadController {
    private final CiudadServiceImpl ciudadServiceImpl;

    @Autowired
    public CiudadController(CiudadServiceImpl ciudadServiceImpl) {
        this.ciudadServiceImpl = ciudadServiceImpl;
    }

    @PostMapping
    public ResponseEntity<Ciudad> registrarCiudad(@RequestBody Ciudad ciudad){
        return new ResponseEntity<>(ciudadServiceImpl.guardarCiudad(ciudad), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Ciudad>> searchAll(){
        return ResponseEntity.ok(ciudadServiceImpl.buscarTodasLasCiudades());
    }

    @GetMapping("/buscarId/{id}")
    public ResponseEntity<Ciudad> buscarCiudad(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(ciudadServiceImpl.buscarCiudad(id).get());
    }

    @GetMapping("/buscarNombre/{nombre}")
    public ResponseEntity<Ciudad> buscarCiudad(@PathVariable("nombre") String nombre) throws ResourceNotFoundException {
        return ResponseEntity.ok(ciudadServiceImpl.buscarCiudadPorNombre(nombre).get());
    }

    @PutMapping
    public ResponseEntity<String> actualizarCiudad(@RequestBody Ciudad ciudad) throws BadRequestException, ResourceNotFoundException {
        ciudadServiceImpl.actualizarCiudad(ciudad);
        return ResponseEntity.ok("Se actualizó la ciudad con id " + ciudad.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCiudad(@PathVariable Long id) throws ResourceNotFoundException {
        ciudadServiceImpl.eliminarCiudad(id);
        return ResponseEntity.ok().body("La ciudad se eliminó correctamente");
    }
}
