package com.example.back.repository;

import com.example.back.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findOneByEmailIgnoreCase(String email);
    Boolean existsByEmailIgnoreCase(String email);
}
