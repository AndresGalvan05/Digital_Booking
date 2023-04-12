package com.example.back.service.implementations;

import com.example.back.exception.BadRequestException;
import com.example.back.exception.ResourceNotFoundException;
import com.example.back.model.Cliente;
import com.example.back.model.Producto;
import com.example.back.model.Reserva;
import com.example.back.model.Usuario;
import com.example.back.repository.ProductoRepository;
import com.example.back.repository.ReservaRepository;
import com.example.back.repository.UsuarioRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service @Log4j
public class ReservaServiceImpl implements com.example.back.service.interfaces.ReservaService {
    private final ReservaRepository reservaRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;
    private final EmailServiceImpl emailServiceImpl;

    @Autowired
    public ReservaServiceImpl(ReservaRepository reservaRepository, UsuarioRepository usuarioRepository, ProductoRepository productoRepository, EmailServiceImpl emailServiceImpl) {
        this.reservaRepository = reservaRepository;
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
        this.emailServiceImpl = emailServiceImpl;
    }

    @Override
    public Reserva guardarReserva(Reserva reserva) throws BadRequestException, MessagingException {
        Reserva reservaGuardada = reservaRepository.save(reserva);
        Usuario usuarioDeReserva = usuarioRepository.findById(reserva.getCliente().getId()).get();
        Producto productoDeReserva = productoRepository.findById(reserva.getProducto().getId()).get();

        if (!usuarioDeReserva.isEstaHabilitado()){
            throw new BadRequestException("No se pudo guardar la reserva: el usuario no está habilitado para realizar reservas ya que aún no ha validado su cuenta.");
        }

        try {
            MimeMessage mimeMessage = emailServiceImpl.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setTo(usuarioDeReserva.getEmail());
            helper.setSubject("Reserva exitosa");
            String mensaje =
                "<html><body>" +
                "Estimado " + usuarioDeReserva.getNombre() + " " + usuarioDeReserva.getApellido() + ", su reserva fue realizada con éxito.<br><br>" +
                "A continuación le detallamos los datos de su hospedaje en " + productoDeReserva.getTitulo() + ": <br><br>" +
                "Fecha de inicio de la reserva: " + reservaGuardada.getFechaInicial() + ".<br>" +
                "Hora de check-in: " + reservaGuardada.getHoraReserva() + ".<br>" +
                "Fecha de fin de la reserva: " + reservaGuardada.getFechaFinal() + ".<br><br>" +
                "Atentamente, el equipo de Digital Booking<br>" +
                "<img src=\"https://gcdnb.pbrd.co/images/jCgkP5zkEXcP.png?o=1\">" +
                "</body></html>";
            helper.setText(mensaje, true);
            emailServiceImpl.sendEmail(mimeMessage);
            log.info("Se guardó una nueva reserva para el usuario " + usuarioDeReserva.getEmail() + " y el producto " + productoDeReserva.getTitulo());
        } catch (MessagingException e) {
            log.error("Error al enviar el email de confirmación de reserva: " + e.getMessage());
            throw new MessagingException("Error al enviar el email de confirmación de reserva: " + e.getMessage());
        }

        log.info("Se guardo una nueva reserva");
        return reservaGuardada;
    }

    @Override
    public Reserva buscarReserva(Long id) throws ResourceNotFoundException {
        Optional<Reserva> reservaBuscada = reservaRepository.findById(id);
        if (reservaBuscada.isPresent()){
            log.info("Se encontró la reserva buscada con id=" + id);
            return reservaBuscada.get();
        } else{
            log.info("No se encontró ninguna reserva con id=" + id);
            throw  new ResourceNotFoundException("No se encontro la reserva de id=" + id);
        }
    }

    @Override
    public List<Reserva> buscarReservasPorUsuario(Cliente usuario) {
        log.info("Se realizó una búsqueda de todas las puntuaciones del usuario con email "+ usuario.getEmail());
        return reservaRepository.findByCliente(usuario);
    }

    @Override
    public List<Reserva> buscarTodasLasReservas() {
        log.info("Se realizó una búsqueda de todas las reservas");
        return reservaRepository.findAll();
    }

    @Override
    public void actualizarReserva(Reserva reserva) throws ResourceNotFoundException {
        buscarReserva(reserva.getId());
        reservaRepository.save(reserva);
        log.info("Se actualizó la reserva con id="+reserva.getId());
    }

    @Override
    public void eliminarReserva(Long id) throws ResourceNotFoundException {
        buscarReserva(id);
        reservaRepository.deleteById(id);
        log.info("Se eliminó una reserva con el id=" + id);
    }
}
