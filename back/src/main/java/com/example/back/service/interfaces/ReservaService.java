package com.example.back.service.interfaces;

import com.example.back.exception.BadRequestException;
import com.example.back.exception.ResourceNotFoundException;
import com.example.back.model.Cliente;
import com.example.back.model.Reserva;
import jakarta.mail.MessagingException;

import java.util.List;

public interface ReservaService {
    Reserva guardarReserva(Reserva reserva) throws BadRequestException, MessagingException;
    Reserva buscarReserva(Long id) throws ResourceNotFoundException;
    List<Reserva> buscarTodasLasReservas();
    List<Reserva> buscarReservasPorUsuario(Cliente id);
    void actualizarReserva(Reserva reserva) throws ResourceNotFoundException;
    void eliminarReserva(Long id) throws ResourceNotFoundException;
}
