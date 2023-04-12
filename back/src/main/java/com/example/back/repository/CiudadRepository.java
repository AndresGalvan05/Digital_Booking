package com.example.back.repository;

import com.example.back.model.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, Long> {
    Optional<Ciudad> findByNombre(String nombre);
}