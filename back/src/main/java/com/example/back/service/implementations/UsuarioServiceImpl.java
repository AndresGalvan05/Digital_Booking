package com.example.back.service.implementations;

import com.example.back.dto.UsuarioDTO;
import com.example.back.exception.BadRequestException;
import com.example.back.exception.ResourceNotFoundException;
import com.example.back.model.Role;
import com.example.back.model.RoleName;
import com.example.back.model.Usuario;
import com.example.back.repository.RoleRepository;
import com.example.back.repository.UsuarioRepository;
import com.example.back.utils.Mapper;
import com.example.back.utils.PasswordEncoder;
import com.example.back.model.ConfirmationToken;
import com.example.back.repository.ConfirmationTokenRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service @Log4j
public class UsuarioServiceImpl implements com.example.back.service.interfaces.UsuarioService {
    private final RoleRepository roleRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final UsuarioRepository usuarioRepository;
    private final Mapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final EmailServiceImpl emailServiceImpl;

    @Autowired
    public UsuarioServiceImpl(ConfirmationTokenRepository confirmationTokenRepository, UsuarioRepository usuarioRepository,
                              Mapper mapper, PasswordEncoder passwordEncoder, EmailServiceImpl emailServiceImpl,
                              RoleRepository roleRepository) {
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.usuarioRepository = usuarioRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.emailServiceImpl = emailServiceImpl;
        this.roleRepository = roleRepository;
    }

    @Override
    public ResponseEntity<?> registrarUsuario(UsuarioDTO usuario) throws BadRequestException, MessagingException {
        if (usuarioRepository.existsByEmailIgnoreCase(usuario.getEmail())) {
            throw new BadRequestException("Ya existe un usuario con el email "+usuario.getEmail());
        }

        usuario.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(usuario.getPassword()));
        usuario.setRole(roleRepository.findByRoleName(RoleName.ROLE_USER));

        Usuario usuarioGuardado = usuarioRepository.save(mapper.usuarioDTOaUsuario(usuario));

        ConfirmationToken confirmationToken = new ConfirmationToken(usuarioGuardado);
        confirmationTokenRepository.save(confirmationToken);

        try {
            MimeMessage mimeMessage = emailServiceImpl.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setTo(usuario.getEmail());
            helper.setSubject("Validar cuenta");
            String link = "<a href='http://g9c3-frontend.s3-website.us-east-2.amazonaws.com/usuarios/confirmar-cuenta?token="+confirmationToken.getToken()+"'>validar cuenta</a>.";
            String mensaje =
                "<html><body>" +
                "Estimado " + usuarioGuardado.getNombre() + " " + usuarioGuardado.getApellido() + ", su registro está casi listo.<br><br>" +
                "Para validar su cuenta, por favor haga click en el siguiente enlace: " + link + "<br><br>" +
                "Atentamente, el equipo de Digital Booking<br>" +
                "<img src=\"https://gcdnb.pbrd.co/images/jCgkP5zkEXcP.png?o=1\">" +
                "</body></html>";
            helper.setText(mensaje, true);
            emailServiceImpl.sendEmail(mimeMessage);
            log.info("Se guardó un nuevo usuario con id="+usuarioGuardado.getId()+" y email="+usuarioGuardado.getEmail());
        } catch (MessagingException e) {
            log.error("Error al enviar el email de confirmación de cuenta: " + e.getMessage());
            throw new MessagingException("Error al enviar el email de confirmación de cuenta: " + e.getMessage());
        }

