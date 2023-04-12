//package com.example.back.service;
//
//import com.example.back.dto.UsuarioDTO;
//import com.example.back.exception.BadRequestException;
//import com.example.back.exception.ResourceNotFoundException;
//import com.example.back.model.Ciudad;
//import com.example.back.model.Role;
//import com.example.back.model.RoleEnum;
//import com.example.back.service.interfaces.IUsuarioService;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@SpringBootTest
//public class UsuarioServiceTest {
//    @Autowired
//    IUsuarioService usuarioService;
//    private static Long idGuardado;
//    private static String emailGuardado;
//
//    @Test @Order(1)
//    public void guardarUsuario() throws BadRequestException {
//        //GIVEN
//        Role rol = new Role(1L, RoleEnum.ROLE_ADMIN);
//        Ciudad ciudad = new Ciudad("Ciudad x", "País Z");
//
//        UsuarioDTO usuarioDTO = new UsuarioDTO();
//        usuarioDTO.setNombre("Andrés");
//        usuarioDTO.setApellido("Galván");
//        usuarioDTO.setEmail("il.andre@hotmail.com");
//        usuarioDTO.setPassword("Pass123");
//        usuarioDTO.setRole(rol);
//        usuarioDTO.setCiudad(ciudad);
//
//        //WHEN
//        UsuarioDTO usuarioDTOSaved = usuarioService.guardarUsuario(usuarioDTO);
//        idGuardado = usuarioDTOSaved.getId();
//        emailGuardado = usuarioDTOSaved.getEmail();
//
//        //THEN
//        assertNotNull(usuarioDTOSaved);
//        assertNotNull(usuarioDTOSaved.getEmail());
//    }
//
//    @Test @Order(2)
//    public void buscarUsuarioPorId() throws ResourceNotFoundException {
//        //WHEN
//        UsuarioDTO usuarioDTO = usuarioService.buscarUsuario(idGuardado);
//
//        //THEN
//        assertNotNull(usuarioDTO);
//    }
//
//    @Test @Order(3)
//    public void buscarUsuarioPorEmail() throws ResourceNotFoundException {
//        //WHEN
//        UsuarioDTO usuarioFound = usuarioService.buscarUsuarioPorEmail(emailGuardado);
//
//        //THEN
//        assertNotNull(usuarioFound);
//    }
//
//    @Test @Order(4)
//    public void buscarTodosLosUsuarios(){
//        //WHEN
//        List<UsuarioDTO> usuarioDTOS = usuarioService.buscarTodosLosUsuarios();
//
//        //THEN
//        assertTrue(usuarioDTOS.size() > 0);
//    }
//
//    @Test @Order(5)
//    public void actualizarUsuario() throws ResourceNotFoundException{
//        //GIVEN
//        UsuarioDTO usuarioDTO = new UsuarioDTO();
//        usuarioDTO.setId(idGuardado);
//        usuarioDTO.setNombre("Lucas");
//        usuarioDTO.setApellido("Bertiche");
//        usuarioDTO.setEmail("prueba@gmail.com");
//
//        //WHEN
//        usuarioService.actualizarUsuario(usuarioDTO);
//        UsuarioDTO usuarioDTOUpdated = usuarioService.buscarUsuario(idGuardado);
//
//        //THEN
//        assertNotNull(usuarioDTOUpdated);
//        assertEquals(usuarioDTOUpdated.getNombre(), "Lucas");
//    }
//
//    @Test @Order(6)
//    public void borrarUsuario() throws ResourceNotFoundException{
//        //WHEN
//        usuarioService.eliminarUsuario(idGuardado);
//
//        //THEN
//        ResourceNotFoundException thrown = assertThrows(
//                ResourceNotFoundException.class,
//                () -> {usuarioService.buscarUsuario(idGuardado);}
//        );
//
//        assertEquals(("No se encontró ningún usuario con id="+ idGuardado), thrown.getMessage());
//    }
//}
