package com.example.back.service.implementations;

import com.example.back.exception.ResourceNotFoundException;
import com.example.back.model.Politica;
import com.example.back.model.Producto;
import com.example.back.repository.PoliticaRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service @Log4j
public class PoliticaServiceImpl implements com.example.back.service.interfaces.PoliticaService {
    private final PoliticaRepository politicaRepository;

    @Autowired
    public PoliticaServiceImpl(PoliticaRepository politicaRepository) {
        this.politicaRepository = politicaRepository;
    }

    @Override
    public Politica guardarPolitica(Politica politica) {
        log.info("Se guardó una nueva politica");
        return politicaRepository.save(politica);
    }

    @Override
    public Optional<Politica> buscarPolitica(Long id) throws ResourceNotFoundException {
        Optional<Politica> politicaBuscada = politicaRepository.findById(id);
        if (politicaBuscada.isPresent()){
            log.info("Se encontró una politica con id=" + id);
            return Optional.of(politicaBuscada.get());
        } else {
            log.info("No se encontró ninguna politica con id=" + id);
            throw new ResourceNotFoundException("No se encontró ninguna politica con id" + id);
        }
    }

    @Override
    public List<Politica> buscarTodasLasPoliticas() {
        log.info("Se realizó una búsqueda de todas las políticas");
        return politicaRepository.findAll();
    }

    @Override
    public void actualizarPolitica(Politica politica) throws ResourceNotFoundException {
        buscarPolitica(politica.getId());
        politicaRepository.save(politica);
        log.info("Se actualizó la politica con el id=" + politica.getId());
    }

    @Override
    public void eliminarPolitica(Long id) throws ResourceNotFoundException {
        Optional<Politica> politica = buscarPolitica(id);
        politica.get().removerPoliticaDeProducto();
        politicaRepository.deleteById(id);
        log.info("Se eliminó la politica con el id=" + id);
    }

    /** GUARDADO DE POLITICAS A PARTIR DE UN PRODUCTO **/
    @Override
    public void guardarPoliticas(Set<Politica> politicas, Producto producto) {
        if (politicas == null) return;
        for (Politica p : politicas) {
            p.setProducto(producto);
            guardarPolitica(p);
        }
    }
}
