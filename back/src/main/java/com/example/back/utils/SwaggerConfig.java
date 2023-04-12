package com.example.back.utils;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
            title = "Digital Booking",
            description =
                    "<pre>" +
                    "Aplicación web que brinda servicios de alquiler de alojamientos.\n" +
                    "Creada en el marco del Proyecto Integrador de la carrera Certified Tech Developer de Digital House.\n" +
                    "Desarrollado por: " +
                            "<a href='https://www.linkedin.com/in/andresgalvancutinella/'>Andrés Galván</a>" + ", " +
                            "<a href='https://www.linkedin.com/in/bertichelucas/'>Lucas Bertiche</a>" + ", " +
                            "<a href='https://www.linkedin.com/in/judithgrau/'>Judith Grau</a>" + ", " +
                            "<a href='https://www.linkedin.com/in/rodrigo-benitez-886462226/'>Rodrigo Benitez</a>" + ", " +
                            "<a href='https://www.linkedin.com/in/pablopalvarez/'>Pablo Álvarez</a>" + " y " +
                            "<a href='https://www.linkedin.com/in/paulacuna96/'>Paula Acuña</a>.\n" +
                    "<a href='http://g9c3-frontend.s3-website.us-east-2.amazonaws.com/'>Deploy</a>\n" +
                    "<a href='https://gitlab.ctd.academy/ctd/hispanos/proyecto-integrador-1/proyecto-integrador-0223/0522ft-c3/grupo-09/-/wikis/home'>Ver más</a>" +
                    "</pre>"
//            ,
//            contact = @Contact(
//                    name = "Andrés Galván",
//                    email = "8il.andre@gmail.com"
//            )
    )
)
public class SwaggerConfig {
}
