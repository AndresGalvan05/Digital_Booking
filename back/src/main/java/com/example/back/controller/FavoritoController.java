package com.example.back.controller;

import com.example.back.exception.ResourceNotFoundException;
import com.example.back.model.Cliente;
import com.example.back.model.Favorito;
import com.example.back.service.interfaces.FavoritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @CrossOrigin
@RequestMapping("/favoritos")
public class FavoritoController {

    private final FavoritoService favoritoService;

    @Autowired
    public FavoritoController(FavoritoService favoritoService) {
        this.favoritoService = favoritoService;
    }

    @PostMapping
    public ResponseEntity<Favorito> guardarFavorito(@RequestBody Favorito favorito){
        System.out.println(favorito);
        return new ResponseEntity<>(favoritoService.guardarFavorito(favorito), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarFavorito(@PathVariable Long id) throws ResourceNotFoundException {
        favoritoService.borrarFavorito(id);
        return ResponseEntity.ok().body("Se elimino el producto de favoritos");
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<List<Favorito>> buscarFavoritosPorUsuario(@PathVariable("user_id") Cliente id){
        List<Favorito > favoritos = favoritoService.buscarFavoritosPorUsuario(id);
        return ResponseEntity.ok(favoritos);
    }
}
