package com.example.back.service.implementations;

import com.example.back.exception.ResourceNotFoundException;
import com.example.back.model.Caracteristica;
import com.example.back.repository.CaracteristicaRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service @Log4j
public class CaracteristicaServiceImpl implements com.example.back.service.interfaces.CaracteristicaService {
    private final CaracteristicaRepository caracteristicaRepository;

    @Autowired
    public CaracteristicaServiceImpl(CaracteristicaRepository caracteristicaRepository) {
        this.caracteristicaRepository = caracteristicaRepository;
    }

    @Override
    public Caracteristica guardarCaracteristica(Caracteristica caracteristica) {
        log.info("Se guardó una nueva característica");
        return caracteristicaRepository.save(caracteristica);
    }

    @Override
    public Optional<Caracteristica> buscarCaracteristica(Long id) throws ResourceNotFoundException {
        Optional<Caracteristica> caracteristicaBuscada = caracteristicaRepository.findById(id);
        if (caracteristicaBuscada.isPresent()){
            log.info("Se encontró una característica con id="+id);
            return Optional.of(caracteristicaBuscada.get());
        } else {
            log.info("No se encontró ninguna característica con id="+id);
            throw new ResourceNotFoundException(("No se encontró ninguna característica con id="+id));
        }
    }

    @Override
    public List<Caracteristica> buscarTodasLasCaracteristicas() {
        log.info("Se realizó una búsqueda de todas las características");
        return caracteristicaRepository.findAll();
    }

    @Override
    public void actualizarCaracteristica (Caracteristica caracteristica) throws ResourceNotFoundException {
        buscarCaracteristica(caracteristica.getId());
        caracteristicaRepository.save(caracteristica);
        log.info("Se actualizó la característica con id="+caracteristica.getId());
    }

    @Override
    public void eliminarCaracteristica(Long id) throws ResourceNotFoundException {
        buscarCaracteristica(id);
        caracteristicaRepository.deleteById(id);
        log.warn("Se eliminó la característica con id="+id);
    }
}
