package com.example.back.service.implementations;

import com.example.back.exception.ResourceNotFoundException;
import com.example.back.model.Imagen;
import com.example.back.model.Producto;
import com.example.back.repository.ImagenRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service @Log4j
public class ImagenServiceImpl implements com.example.back.service.interfaces.ImagenService {
    private final ImagenRepository imagenRepository;

    @Autowired
    public ImagenServiceImpl(ImagenRepository imagenRepository) {
        this.imagenRepository = imagenRepository;
    }

    @Override
    public Imagen guardarImagen(Imagen imagen) {
        log.info("Se guardó una nueva imagen");
        return imagenRepository.save(imagen);
    }

    @Override
    public Optional<Imagen> buscarImagen(Long id) throws ResourceNotFoundException {
        Optional<Imagen> imagenBuscada = imagenRepository.findById(id);
        if (imagenBuscada.isPresent()){
            log.info("Se encontró una imagen con id=" + id);
            return Optional.of(imagenBuscada.get());
        } else {
            log.info("No se encontró ninguna imagen con id=" + id);
            throw new ResourceNotFoundException("No se encontró ninguna imagen con id=" + id);
        }
    }

    @Override
    public List<Imagen> buscarTodasLasImagenes() {
        log.info("Se realizó una búsqueda de todas las imágenes");
        return imagenRepository.findAll();
    }

    @Override
    public void actualizarImagen(Imagen imagen) throws ResourceNotFoundException {
        buscarImagen(imagen.getId());
        imagenRepository.save(imagen);
        log.info("Se actualizó la imagen con el id=" + imagen.getId());
    }

    @Override
    public void eliminarImagen(Long id) throws ResourceNotFoundException {
        Optional<Imagen> imagen = buscarImagen(id);
        imagen.get().removerImagenDeProducto();
        imagenRepository.deleteById(id);
        log.info("Se eliminó la imagen con el id=" + id);
    }

    /** GUARDADO DE IMAGENES A PARTIR DE UN PRODUCTO **/
    @Override
    public void guardarImagenes(Set<Imagen> imagenes, Producto producto) {
        if (imagenes == null) return;
        for (Imagen i : imagenes) {
            i.setProducto(producto);
            imagenRepository.save(i);
        }
    }
}
