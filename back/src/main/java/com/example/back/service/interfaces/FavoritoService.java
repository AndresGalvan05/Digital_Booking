package com.example.back.service.interfaces;

import com.example.back.model.Cliente;
import com.example.back.model.Favorito;

import java.util.List;

public interface FavoritoService {

    Favorito guardarFavorito(Favorito Favorito);

    List<Favorito> buscarFavoritosPorUsuario(Cliente id);
    void borrarFavorito(Long id);
}
