package com.example.back.service.implementations;

import com.example.back.model.Cliente;
import com.example.back.model.Favorito;
import com.example.back.repository.FavoritoRepository;
import com.example.back.service.interfaces.FavoritoService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @Log4j
public class FavoritoServiceImpl implements FavoritoService {

    private final FavoritoRepository favoritoRepository;

    @Autowired
    public FavoritoServiceImpl(FavoritoRepository favoritoRepository) {
        this.favoritoRepository = favoritoRepository;
    }

    @Override
    public Favorito guardarFavorito(Favorito favorito) {
        System.out.println(favorito);
        return favoritoRepository.save(favorito);
    }

    @Override
    public List<Favorito> buscarFavoritosPorUsuario(Cliente id) {
        return favoritoRepository.findByCliente(id);
    }

    @Override
    public void borrarFavorito(Long id) {
        favoritoRepository.deleteById(id);
    }
}
