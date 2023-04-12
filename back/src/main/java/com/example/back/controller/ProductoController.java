package com.example.back.controller;

import com.example.back.dto.ProductoDTO;
import com.example.back.exception.BadRequestException;
import com.example.back.exception.ResourceNotFoundException;
import com.example.back.model.Categoria;
import com.example.back.model.Ciudad;
import com.example.back.model.Cliente;
import com.example.back.service.implementations.ProductoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController @CrossOrigin
@RequestMapping("/productos")
public class ProductoController {
    private final ProductoServiceImpl productoServiceImpl;

    @Autowired
    public ProductoController(ProductoServiceImpl productoServiceImpl) {
        this.productoServiceImpl = productoServiceImpl;
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> registrarProducto(@RequestBody ProductoDTO productoDTO) throws BadRequestException {
        return new ResponseEntity<>(productoServiceImpl.guardarProducto(productoDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> buscarProducto(@PathVariable("id") Long id) throws ResourceNotFoundException{
        return ResponseEntity.ok(productoServiceImpl.buscarProducto(id));
    }

    @GetMapping("/buscar-titulo/{titulo}")
    public ResponseEntity<ProductoDTO> buscarProductoPorTitulo(@PathVariable("titulo") String titulo) throws ResourceNotFoundException{
        return ResponseEntity.ok(productoServiceImpl.buscarPorTitulo(titulo));
    }

    @GetMapping("/ciudad/{id}")
    public ResponseEntity<List<ProductoDTO>> buscarProductoPorCiudad(@PathVariable("id") Ciudad id){
        List<ProductoDTO> productoDTOS = productoServiceImpl.buscarProductosPorCiudad(id);
        return ResponseEntity.ok(productoDTOS);
    }

    @GetMapping("/ciudad/{id}/fecha/{checkIn}&&{checkOut}")
    public ResponseEntity<List<ProductoDTO>> buscarProductosPorCiudadYFecha(@PathVariable("id") Ciudad id, @PathVariable("checkIn") String checkIn,
                                                                            @PathVariable("checkOut") String checkOut){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaInicio = LocalDate.parse(checkIn, formatter);
        LocalDate fechaFinal  = LocalDate.parse(checkOut, formatter);
        List<ProductoDTO> productoDTOS = productoServiceImpl.buscarProductosPorFechaYCiudad(id, fechaInicio, fechaFinal);
        return ResponseEntity.ok(productoDTOS);
    }

    @GetMapping("/fecha/{checkIn}&&{checkOut}")
    public ResponseEntity<List<ProductoDTO>> buscarProductoPorFechas(@PathVariable("checkIn") String checkIn, @PathVariable("checkOut") String checkOut){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaInicio = LocalDate.parse(checkIn, formatter);
        LocalDate fechaFinal  = LocalDate.parse(checkOut, formatter);
        List<ProductoDTO> productoDTOS = productoServiceImpl.buscarProductosPorFecha(fechaInicio, fechaFinal);
        return ResponseEntity.ok(productoDTOS);
    }

    @GetMapping("/categoria/{id}")
    public ResponseEntity<List<ProductoDTO>> buscarProductosPorCategoria(@PathVariable("id") Categoria id){
        List<ProductoDTO> productoDTOS = productoServiceImpl.buscarProductosPorCategoria(id);
        return  ResponseEntity.ok(productoDTOS);
    }

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> buscarTodosLosProductos(){
        return ResponseEntity.ok(productoServiceImpl.buscarTodosLosProductos());
    }

    @PutMapping
    public ResponseEntity<String> actualizarProducto(@RequestBody ProductoDTO productoDTO) throws ResourceNotFoundException{
        productoServiceImpl.actualizarProducto(productoDTO);
        return ResponseEntity.ok("El producto se actualizo correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable("id") Long id) throws ResourceNotFoundException{
        productoServiceImpl.eliminarProducto(id);
        return ResponseEntity.ok("El producto se elimino correctamente");
    }

    @GetMapping("/random")
    public ResponseEntity<List<ProductoDTO>> buscarProductosRandom(){
        List<ProductoDTO> productoDTOS = productoServiceImpl.buscarProductosRandom();
        return ResponseEntity.ok(productoDTOS);
    }

    @GetMapping("/favoritos/{user_id}")
    public ResponseEntity<List<ProductoDTO>> buscarProductosFavoritos(@PathVariable("user_id")Cliente cliente) throws ResourceNotFoundException{
        List<ProductoDTO> productoDTOS = productoServiceImpl.buscarProductosFavoritosPorCliente(cliente);
        return ResponseEntity.ok(productoDTOS);
    }
}
