package com.example.back.service.interfaces;

import com.example.back.dto.ProductoDTO;
import com.example.back.exception.BadRequestException;
import com.example.back.exception.ResourceNotFoundException;
import com.example.back.model.Categoria;
import com.example.back.model.Ciudad;
import com.example.back.model.Cliente;
import com.example.back.model.Producto;

import java.time.LocalDate;
import java.util.List;

public interface ProductoService {
    ProductoDTO guardarProducto(ProductoDTO productoDTO) throws BadRequestException;
    ProductoDTO buscarProducto(Long id) throws ResourceNotFoundException;
    ProductoDTO buscarPorTitulo(String titulo) throws ResourceNotFoundException;
    List<ProductoDTO> buscarProductosPorCategoria(Categoria categoria);
    List<ProductoDTO> buscarProductosPorCiudad(Ciudad ciudad);
    List<ProductoDTO> buscarProductosFavoritosPorCliente(Cliente cliente);
    List<ProductoDTO> buscarProductosRandom();
    List<ProductoDTO> buscarTodosLosProductos();
    List<ProductoDTO> buscarProductosPorFecha(LocalDate checkIn, LocalDate checkOut);
    List<ProductoDTO> buscarProductosPorFechaYCiudad(Ciudad ciudad, LocalDate checkIn, LocalDate checkOut);
    List<ProductoDTO> listaDeProductosADTO(List<Producto> productos);
    void actualizarProducto(ProductoDTO productoDTO) throws ResourceNotFoundException;
    void eliminarProducto(Long id) throws ResourceNotFoundException;
}
