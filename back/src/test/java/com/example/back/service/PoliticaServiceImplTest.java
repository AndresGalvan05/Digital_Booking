package com.example.back.service;

import com.example.back.dto.ProductoDTO;
import com.example.back.exception.BadRequestException;
import com.example.back.exception.ResourceNotFoundException;
import com.example.back.model.Categoria;
import com.example.back.model.Ciudad;
import com.example.back.model.Politica;
import com.example.back.model.Producto;
import com.example.back.service.implementations.ProductoServiceImpl;
import com.example.back.service.interfaces.PoliticaService;
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
public class PoliticaServiceImplTest {
    @Autowired
    PoliticaService politicaService;
    @Autowired
    ProductoServiceImpl productoServiceImpl;
    @Autowired
    Mapper mapper;

    private static Long idGuardado;
    private static Producto productoStatic;

    @Test @Order(1)
    void guardarPolitica() throws ResourceNotFoundException, BadRequestException {
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

        /********** DADA LA POLITICA **********/

        Politica politicaToSave = new Politica();
        politicaToSave.setTitulo("Normas de la casa");
        politicaToSave.setDescripcion("Check out 10:00");
        politicaToSave.setProducto(producto);

        // CUANDO
        Politica politicaSaved = politicaService.guardarPolitica(politicaToSave);

        // ENTONCES
        assertNotNull(politicaSaved);
        assertNotNull(politicaSaved.getId());
        assertEquals("Normas de la casa", politicaSaved.getTitulo());
        assertEquals("Check out 10:00", politicaSaved.getDescripcion());

        idGuardado = politicaSaved.getId();
        productoStatic = politicaSaved.getProducto();
    }

    @Test @Order(2)
    void buscarPolitica() throws ResourceNotFoundException {
        Optional<Politica> politicaSearched = politicaService.buscarPolitica(idGuardado);

        assertNotNull(politicaSearched);
    }

//    @Test @Order(3)
//    void buscarTodasLasPoliticas() {
//        List<Politica> politicaList = politicaService.buscarTodasLasPoliticas();
//
//        assertTrue(politicaList.size() > 0);
//    }

    @Test @Order(4)
    void actualizarPolitica() throws ResourceNotFoundException {
        Politica politicaToUpdate = new Politica();
        politicaToUpdate.setId(idGuardado);
        politicaToUpdate.setTitulo("Política de cancelación");
        politicaToUpdate.setDescripcion("Agregá las fechas de tu viaje para obtener los detalles de cancelación de esta estadía.");
        politicaToUpdate.setProducto(productoStatic);

        politicaService.guardarPolitica(politicaToUpdate);
        politicaService.actualizarPolitica(politicaToUpdate);
        Optional<Politica> politicaUpdated = politicaService.buscarPolitica(politicaToUpdate.getId());

        assertEquals("Política de cancelación", politicaUpdated.get().getTitulo());
        assertEquals("Agregá las fechas de tu viaje para obtener los detalles de cancelación de esta estadía.", politicaUpdated.get().getDescripcion());
    }

//    @Test @Order(5)
//    void eliminarPolitica() throws ResourceNotFoundException {
//        politicaService.eliminarPolitica(idGuardado);
//
//        ResourceNotFoundException thrown = assertThrows(
//                ResourceNotFoundException.class,
//                () -> politicaService.buscarPolitica(idGuardado).isPresent()
//        );
//
//        assertTrue(thrown.getMessage().contains("No se encontró ninguna política con id=" + idGuardado));
//    }
}