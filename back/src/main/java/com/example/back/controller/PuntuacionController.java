package com.example.back.controller;

import com.example.back.exception.BadRequestException;
import com.example.back.exception.ResourceNotFoundException;
import com.example.back.model.Cliente;
import com.example.back.model.Producto;
import com.example.back.model.Puntuacion;
import com.example.back.service.implementations.PuntuacionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @CrossOrigin
@RequestMapping("/puntuaciones")
public class PuntuacionController {
    private final PuntuacionServiceImpl puntuacionServiceImpl;

    @Autowired
    public PuntuacionController(PuntuacionServiceImpl puntuacionServiceImpl) {
        this.puntuacionServiceImpl = puntuacionServiceImpl;
    }

    @PostMapping
    public ResponseEntity<Puntuacion> registrarPuntuacion(@RequestBody Puntuacion puntuacion) throws BadRequestException, ResourceNotFoundException {
        return new ResponseEntity<>(puntuacionServiceImpl.guardarPuntuacion(puntuacion), HttpStatus.CREATED);
    }

    @GetMapping("/buscarId/{id}")
    public ResponseEntity<Puntuacion> buscarPuntuacion(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(puntuacionServiceImpl.buscarPuntuacion(id).get());
    }

    @GetMapping("/producto/{id}")
    public ResponseEntity<List<Puntuacion>> buscarPuntuacionesPorProducto(@PathVariable("id") Producto id) {
        return ResponseEntity.ok(puntuacionServiceImpl.buscarPuntuacionesPorProducto(id));
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<Puntuacion>> buscarPuntuacionesPorUsuario(@PathVariable("id") Cliente id) {
        return ResponseEntity.ok(puntuacionServiceImpl.buscarPuntuacionesPorUsuario(id));
    }

    @GetMapping
    public ResponseEntity<List<Puntuacion>> buscarTodasLasPuntuaciones(){
        return ResponseEntity.ok(puntuacionServiceImpl.buscarTodasLasPuntuaciones());
    }

    @PutMapping
    public ResponseEntity<String> actualizarPuntuacion(@RequestBody Puntuacion puntuacion) throws BadRequestException, ResourceNotFoundException {
        puntuacionServiceImpl.actualizarPuntuacion(puntuacion);
        return ResponseEntity.ok("Se actualizó la puntuacion con id " + puntuacion.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPuntuacion(@PathVariable Long id) throws ResourceNotFoundException {
        puntuacionServiceImpl.eliminarPuntuacion(id);
        return ResponseEntity.ok().body("La puntuacion se eliminó correctamente");
    }
}
