package com.example.back.controller;

import com.example.back.exception.BadRequestException;
import com.example.back.exception.ResourceNotFoundException;
import com.example.back.model.Categoria;
import com.example.back.service.implementations.CategoriaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @CrossOrigin
@RequestMapping("/categorias")
public class CategoriaController {
    private final CategoriaServiceImpl categoriaServiceImpl;

    @Autowired
    public CategoriaController(CategoriaServiceImpl categoriaServiceImpl) {
        this.categoriaServiceImpl = categoriaServiceImpl;
    }

    @PostMapping
    public ResponseEntity<Categoria> registrarCategoria(@RequestBody Categoria categoria){
        return new ResponseEntity<>(categoriaServiceImpl.guardarCategoria(categoria), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> searchAll(){
        return ResponseEntity.ok(categoriaServiceImpl.buscarTodasLasCategorias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarCategoria(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(categoriaServiceImpl.buscarCategoria(id).get());
    }

    @PutMapping
    public ResponseEntity<String> actualizarCategoria(@RequestBody Categoria categoria) throws BadRequestException, ResourceNotFoundException {
        categoriaServiceImpl.actualizarCategoria(categoria);
        return ResponseEntity.ok("Se actualizó la categoría con id " + categoria.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCategoria(@PathVariable Long id) throws ResourceNotFoundException {
        categoriaServiceImpl.eliminarCategoria(id);
        return ResponseEntity.ok().body("La categoría se eliminó correctamente");
    }
}
