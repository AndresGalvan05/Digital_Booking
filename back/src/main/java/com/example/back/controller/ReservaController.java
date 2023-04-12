package com.example.back.controller;

import com.example.back.exception.BadRequestException;
import com.example.back.exception.ResourceNotFoundException;
import com.example.back.model.Cliente;
import com.example.back.model.Reserva;
import com.example.back.service.interfaces.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/reservas")
public class ReservaController {
    private final ReservaService reservaService;

    @Autowired
    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    public ResponseEntity<Reserva> registrarReserva(@RequestBody Reserva reserva) {
        try {
            return new ResponseEntity<>(reservaService.guardarReserva(reserva), HttpStatus.CREATED);
        } catch (BadRequestException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity("Ocurrió un error inesperado: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Reserva> buscarReserva(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(reservaService.buscarReserva(id));
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<Reserva>> buscarReservasPorUsuario(@PathVariable("id") Cliente id) {
        List<Reserva> reservas = reservaService.buscarReservasPorUsuario(id);
        return ResponseEntity.ok(reservas);
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> searchAll(){
        return ResponseEntity.ok(reservaService.buscarTodasLasReservas());
    }

    @PutMapping
    public ResponseEntity<String> actualizarReserva(@RequestBody Reserva reserva) throws ResourceNotFoundException {
        reservaService.actualizarReserva(reserva);
        return ResponseEntity.ok("Se actualizó la reserva con id " + reserva.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarReserva(@PathVariable Long id) throws ResourceNotFoundException {
        reservaService.eliminarReserva(id);
        return new ResponseEntity<String>("La reserva se eliminó correctamente", HttpStatus.OK);
    }
}
