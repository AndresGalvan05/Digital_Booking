package com.example.back.controller;

import com.example.back.dto.UsuarioDTO;
import com.example.back.exception.BadRequestException;
import com.example.back.exception.ResourceNotFoundException;
import com.example.back.model.RoleName;
import com.example.back.repository.RoleRepository;
import com.example.back.service.implementations.UsuarioServiceImpl;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @CrossOrigin
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioServiceImpl usuarioServiceImpl;
    private final RoleRepository roleRepository;

    @Autowired
    public UsuarioController(UsuarioServiceImpl usuarioServiceImpl,
                             RoleRepository roleRepository) {
        this.usuarioServiceImpl = usuarioServiceImpl;
        this.roleRepository = roleRepository;
    }

    @PostMapping
    public ResponseEntity<?> registrarUsuario(@RequestBody UsuarioDTO usuarioDTO) throws BadRequestException, MessagingException {
        return new ResponseEntity<>(usuarioServiceImpl.registrarUsuario(usuarioDTO), HttpStatus.CREATED);
    }

    @GetMapping("/confirmar-cuenta")
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token")String confirmationToken) {
        return usuarioServiceImpl.confirmarEmail(confirmationToken);
    }

    @GetMapping("/recuperar-password")
    public ResponseEntity<?> recuperarPassword(@RequestParam("email") String email) throws ResourceNotFoundException, MessagingException {
        return usuarioServiceImpl.recuperarPassword(email);
    }

    @GetMapping("/actualizar-password")
    public ResponseEntity<?> actualizarPassword(@RequestParam("token")String confirmationToken) throws MessagingException {
        return usuarioServiceImpl.actualizarPassword(confirmationToken);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> searchAll(){
        return ResponseEntity.ok(usuarioServiceImpl.buscarTodosLosUsuarios());
    }

    @GetMapping("/buscarId/{id}")
    public ResponseEntity<UsuarioDTO> buscarUsuario(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(usuarioServiceImpl.buscarUsuario(id));
    }

    @GetMapping("/buscarEmail/{email}")
    public ResponseEntity<UsuarioDTO> buscarUsuario(@PathVariable("email") String email) throws ResourceNotFoundException {
        return ResponseEntity.ok(usuarioServiceImpl.buscarUsuarioPorEmail(email));
    }

    @PutMapping
    public ResponseEntity<String> actualizarUsuario(@RequestBody UsuarioDTO usuarioDTO) throws BadRequestException, ResourceNotFoundException {
        usuarioServiceImpl.actualizarUsuario(usuarioDTO);
        return ResponseEntity.ok("Se actualizó al usuario con id " + usuarioDTO.getId());
    }

    @PutMapping("/roles/{email}/{newRole}")
    public void actualizarRole(@PathVariable String email, @PathVariable RoleName newRole) throws ResourceNotFoundException {
        usuarioServiceImpl.actualizarRole(email, roleRepository.findByRoleName(newRole).getRoleName());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id) throws ResourceNotFoundException {
        usuarioServiceImpl.eliminarUsuario(id);
        return ResponseEntity.ok().body("El usuario se eliminó correctamente");
    }
}