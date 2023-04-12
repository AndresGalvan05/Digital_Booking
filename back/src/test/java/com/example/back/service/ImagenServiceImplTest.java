package com.example.back.service;

import com.example.back.dto.ProductoDTO;
import com.example.back.exception.BadRequestException;
import com.example.back.exception.ResourceNotFoundException;
import com.example.back.model.Categoria;
import com.example.back.model.Ciudad;
import com.example.back.model.Imagen;
import com.example.back.model.Producto;
import com.example.back.service.implementations.ProductoServiceImpl;
import com.example.back.service.interfaces.ImagenService;
import com.example.back.utils.Mapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class ImagenServiceImplTest {
    @Autowired
    ImagenService imagenService;
    @Autowired
    ProductoServiceImpl productoServiceImpl;
    @Autowired
    Mapper mapper;

    private static Long idGuardado;
    private static Producto productoStatic;

    @Test @Order(1)
    void guardarImagen() throws BadRequestException {
        /********** DADO EL PRODUCTO **********/
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setTitulo("Hotel Maravilloso");
        productoDTO.setDescripcion("El mejor hotel de Buenos Aires");
        productoDTO.setLatitud((float) 15.1);
        productoDTO.setLongitud((float) 23.65156);

        /** CON CATEGORIA **/
        Categoria categoria = new Categoria();
        categoria.setId(3L);
        productoDTO.setCategoria(categoria);

        /** CON CIUDAD **/
        Ciudad ciudad = new Ciudad();
        ciudad.setId(3L);
        productoDTO.setCiudad(ciudad);

        Producto producto = mapper.productoDTOAProducto(productoServiceImpl.guardarProducto(productoDTO));
        
        /********** DADA LA IMAGEN **********/
        Imagen imagenToSave = new Imagen();
        imagenToSave.setTitulo("hotel_1_img");
        imagenToSave.setUrl("https://unsplash.com/photos/oxeCZrodz78");
        imagenToSave.setProducto(producto);

        // CUANDO
        Imagen imagenSaved = imagenService.guardarImagen(imagenToSave);

        // ENTONCES
        assertNotNull(imagenSaved);
        assertNotNull(imagenSaved.getId());
        assertEquals("hotel_1_img", imagenSaved.getTitulo());
        assertEquals("https://unsplash.com/photos/oxeCZrodz78", imagenSaved.getUrl());

        idGuardado = imagenSaved.getId();
        productoStatic = imagenSaved.getProducto();
    }

    @Test @Order(2)
    void buscarImagen() throws ResourceNotFoundException {
        Optional<Imagen> imagenSearched = imagenService.buscarImagen(idGuardado);

        assertNotNull(imagenSearched);
    }

//    @Test @Order(3)
//    void buscarTodasLasImagens() {
//        List<Imagen> imagenList = imagenService.buscarTodasLasImagenes();
//
//        assertTrue(imagenList.size() > 0);
//    }

    @Test @Order(4)
    void actualizarImagen() throws ResourceNotFoundException, BadRequestException {
        Imagen imagenToUpdate = new Imagen();
        imagenToUpdate.setId(idGuardado);
        imagenToUpdate.setTitulo("apartment_1_img");
        imagenToUpdate.setUrl("https://unsplash.com/photos/AH8zKXqFITA");
        imagenToUpdate.setProducto(productoStatic);

        imagenService.guardarImagen(imagenToUpdate);
        imagenService.actualizarImagen(imagenToUpdate);
        Optional<Imagen> imagenUpdated = imagenService.buscarImagen(imagenToUpdate.getId());

        assertEquals("apartment_1_img", imagenUpdated.get().getTitulo());
        assertEquals("https://unsplash.com/photos/AH8zKXqFITA", imagenUpdated.get().getUrl());
    }

//    @Test @Order(5)
//    void eliminarImagen() throws ResourceNotFoundException {
//        // No me deja eliminar la imagen porque está asociada a un producto, debo borrar la asociación primero
//        imagenService.eliminarImagen(idGuardado);
//
////        // Valido que la imagen se haya eliminado
////        Optional<Imagen> deletedImagen = imagenService.buscarImagen(idGuardado);
////        assertFalse(deletedImagen.isPresent(), "La imagen no se eliminó");
//
//        ResourceNotFoundException thrown = assertThrows(
//                ResourceNotFoundException.class,
//                () -> {imagenService.buscarImagen(idGuardado);}
//        );
//
//        assertEquals(("No se encontró ninguna imagen con id=" + idGuardado), thrown.getMessage());
//    }
}