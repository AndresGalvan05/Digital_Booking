package com.example.back.controller;

import com.example.back.exception.BadRequestException;
import com.example.back.exception.ResourceNotFoundException;
import com.example.back.model.Politica;
import com.example.back.service.implementations.PoliticaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @CrossOrigin
@RequestMapping("/politicas")
public class PoliticaController {
    private final PoliticaServiceImpl politicaServiceImpl;

    @Autowired
    public PoliticaController(PoliticaServiceImpl politicaServiceImpl) {
        this.politicaServiceImpl = politicaServiceImpl;
    }

    @PostMapping
    public ResponseEntity<Politica> registrarPolitica(@RequestBody Politica politica) throws ResourceNotFoundException {
        return new ResponseEntity<>(politicaServiceImpl.guardarPolitica(politica), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Politica>> searchAll(){
        return ResponseEntity.ok(politicaServiceImpl.buscarTodasLasPoliticas());
    }

//    @GetMapping("/buscarId/{id}")
//    public ResponseEntity<Politica> buscarPolitica(@PathVariable("id") Long id) throws ResourceNotFoundException {
//        return ResponseEntity.ok(politicaService.buscarPolitica(id).get());
//    }

    @PutMapping
    public ResponseEntity<String> actualizarPolitica(@RequestBody Politica politica) throws BadRequestException, ResourceNotFoundException {
        politicaServiceImpl.actualizarPolitica(politica);
        return ResponseEntity.ok("Se actualizó la politica con id " + politica.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPolitica(@PathVariable Long id) throws ResourceNotFoundException {
        politicaServiceImpl.eliminarPolitica(id);
        return ResponseEntity.ok().body("La politica se eliminó correctamente");
    }
}
