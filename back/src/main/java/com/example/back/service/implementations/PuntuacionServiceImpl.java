package com.example.back.service.implementations;

import com.example.back.exception.BadRequestException;
import com.example.back.exception.ResourceNotFoundException;
import com.example.back.model.Cliente;
import com.example.back.model.Producto;
import com.example.back.model.Puntuacion;
import com.example.back.repository.PuntuacionRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service @Log4j
public class PuntuacionServiceImpl implements com.example.back.service.interfaces.PuntuacionService {
    private final PuntuacionRepository puntuacionRepository;

    @Autowired
    public PuntuacionServiceImpl(PuntuacionRepository puntuacionRepository) {
        this.puntuacionRepository = puntuacionRepository;
    }

    @Override
    public Puntuacion guardarPuntuacion(Puntuacion puntuacion) throws BadRequestException {
        if (puntuacion.getPuntuacion() > 5 || puntuacion.getPuntuacion() < 1){
            throw new BadRequestException("La puntuación debe valer entre 1 y 5");
        }
        log.info("Se guardó una nueva puntuación");
        return puntuacionRepository.save(puntuacion);
    }

    @Override
    public Optional<Puntuacion> buscarPuntuacion(Long id) throws ResourceNotFoundException {
        Optional<Puntuacion> puntuacionBuscada = puntuacionRepository.findById(id);
        if (puntuacionBuscada.isPresent()){
            log.info("Se encontró una puntuación con id="+id);
            return Optional.of(puntuacionBuscada.get());
        } else {
            log.info("No se encontró ninguna puntuación con id="+id);
            throw new ResourceNotFoundException(("No se encontró ninguna puntuación con id="+id));
        }
    }

    @Override
    public List<Puntuacion> buscarPuntuacionesPorProducto(Producto producto) {
        log.info("Se realizó una búsqueda de todas las puntuaciones del producto "+ producto.getTitulo());
        return puntuacionRepository.findByProducto(producto);
    }

    @Override
    public List<Puntuacion> buscarPuntuacionesPorUsuario(Cliente usuario) {
        log.info("Se realizó una búsqueda de todas las puntuaciones del usuario con email "+ usuario.getEmail());
        return puntuacionRepository.findByCliente(usuario);
    }

    @Override
    public List<Puntuacion> buscarTodasLasPuntuaciones() {
        log.info("Se realizó una búsqueda de todas las puntuaciones");
        return puntuacionRepository.findAll();
    }

    @Override
    public void actualizarPuntuacion (Puntuacion puntuacion) throws ResourceNotFoundException {
        buscarPuntuacion(puntuacion.getId());
        puntuacionRepository.save(puntuacion);
        log.info("Se actualizó la puntuación con id="+puntuacion.getId());
    }

    @Override
    public void eliminarPuntuacion(Long id) throws ResourceNotFoundException {
        buscarPuntuacion(id);
        puntuacionRepository.deleteById(id);
        log.warn("Se eliminó la puntuación con id="+id);
    }
}
