package com.example.back.repository;

import com.example.back.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByCategoria(Categoria id);
    List<Producto> findByCiudad(Ciudad id);
    Producto findOneByTitulo(String titulo);

    @Query(value = "select p from Producto p order by RAND() limit 8")
    List<Producto> findRandomProducts();

    @Query(value = "select p from Producto p where p.id not in (select distinct r.producto from Reserva r " +
                "where r.fechaFinal >= ?1 AND r.fechaInicial <= ?2)")
    List<Producto> findUsingDateRange(LocalDate checkIn, LocalDate checkOut);

    @Query(value = "select p from Producto p where p.ciudad = ?1 AND p.id not in (select distinct r.producto from Reserva r " +
            "where r.fechaFinal >= ?2 AND r.fechaInicial <= ?3)")
    List<Producto> findUsingCityAndDateRange(Ciudad id, LocalDate checkIn, LocalDate checkOut);

    @Query(value = "select p from Producto p where p.id in (select distinct f.producto from Favorito f " +
            "where f.cliente = ?1)")
    List<Producto> findFavoriteProductsUsingClientID(Cliente id);
}
