package com.example.back.service.implementations;

import com.example.back.exception.ResourceNotFoundException;
import com.example.back.model.Ciudad;
import com.example.back.repository.CiudadRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service @Log4j
public class CiudadServiceImpl implements com.example.back.service.interfaces.CiudadService {
    private final CiudadRepository ciudadRepository;

    @Autowired
    public CiudadServiceImpl(CiudadRepository ciudadRepository) {
        this.ciudadRepository = ciudadRepository;
    }

    @Override
    public Ciudad guardarCiudad(Ciudad ciudad) {
        log.info("Se guardó una nueva ciudad");
        return ciudadRepository.save(ciudad);
    }

    @Override
    public Optional<Ciudad> buscarCiudad(Long id) throws ResourceNotFoundException {
        Optional<Ciudad> ciudadBuscada = ciudadRepository.findById(id);
        if (ciudadBuscada.isPresent()){
            log.info("Se encontró una ciudad con id="+id);
            return Optional.of(ciudadBuscada.get());
        } else {
            log.info("No se encontró ninguna ciudad con id="+id);
            throw new ResourceNotFoundException(("No se encontró ninguna ciudad con id="+id));
        }
    }

    @Override
    public Optional<Ciudad> buscarCiudadPorNombre(String nombre) throws ResourceNotFoundException {
        Optional<Ciudad> ciudadBuscada = ciudadRepository.findByNombre(nombre);
        if (ciudadBuscada.isPresent()){
            log.info("Se encontró una ciudad con nombre="+nombre);
            return Optional.of(ciudadBuscada.get());
        } else {
            log.info("No se encontró ninguna ciudad con nombre="+nombre);
            throw new ResourceNotFoundException(("No se encontró ninguna ciudad con nombre="+nombre));
        }
    }

    @Override
    public List<Ciudad> buscarTodasLasCiudades() {
        log.info("Se realizó una búsqueda de todas las ciudades");
        return ciudadRepository.findAll();
    }

    @Override
    public void actualizarCiudad (Ciudad ciudad) throws ResourceNotFoundException {
        buscarCiudad(ciudad.getId());
        ciudadRepository.save(ciudad);
        log.info("Se actualizó la ciudad con id="+ciudad.getId());
    }

    @Override
    public void eliminarCiudad(Long id) throws ResourceNotFoundException {
        buscarCiudad(id);
        ciudadRepository.deleteById(id);
        log.warn("Se eliminó la ciudad con id="+id);
    }
}
