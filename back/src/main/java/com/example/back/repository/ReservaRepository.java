package com.example.back.repository;

import com.example.back.model.Cliente;
import com.example.back.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByCliente(Cliente id);
}
