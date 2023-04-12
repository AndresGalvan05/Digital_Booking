package com.example.back.service;

import com.example.back.dto.ProductoDTO;
import com.example.back.exception.BadRequestException;
import com.example.back.exception.ResourceNotFoundException;
import com.example.back.model.*;
import com.example.back.service.interfaces.ProductoService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class ProductoServiceImplTest {
    @Autowired
    ProductoService productoService;
    private static Long idGuardado;
    private static Categoria categoriaGuardada;
    private static Ciudad ciudadGuardada;

    @Test @Order(1)
    public void guardarProducto() throws BadRequestException {
        //GIVEN
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setTitulo("Hotel Maravilloso");
        productoDTO.setDescripcion("El mejor hotel de Buenos Aires");
        productoDTO.setLatitud((float) 15.1);
        productoDTO.setLongitud((float) 23.65156);

        /** CATEGORIA **/
        Categoria categoria = new Categoria();
        categoria.setId(3L);
        productoDTO.setCategoria(categoria);

        /** CIUDAD **/
        Ciudad ciudad = new Ciudad();
        ciudad.setId(3L);
        productoDTO.setCiudad(ciudad);

        //WHEN
        ProductoDTO productoDTOSaved = productoService.guardarProducto(productoDTO);
        idGuardado = productoDTOSaved.getId();
        categoriaGuardada = productoDTOSaved.getCategoria();
        ciudadGuardada = productoDTOSaved.getCiudad();

        //THEN
        assertNotNull(productoDTOSaved);
        assertNotNull(productoDTOSaved.getCiudad());
    }

    @Test @Order(2)
    public void buscarProductoPorId() throws ResourceNotFoundException {
        //WHEN
        ProductoDTO productoDTO = productoService.buscarProducto(idGuardado);

        //THEN
        assertNotNull(productoDTO);
    }

    @Test @Order(3)
    public void buscarProductosPorCategoria(){
        //WHEN
        List<ProductoDTO> productoDTOS = productoService.buscarProductosPorCategoria(categoriaGuardada);

        //THEN
        assertTrue(productoDTOS.size() > 0);
    }

    @Test @Order(4)
    public void buscarProductosPorCiudad(){
        //WHEN
        List<ProductoDTO> productoDTOS = productoService.buscarProductosPorCiudad(ciudadGuardada);

        //THEN
        assertTrue(productoDTOS.size() > 0);
    }

    @Test @Order(5)
    public void buscarProductosRandom(){
        //WHEN
        List<ProductoDTO> productoDTOS = productoService.buscarProductosRandom();

        //THEN
        assertEquals(productoDTOS.size(), 8);
    }

    @Test @Order(6)
    public void buscarTodosLosProductos(){
        //WHEN
        List<ProductoDTO> productoDTOS = productoService.buscarTodosLosProductos();

        //THEN
        assertTrue(productoDTOS.size() > 0);
    }

    @Test @Order(7)
    public void actualizarProducto() throws ResourceNotFoundException{
        //GIVEN
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(idGuardado);
        productoDTO.setTitulo("Producto Actualizado");
        productoDTO.setCiudad(ciudadGuardada);
        productoDTO.setCategoria(categoriaGuardada);
        productoDTO.setDescripcion("Descripcion Actualizada");

        //WHEN
        productoService.actualizarProducto(productoDTO);
        ProductoDTO productoDTOUpdated = productoService.buscarProducto(idGuardado);

        //THEN
        assertNotNull(productoDTOUpdated);
        assertEquals(productoDTOUpdated.getTitulo(), "Producto Actualizado");
        assertEquals(productoDTOUpdated.getDescripcion(), "Descripcion Actualizada");
    }

    @Test @Order(8)
    public void borrarProducto() throws ResourceNotFoundException{
        //WHEN
        productoService.eliminarProducto(idGuardado);

        //THEN
        ResourceNotFoundException thrown = assertThrows(
                ResourceNotFoundException.class,
                () -> {productoService.buscarProducto(idGuardado);}
        );

        assertEquals(("No se encontró ningun producto con id="+ idGuardado), thrown.getMessage());
    }

}