        return ResponseEntity.ok().body("Verifica tu email con el link enviado a tu correo electrónico");
    }

    @Override
    public ResponseEntity<?> confirmarEmail(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByToken(confirmationToken);

        if(token != null) {
            Optional<Usuario> usuario = usuarioRepository.findOneByEmailIgnoreCase(token.getUsuario().getEmail());
            usuario.get().setEstaHabilitado(true);
            usuarioRepository.save(usuario.get());
            log.info("Se validó el email del usuario con id="+usuario.get().getId()+" y email="+usuario.get().getEmail());

            confirmationTokenRepository.deleteById(token.getTokenId());
            return ResponseEntity.ok().body("Cuenta validada");
        } else {
            return ResponseEntity.badRequest().body("Error: no se pudo validar la cuenta");
        }
    }

    @Override
    public ResponseEntity<?> recuperarPassword (String email) throws ResourceNotFoundException, MessagingException {
        if (!usuarioRepository.existsByEmailIgnoreCase(email)) {
            throw new ResourceNotFoundException("No existe un usuario con email " + email);
        }
        Optional<Usuario> usuario = usuarioRepository.findOneByEmailIgnoreCase(email);
        ConfirmationToken confirmationToken = new ConfirmationToken(usuario.get());
        confirmationTokenRepository.save(confirmationToken);

        try {
            MimeMessage mimeMessage = emailServiceImpl.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setTo(email);
            helper.setSubject("Recuperar contraseña");
            String link = "<a href='http://g9c3-frontend.s3-website.us-east-2.amazonaws.com/usuarios/actualizar-password?token="+confirmationToken.getToken()+"'>reestablecer contraseña</a>.";
            String mensaje =
                "<html><body>" +
                "Estimado " + usuario.get().getNombre() + " " + usuario.get().getApellido() + ", recibimos una solicitud para reestablecer su contraseña de Digital Booking.<br><br>" +
                "Para reestablecerla, ingrese al siguiente enlace: " + link + "<br>" +
                "Si no realizó esta solicitud, por favor ignore este email.<br><br>" +
                "Atentamente, el equipo de Digital Booking<br>" +
                "<img src=\"https://gcdnb.pbrd.co/images/jCgkP5zkEXcP.png?o=1\">" +
                "</body></html>";
            helper.setText(mensaje, true);
            emailServiceImpl.sendEmail(mimeMessage);
            log.info("Se envió un email de recuperación de contraseña al usuario con id " + usuario.get().getId() + " y email " + usuario.get().getEmail());
        } catch (MessagingException e) {
            log.error("Error al enviar el email de recuperación de contraseña: " + e.getMessage());
            throw new MessagingException("Error al enviar el email de recuperación de contraseña: " + e.getMessage());
        }

        return ResponseEntity.ok().body("Se envió un email a su correo electrónico con las instrucciones para recuperar su contraseña");
    }

    @Override
    public ResponseEntity<?> actualizarPassword(String confirmationToken) throws MessagingException {
        ConfirmationToken token = confirmationTokenRepository.findByToken(confirmationToken);

        if(token != null) {
            Optional<Usuario> usuario = usuarioRepository.findOneByEmailIgnoreCase(token.getUsuario().getEmail());
            String newPassword = RandomStringUtils.randomAlphanumeric(10);
            usuario.get().setPassword(newPassword);
            usuarioRepository.save(usuario.get());
            log.info("Su actualizó la contraseña del usuario con id " + usuario.get().getId() + " y email " + usuario.get().getEmail());

            try {
                MimeMessage mimeMessage = emailServiceImpl.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
                helper.setTo(usuario.get().getEmail());
                helper.setSubject("Nueva contraseña");
                String mensaje =
                    "<html><body>" +
                    "Estimado " + usuario.get().getNombre() + " " + usuario.get().getApellido() + ", su contraseña ha sido actualizada.<br><br>" +
                    "Su nueva contraseña es: " + newPassword + "<br>" +
                    "Puede actualizar su contraseña por una de su preferencia en las opciones de su perfil..<br><br>" +
                    "Atentamente, el equipo de Digital Booking<br>" +
                    "<img src=\"https://gcdnb.pbrd.co/images/jCgkP5zkEXcP.png?o=1\">" +
                    "</body></html>";
                helper.setText(mensaje, true);
                emailServiceImpl.sendEmail(mimeMessage);
                log.info("Se actualizó la contraseña del usuario con id " + usuario.get().getId() + " y email " + usuario.get().getEmail());
            } catch (MessagingException e) {
                log.error("Error al enviar el email para actualizar la contraseña: " + e.getMessage());
                throw new MessagingException("Error al enviar el email para actualizar la contraseña: " + e.getMessage());
            }

            confirmationTokenRepository.deleteById(token.getTokenId());
            return ResponseEntity.ok().body("Contraseña actualizada");
        } else {
            return ResponseEntity.badRequest().body("Error: no se pudo actualizar la contraseña");
        }
    }

    @Override
    public UsuarioDTO buscarUsuario(Long id) throws ResourceNotFoundException {
        Optional<Usuario> usuarioBuscado = usuarioRepository.findById(id);
        if (usuarioBuscado.isPresent()){
            log.info("Se encontró un usuario con id="+id);
            return mapper.usuarioAUsuarioDTO(usuarioBuscado.get());
        } else {
            log.info("No se encontró ningún usuario con id="+id);
            throw new ResourceNotFoundException(("No se encontró ningún usuario con id="+id));
        }
    }

    @Override
    public UsuarioDTO buscarUsuarioPorEmail(String email) throws ResourceNotFoundException {
        Optional<Usuario> usuarioBuscado = usuarioRepository.findOneByEmailIgnoreCase(email);
        if (usuarioBuscado.isPresent()){
            log.info("Se encontró un usuario con email="+email);
            return mapper.usuarioAUsuarioDTO(usuarioBuscado.get());
        } else {
            log.info("No se encontró ningún usuario con email="+email);
            throw new ResourceNotFoundException(("No se encontró ningún usuario con email="+email));
        }
    }

    @Override
    public List<UsuarioDTO> buscarTodosLosUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        log.info("Se realizó una búsqueda de todos los usuarios");
        return listaDeUsuariosADTO(usuarios);
    }

    @Override
    public void actualizarUsuario (UsuarioDTO usuarioDTO) throws ResourceNotFoundException {
        buscarUsuario(usuarioDTO.getId());
        usuarioRepository.save(mapper.usuarioDTOaUsuario(usuarioDTO));
        log.info("Se actualizó al usuario con id="+usuarioDTO.getId());
    }

    @Override
    public void actualizarRole (String email, RoleName roleName) throws ResourceNotFoundException {
        Optional<Usuario> usuarioBuscado = usuarioRepository.findOneByEmailIgnoreCase(email);
        if (usuarioBuscado.isPresent()){
            Role newRole = roleRepository.findByRoleName(roleName);
            usuarioBuscado.get().setRole(newRole);
            usuarioRepository.save(usuarioBuscado.get());
            log.info("Se actualizó el role del usuario con email " + email);
        } else {
            throw new ResourceNotFoundException("No se encontró ningún usuario con email " + email);
        }
    }

    @Override
    public void eliminarUsuario(Long id) throws ResourceNotFoundException {
        buscarUsuario(id);
        usuarioRepository.deleteById(id);
        log.warn("Se eliminó al usuario con id="+id);
    }

    /**** DTO FUNCIONES AUXILIARES ****/
    @Override
    public List<UsuarioDTO> listaDeUsuariosADTO(List<Usuario> usuarios){
        List<UsuarioDTO> respuesta = new ArrayList<>();

        for (Usuario user : usuarios) {
            respuesta.add(mapper.usuarioAUsuarioDTO(user));
        }
        return respuesta;
    }
}
