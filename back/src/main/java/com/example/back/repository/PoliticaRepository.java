package com.example.back.repository;

import com.example.back.model.Politica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoliticaRepository extends JpaRepository<Politica, Long> {
}
