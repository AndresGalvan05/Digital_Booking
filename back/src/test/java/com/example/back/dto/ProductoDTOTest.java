package com.example.back.dto;

import com.example.back.model.Ciudad;
import com.example.back.model.Producto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductoDTOTest {

    @Test
    public void entidadADTO(){
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setTitulo("titulo");
        producto.setDescripcion("descripcion");

        //Agrego ciudasd
        Ciudad ciudad = new Ciudad();
        ciudad.setId(1L);
        ciudad.setNombre("CABA");
        ciudad.setPais("ARG");
    }
}
