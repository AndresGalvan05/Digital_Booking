//package com.example.back.service;
//
//import com.example.back.dto.ProductoDTO;
//import com.example.back.dto.UsuarioDTO;
//import com.example.back.exception.BadRequestException;
//import com.example.back.exception.ResourceNotFoundException;
//import com.example.back.model.*;
//import com.example.back.service.implementations.ProductoService;
//import com.example.back.service.implementations.UsuarioService;
//import com.example.back.service.interfaces.IPuntuacionService;
//import com.example.back.utils.Mapper;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@SpringBootTest
//public class PuntuacionServiceTest {
//
//    @Autowired
//    IPuntuacionService puntuacionService;
//    @Autowired
//    ProductoService productoService;
//    @Autowired
//    UsuarioService usuarioService;
//    @Autowired
//    Mapper mapper;
//
//    private static Long idGuardado;
//    private static Producto productoStatic;
//    private static Usuario usuarioStatic;
//    @Test
//    @Order(1)
//    void guardarPuntuacion() throws BadRequestException, ResourceNotFoundException {
//        /********** DADO EL PRODUCTO **********/
//        ProductoDTO productoDTO = new ProductoDTO();
//        productoDTO.setTitulo("Hotel Maravilloso");
//        productoDTO.setDescripcion("El mejor hotel de Buenos Aires");
//        productoDTO.setLatitud((float) 15.1);
//        productoDTO.setLongitud((float) 23.65156);
//
//        /** CON CATEGORIA **/
//        Categoria categoria = new Categoria();
//        categoria.setId(3L);
//        productoDTO.setCategoria(categoria);
//
//        /** CON CIUDAD **/
//        Ciudad ciudad = new Ciudad();
//        ciudad.setId(3L);
//        productoDTO.setCiudad(ciudad);
//
//        Producto producto = mapper.productoDTOAProducto(productoService.guardarProducto(productoDTO));
//
//        /********** DADO EL USUARIO **********/
//        UsuarioDTO usuarioDTO = new UsuarioDTO();
//        usuarioDTO.setNombre("Andrés");
//        usuarioDTO.setApellido("Galván");
//        usuarioDTO.setEmail("8il.andre@gmail.com");
//
//        Usuario usuario = mapper.usuarioDTOaUsuario(usuarioService.guardarUsuario(usuarioDTO));
//
//        /********** DADA LA PUNTUACION **********/
//        Puntuacion puntuacionToSave = new Puntuacion();
//        puntuacionToSave.setPuntuacion(5);
//        puntuacionToSave.setProducto(producto);
//        puntuacionToSave.setUsuario(usuario);
//
//        Puntuacion puntuacionSaved = puntuacionService.guardarPuntuacion(puntuacionToSave);
//
//        assertNotNull(puntuacionSaved);
//        assertNotNull(puntuacionSaved.getId());
//        assertEquals(5, puntuacionSaved.getPuntuacion());
//
//        idGuardado = puntuacionSaved.getId();
//        productoStatic = puntuacionSaved.getProducto();
//        usuarioStatic = puntuacionSaved.getUsuario();
//    }
//
//    @Test @Order(2)
//    void buscarPuntuacion() throws ResourceNotFoundException {
//        Optional<Puntuacion> puntuacionSearched = puntuacionService.buscarPuntuacion(idGuardado);
//
//        assertNotNull(puntuacionSearched);
//    }
//
//    @Test @Order(3)
//    void buscarTodasLasPuntuacions() {
//        List<Puntuacion> puntuacionList = puntuacionService.buscarTodasLasPuntuaciones();
//
//        assertTrue(puntuacionList.size() >= 1);
//    }
//
//    @Test @Order(4)
//    void actualizarPuntuacion() throws ResourceNotFoundException, BadRequestException {
//        //GIVEN
//        Puntuacion puntuacionToUpdate = new Puntuacion();
//        puntuacionToUpdate.setId(idGuardado);
//        puntuacionToUpdate.setPuntuacion(1);
//        puntuacionToUpdate.setProducto(productoStatic);
//        puntuacionToUpdate.setUsuario(usuarioStatic);
//
//        //WHEN
//        puntuacionService.guardarPuntuacion(puntuacionToUpdate);
//        puntuacionService.actualizarPuntuacion(puntuacionToUpdate);
//        Optional<Puntuacion> puntuacionUpdated = puntuacionService.buscarPuntuacion(puntuacionToUpdate.getId());
//
//        assertEquals(1, puntuacionUpdated.get().getPuntuacion());
//    }
//
////    @Test @Order(5)
////    void eliminarPuntuacion() throws ResourceNotFoundException {
////        puntuacionService.eliminarPuntuacion(idGuardado);
////
////        ResourceNotFoundException thrown = assertThrows(
////                ResourceNotFoundException.class,
////                () -> {puntuacionService.buscarPuntuacion(idGuardado);}
////        );
////
////        assertEquals(("No se encontró la puntuación con id" + idGuardado), thrown.getMessage());
////    }
//}
