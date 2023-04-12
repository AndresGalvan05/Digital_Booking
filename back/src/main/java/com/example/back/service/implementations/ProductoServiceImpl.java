package com.example.back.service.implementations;

import com.example.back.dto.ProductoDTO;
import com.example.back.exception.BadRequestException;
import com.example.back.exception.ResourceNotFoundException;
import com.example.back.model.*;
import com.example.back.repository.ProductoRepository;
import com.example.back.utils.Mapper;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j
public class ProductoServiceImpl implements com.example.back.service.interfaces.ProductoService {
    private final ProductoRepository productoRepository;
    private final ImagenServiceImpl imagenServiceImpl;
    private final PoliticaServiceImpl politicaServiceImpl;
    private final PuntuacionServiceImpl puntuacionServiceImpl;
    private final Mapper mapper;

    @Autowired
    public ProductoServiceImpl(ProductoRepository productoRepository, ImagenServiceImpl imagenServiceImpl, PoliticaServiceImpl politicaServiceImpl, PuntuacionServiceImpl puntuacionServiceImpl, Mapper mapper) {
        this.productoRepository = productoRepository;
        this.imagenServiceImpl = imagenServiceImpl;
        this.politicaServiceImpl = politicaServiceImpl;
        this.puntuacionServiceImpl = puntuacionServiceImpl;
        this.mapper = mapper;
    }

    @Override
    public ProductoDTO guardarProducto(ProductoDTO productoDTO) throws BadRequestException {
        Producto producto = mapper.productoDTOAProducto(productoDTO);

        producto.asignarProductoAEntidades();
        productoRepository.save(producto);
        log.info("Se guardó un nuevo producto");
        return mapper.productoAProductoDTO(producto);
    }

    @Override
    public ProductoDTO buscarProducto(Long id) throws ResourceNotFoundException {
        Optional<Producto> productoBuscado = productoRepository.findById(id);
        if (productoBuscado.isPresent()){
            log.info("Se encontró un producto con id="+id);
            return mapper.productoAProductoDTO(productoBuscado.get());
        } else {
            log.info("No se encontró ningún producto con id="+id);
            throw new ResourceNotFoundException(("No se encontró ningun producto con id="+id));
        }
    }

    @Override
    public ProductoDTO buscarPorTitulo(String titulo) throws ResourceNotFoundException {
        Producto producto = productoRepository.findOneByTitulo(titulo);
        log.info("Se realizó una búsqueda de un producto con titulo=" + titulo);
        return mapper.productoAProductoDTO(producto);
    }

    @Override
    public List<ProductoDTO> buscarProductosPorCategoria(Categoria categoria){
        List<Producto> productos = productoRepository.findByCategoria(categoria);
        log.info("Se realizó una búsqueda de todos los productos con categoria de id=" + categoria.getId());
        return listaDeProductosADTO(productos);
    }

    @Override
    public List<ProductoDTO> buscarProductosPorCiudad(Ciudad ciudad){
        List<Producto> productos = productoRepository.findByCiudad(ciudad);
        log.info("Se realizó una búsqueda de todos los productos con ciudad de id=" + ciudad.getId());
        return listaDeProductosADTO(productos);
    }

    @Override
    public List<ProductoDTO> buscarProductosFavoritosPorCliente(Cliente cliente) {
        List<Producto> productos = productoRepository.findFavoriteProductsUsingClientID(cliente);
        log.info("Se realizó una búsqueda de todos los productos favoritos para el cliente con id=" + cliente.getId());
        return listaDeProductosADTO(productos);
    }

    @Override
    public List<ProductoDTO> buscarProductosRandom() {
        List<Producto> productos = productoRepository.findRandomProducts();
        log.info("Se realiza la búsqueda de productos random");
        return listaDeProductosADTO(productos);
    }

    @Override
    public List<ProductoDTO> buscarTodosLosProductos() {
        List<Producto> productos  = productoRepository.findAll();
        log.info("Se realizó una búsqueda de todos los productos");
        return listaDeProductosADTO(productos);
    }

    @Override
    public List<ProductoDTO> buscarProductosPorFecha(LocalDate checkIn, LocalDate checkOut) {
        List<Producto> productos = productoRepository.findUsingDateRange(checkIn, checkOut);
        log.info("Se realizó una búsqueda de todos los productos por fecha");
        return listaDeProductosADTO(productos);
    }



    @Override
    public List<ProductoDTO> buscarProductosPorFechaYCiudad(Ciudad ciudad, LocalDate checkIn, LocalDate checkOut) {
        List<Producto> productos = productoRepository.findUsingCityAndDateRange(ciudad, checkIn, checkOut);
        log.info("Se realizó una búsqueda de todos los productos por fecha y por ciudad");
        return listaDeProductosADTO(productos);
    }

    @Override
    public void actualizarProducto(ProductoDTO productoDTO) throws ResourceNotFoundException {
        ProductoDTO productoBuscado = buscarProducto(productoDTO.getId());
        Producto producto = mapper.productoDTOAProducto(productoDTO);
        producto.asignarProductoAEntidades();
        productoRepository.save(producto);
        log.info("Se actualizó el producto con el id=" + productoDTO.getId());
    }

    @Override
    public void eliminarProducto(Long id) throws ResourceNotFoundException {
        buscarProducto(id);
        productoRepository.deleteById(id);
        log.info("Se eliminó el producto con el id=" + id);
    }

    /**** DTO FUNCIONES AUXILIARES ****/
    @Override
    public List<ProductoDTO> listaDeProductosADTO(List<Producto> productos){
        List<ProductoDTO> respuesta = new ArrayList<>();

        for (Producto prod : productos) {
            respuesta.add(mapper.productoAProductoDTO(prod));
        }
        return respuesta;
    }
}
