package com.example.back.controller;

import com.example.back.exception.ResourceNotFoundException;
import com.example.back.model.Imagen;
import com.example.back.service.implementations.ImagenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @CrossOrigin
@RequestMapping("/imagenes")
public class ImagenController {
    private final ImagenServiceImpl imagenServiceImpl;

    @Autowired
    public ImagenController(ImagenServiceImpl imagenServiceImpl) {
        this.imagenServiceImpl = imagenServiceImpl;
    }

    @PostMapping
    public ResponseEntity<Imagen> registrarImagen(@RequestBody Imagen imagen) throws ResourceNotFoundException {
        return new ResponseEntity<>(imagenServiceImpl.guardarImagen(imagen), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Imagen>> searchAll(){
        return ResponseEntity.ok(imagenServiceImpl.buscarTodasLasImagenes());
    }

//    @GetMapping("/buscarId/{id}")
//    public ResponseEntity<Imagen> buscarImagen(@PathVariable("id") Long id) throws ResourceNotFoundException {
//        return ResponseEntity.ok(imagenService.buscarImagen(id).get());
//    }

//    @PutMapping
//    public ResponseEntity<String> actualizarImagen(@RequestBody Imagen imagen) throws BadRequestException, ResourceNotFoundException {
//        imagenService.actualizarImagen(imagen);
//        return ResponseEntity.ok("Se actualizó la imagen con id " + imagen.getId());
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarImagen(@PathVariable Long id) throws ResourceNotFoundException {
        imagenServiceImpl.eliminarImagen(id);
        return ResponseEntity.ok().body("La imagen se eliminó correctamente");
    }
}
