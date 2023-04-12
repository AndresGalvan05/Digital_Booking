package com.example.back.utils;

import com.example.back.dto.*;
import com.example.back.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public Mapper() {
        mapper.registerModule(new JavaTimeModule());
    }

    public UsuarioDTO usuarioAUsuarioDTO(Usuario usuario){
        UsuarioDTO usuarioDTO = mapper.convertValue(usuario, UsuarioDTO.class);
        return usuarioDTO;
    }

    public Usuario usuarioDTOaUsuario(UsuarioDTO usuarioDTO){
        Usuario usuario = mapper.convertValue(usuarioDTO, Cliente.class);
        return usuario;
    }

    public ProductoDTO productoAProductoDTO(Producto producto){
        ProductoDTO productoDTO = mapper.convertValue(producto, ProductoDTO.class);
        productoDTO.setPromedio(producto.calcularPromedio());
        return productoDTO;
    }

    public Producto productoDTOAProducto(ProductoDTO productoDTO){
        Producto producto = mapper.convertValue(productoDTO, Producto.class);
        return producto;
    }
}
