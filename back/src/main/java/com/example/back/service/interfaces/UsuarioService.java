package com.example.back.service.interfaces;

import com.example.back.dto.UsuarioDTO;
import com.example.back.exception.BadRequestException;
import com.example.back.exception.ResourceNotFoundException;
import com.example.back.model.RoleName;
import com.example.back.model.Usuario;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UsuarioService {
    ResponseEntity<?> registrarUsuario(UsuarioDTO usuarioDTO) throws BadRequestException, MessagingException;
    ResponseEntity<?> confirmarEmail(String confirmationToken);
    ResponseEntity<?> recuperarPassword(String email) throws ResourceNotFoundException, MessagingException;
    ResponseEntity<?> actualizarPassword(String newPassword) throws MessagingException;
    UsuarioDTO buscarUsuario(Long id) throws ResourceNotFoundException;
    UsuarioDTO buscarUsuarioPorEmail(String email) throws ResourceNotFoundException;
    List<UsuarioDTO> buscarTodosLosUsuarios();
    List<UsuarioDTO> listaDeUsuariosADTO(List<Usuario> usuarios);
    void actualizarUsuario(UsuarioDTO usuarioDTO) throws ResourceNotFoundException;
    void actualizarRole(String email, RoleName roleName) throws ResourceNotFoundException;
    void eliminarUsuario(Long id) throws ResourceNotFoundException;
}
