use `0223TDPROM1C3LAED0522FT_GRUPO9`;
SET SQL_SAFE_UPDATES = 0;
SET FOREIGN_KEY_CHECKS=0; #para mantener el sql safe y el foreign key check se cambia de 0 a 1
###	Eliminar datos pre-existentes
delete from categorias;
delete from ciudades;
delete from producto_caracteristica;
delete from caracteristicas;
delete from reservas;
delete from productos;
delete from usuarios;
delete from roles;
delete from imagenes;
delete from politicas;
delete from puntuaciones;
delete from favoritos;
delete from confirmation_tokens;

#### Insertar categorias
insert into categorias (id, titulo, descripcion, url_imagen) values ('1', 'Hoteles', 'Alojamientos de tipo hotel', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Categoria/hotel.jpg' );
insert into categorias (id, titulo, descripcion, url_imagen) values ('2', 'Hostels', 'Alojamientos de tipo hostel', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Categoria/hostel.jpg' );
insert into categorias (id, titulo, descripcion, url_imagen) values ('3', 'Departamentos', 'Alojamientos de tipo departamento', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Categoria/departamento.jpg' );
insert into categorias (id, titulo, descripcion, url_imagen) values ('4', 'Bed and Breakfast', 'Alojamientos de tipo bed and breakfast', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Categoria/bedandbreakfast.jpg' );
select * from categorias;

### Insertar ciudades
insert into ciudades (id, nombre, pais) values ('1','Florencia','Italia');
insert into ciudades (id, nombre, pais) values ('2','Lima','Peru');
insert into ciudades (id, nombre, pais) values ('3','Cordoba','Argentina');
insert into ciudades (id, nombre, pais) values ('4','Paris','Francia');
insert into ciudades (id, nombre, pais) values ('5','Miami','Estados Unidos');
insert into ciudades (id, nombre, pais) values ('6','Madrid','España');
insert into ciudades (id, nombre, pais) values ('7','Pinamar','Argentina');
insert into ciudades (id, nombre, pais) values ('8','Florianópolis','Brasil');
select * from ciudades;

### Insertar productos
insert into productos (id, titulo, descripcion, categoria_id, ciudad_id, direccion, latitud, longitud) values ('1','Hotel Hermitage','El Hotel Hermitage, situado junto al Ponte Vecchio, goza de vistas panorámicas al centro histórico de Florencia desde la terraza de la azotea. Ofrece habitaciones tradicionales con aire acondicionado, TV y baño privado.
Las habitaciones del Hermitage incluyen ducha o bañera de hidromasaje, y están decoradas con muebles antiguos y alfombras florentinas. Algunas tienen vistas al río Arno.
','1','1','Viali di Circonvallazione 458','43.768700','11.256930');
insert into productos (id, titulo, descripcion, categoria_id, ciudad_id, direccion, latitud, longitud) values ('2','Hotel Melia Lima','El Hotel Meliá Lima, de 5 estrellas, ofrece habitaciones muy confortables equipadas con TV de pantalla plana por cable en el distrito de San Isidro de Lima.
El Hotel Meliá Lima cuenta con una pileta y un centro de bienestar con sauna, gimnasio, bañera de hidromasaje y servicio de masajes. El hotel ofrece conexión de wifi gratis en las zonas comunes.
','2','2','Av. Abancay 732','-12.046373','-77.042755');
insert into productos (id, titulo, descripcion, categoria_id, ciudad_id, direccion, latitud, longitud) values ('3','Departamento Cañada','El Departamento en la Cañada ofrece alojamiento en Córdoba, a menos de 1 km de la catedral de Córdoba. El alojamiento se encuentra a 13 minutos a pie de la Manzana Jesuítica.
El departamento tiene acceso directo a un balcón, aire acondicionado, cocina totalmente equipada y TV de pantalla plana.
','3','3','Paseo San Martín 501','-31.420082','-64.188774');
insert into productos (id, titulo, descripcion, categoria_id, ciudad_id, direccion, latitud, longitud) values ('4','La Villa Paris B&B','La Villa Paris - B&B ocupa un edificio construido en 1920, ubicado en la zona sur de París, a 550 metros del estadio Charléty. Ofrece alojamiento de tipo bed and breakfast con WiFi gratuita.
Las habitaciones incluyen aire acondicionado, TV de pantalla plana, escritorio y baño con artículos de aseo gratuitos, ducha y secador de pelo.
','4','4','Rue Saint-Rustique 284','48.8507','2.33364');
insert into productos (id, titulo, descripcion, categoria_id, ciudad_id, direccion, latitud, longitud) values ('5','The Palms Hotel','Este hotel se encuentra frente al mar, en Miami Beach, y cuenta con jardín tropical y piscina al aire libre.
Todas las habitaciones del The Palms Hotel & Spa disponen de TV de pantalla plana. Disponen de escritorio, utensilios de planchado y artículos de aseo BeeKind®. Las habitaciones ofrecen vistas a los jardines del hotel, al paisaje urbano o a las playas del océano Atlántico.
','1','5','Miracle Mile 802','25.7741728','-80.19362');
insert into productos (id, titulo, descripcion, categoria_id, ciudad_id, direccion, latitud, longitud) values ('6','Petit Hostel','El Petit Hostel ofrece habitaciones con aire acondicionado y baño privado en Madrid, a 500 metros de la Puerta de Toledo y a 1,5 km del mercado de San Miguel. También cuenta con salón compartido, recepción 24 horas y WiFi gratuita en todas las instalaciones. 
El establecimiento es hipoalergénico y se encuentra a 1,8 km de la Plaza Mayor.
','2','6','Puerta del Sol 580','40.4167047','-3.7035825');
insert into productos (id, titulo, descripcion, categoria_id, ciudad_id, direccion, latitud, longitud) values ('7','Casa Del Mar','Casa Del Mar se encuentra a 20 minutos en auto de las famosas playas de Pinamar y ofrece pileta al aire libre y jardín. Hay WiFi y estacionamiento gratuitos.
Las habitaciones son amplias y luminosas, y tienen aire acondicionado, teléfono, minibar, escritorio y baño privado con artículos de aseo.
Todos los días se prepara un desayuno buffet con una variedad de frutas, jugos naturales y pasteles.
','3','7','Arca de Noe 196','-37.1099492','-56.8539007');
insert into productos (id, titulo, descripcion, categoria_id, ciudad_id, direccion, latitud, longitud) values ('8','Tri Hotel Florianópolis','El Tri Hotel Florianópolis dispone de un spa de 2 plantas y un restaurante con estrella Michelin, y está junto al jardín botánico de Giardino della Gherardesca. Hay una amplia variedad de suites y habitaciones de lujo.
Las habitaciones son amplias y luminosas, y tienen una decoración renacentista, TV LCD, reproductor de DVD, albornoces, pantuflas y una amplia variedad de artículos modernos.
El Four Seasons consta de 2 edificios, el Palazzo della Gherardesca, del siglo XV, y el antiguo convento del siglo XVI La Villa. Hay 4,5 hectáreas de hermosos jardines entre ellos.
','4','8','Rua Mae Angêlica 315','-27.6078','-48.5925');
select * from productos;

### Insertar imagenes por producto
insert into imagenes (id, titulo, url, producto_id) values ('1','BNBBrasil1', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/BNBBrasil/BNBBrasil1.jpg', '8');
insert into imagenes (id, titulo, url, producto_id) values ('2','BNBBrasil2', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/BNBBrasil/BNBBrasil2.jpg', '8');
insert into imagenes (id, titulo, url, producto_id) values ('3','BNBBrasil3', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/BNBBrasil/BNBBrasil3.jpg', '8');
insert into imagenes (id, titulo, url, producto_id) values ('4','BNBBrasil4', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/BNBBrasil/BNBBrasil4.jpg', '8');
insert into imagenes (id, titulo, url, producto_id) values ('5','BNBBrasil5', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/BNBBrasil/BNBBrasil5.jpg', '8');
insert into imagenes (id, titulo, url, producto_id) values ('6','BNBBrasil6', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/BNBBrasil/BNBBrasil6.jpg', '8');
insert into imagenes (id, titulo, url, producto_id) values ('7','BNBBrasil7', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/BNBBrasil/BNBBrasil7.jpg', '8');
insert into imagenes (id, titulo, url, producto_id) values ('8','BNBParis1', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/BNBParis/BNBParis1.jpg', '4');
insert into imagenes (id, titulo, url, producto_id) values ('9','BNBParis2', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/BNBParis/BNBParis2.jpg', '4');
insert into imagenes (id, titulo, url, producto_id) values ('10','BNBParis3', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/BNBParis/BNBParis3.jpg', '4');
insert into imagenes (id, titulo, url, producto_id) values ('11','BNBParis4', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/BNBParis/BNBParis4.jpg', '4');
insert into imagenes (id, titulo, url, producto_id) values ('12','BNBParis5', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/BNBParis/BNBParis5.jpg', '4');
insert into imagenes (id, titulo, url, producto_id) values ('13','DepaPinamar1', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/DepaPinamar/DepaPinamar1.jpg', '7');
insert into imagenes (id, titulo, url, producto_id) values ('14','DepaPinamar2', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/DepaPinamar/DepaPinamar2.jpg', '7');
insert into imagenes (id, titulo, url, producto_id) values ('15','DepaPinamar3', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/DepaPinamar/DepaPinamar3.jpg', '7');
insert into imagenes (id, titulo, url, producto_id) values ('16','DepaPinamar4', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/DepaPinamar/DepaPinamar4.jpg', '7');
insert into imagenes (id, titulo, url, producto_id) values ('17','DepaPinamar5', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/DepaPinamar/DepaPinamar5.jpg', '7');
insert into imagenes (id, titulo, url, producto_id) values ('18','DepaPinamar6', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/DepaPinamar/DepaPinamar6.jpg', '7');
insert into imagenes (id, titulo, url, producto_id) values ('19','DepaCordoba1', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/DepartamentoCordoba/DepaCordoba1.jpg', '3');
insert into imagenes (id, titulo, url, producto_id) values ('20','DepaCordoba2', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/DepartamentoCordoba/DepaCordoba2.jpg', '3');
insert into imagenes (id, titulo, url, producto_id) values ('21','DepaCordoba3', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/DepartamentoCordoba/DepaCordoba3.jpg', '3');
insert into imagenes (id, titulo, url, producto_id) values ('22','DepaCordoba4', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/DepartamentoCordoba/DepaCordoba4.jpg', '3');
insert into imagenes (id, titulo, url, producto_id) values ('23','DepaCordoba5', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/DepartamentoCordoba/DepaCordoba5.jpg', '3');
insert into imagenes (id, titulo, url, producto_id) values ('24','DepaCordoba6', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/DepartamentoCordoba/DepaCordoba6.jpg', '3');
insert into imagenes (id, titulo, url, producto_id) values ('25','HostalEspana1', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/HostalEspana/HostalEspana1.jpg', '6');
insert into imagenes (id, titulo, url, producto_id) values ('26','HostalEspana2', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/HostalEspana/HostalEspana2.jpg', '6');
insert into imagenes (id, titulo, url, producto_id) values ('27','HostalEspana3', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/HostalEspana/HostalEspana3.jpg', '6');
insert into imagenes (id, titulo, url, producto_id) values ('28','HostalEspana4', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/HostalEspana/HostalEspana4.jpg', '6');
insert into imagenes (id, titulo, url, producto_id) values ('29','HostalEspana5', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/HostalEspana/HostalEspana5.jpg', '6');
insert into imagenes (id, titulo, url, producto_id) values ('30','HostalEspana6', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/HostalEspana/HostalEspana6.jpg', '6');
insert into imagenes (id, titulo, url, producto_id) values ('31','HostalEspana7', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/HostalEspana/HostalEspana7.jpg', '6');
insert into imagenes (id, titulo, url, producto_id) values ('32','HostalPeru1', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/HostalPeru/HostalPeru1.jpg', '2');
insert into imagenes (id, titulo, url, producto_id) values ('33','HostalPeru2', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/HostalPeru/HostalPeru2.jpg', '2');
insert into imagenes (id, titulo, url, producto_id) values ('34','HostalPeru3', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/HostalPeru/HostalPeru3.jpg', '2');
insert into imagenes (id, titulo, url, producto_id) values ('35','HostalPeru4', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/HostalPeru/HostalPeru4.jpg', '2');
insert into imagenes (id, titulo, url, producto_id) values ('36','HostalPeru5', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/HostalPeru/HostalPeru5.jpg', '2');
insert into imagenes (id, titulo, url, producto_id) values ('37','HostalPeru6', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/HostalPeru/HostalPeru6.jpg', '2');
insert into imagenes (id, titulo, url, producto_id) values ('38','HostalPeru7', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/HostalPeru/HostalPeru7.jpg', '2');
insert into imagenes (id, titulo, url, producto_id) values ('39','HostalPeru8', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/HostalPeru/HostalPeru8.jpg', '2');
insert into imagenes (id, titulo, url, producto_id) values ('40','hotelitaly1', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/HotelItalia/hotelitaly1.jpg', '1');
insert into imagenes (id, titulo, url, producto_id) values ('41','hotelitaly2', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/HotelItalia/hotelitaly2.jpg', '1');
insert into imagenes (id, titulo, url, producto_id) values ('42','hotelitaly3', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/HotelItalia/hotelitaly3.jpg', '1');
insert into imagenes (id, titulo, url, producto_id) values ('43','hotelitaly4', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/HotelItalia/hotelitaly4.jpg', '1');
insert into imagenes (id, titulo, url, producto_id) values ('44','hotelitaly5', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/HotelItalia/hotelitaly5.jpg', '1');
insert into imagenes (id, titulo, url, producto_id) values ('45','hotelitaly6', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/HotelItalia/hotelitaly6.jpg', '1');
insert into imagenes (id, titulo, url, producto_id) values ('46','hotelitaly7', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/HotelItalia/hotelitaly7.jpg', '1');
insert into imagenes (id, titulo, url, producto_id) values ('47','HotelMiami1', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/HotelMiami/HotelMiami1.jpg', '5');
insert into imagenes (id, titulo, url, producto_id) values ('48','HotelMiami2', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/HotelMiami/HotelMiami2.jpg', '5');
insert into imagenes (id, titulo, url, producto_id) values ('49','HotelMiami3', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/HotelMiami/HotelMiami3.jpg', '5');
insert into imagenes (id, titulo, url, producto_id) values ('50','HotelMiami4', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/HotelMiami/HotelMiami4.jpg', '5');
insert into imagenes (id, titulo, url, producto_id) values ('51','HotelMiami5', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/HotelMiami/HotelMiami5.jpg', '5');
insert into imagenes (id, titulo, url, producto_id) values ('52','HotelMiami6', 'https://g9c3-productos.s3.us-east-2.amazonaws.com/Hoteles/HotelMiami/HotelMiami6.jpg', '5');
select * from imagenes;

### Insertar caracteristicas (FONTAWESOME)
insert into caracteristicas (id, nombre, icono) values ('1','WiFi','<FontAwesomeIcon icon="fa-solid fa-wifi" />');
insert into caracteristicas (id, nombre, icono) values ('2','Apto mascotas','<FontAwesomeIcon icon="fa-solid fa-paw-simple" />');
insert into caracteristicas (id, nombre, icono) values ('3','Estacionamiento gratuito','<FontAwesomeIcon icon="fa-solid fa-square-parking" />');
insert into caracteristicas (id, nombre, icono) values ('4','Televisor','<FontAwesomeIcon icon="fa-duotone fa-tv" />');
insert into caracteristicas (id, nombre, icono) values ('5','Pileta','<FontAwesomeIcon icon="fa-solid fa-person-swimming" />');
insert into caracteristicas (id, nombre, icono) values ('6','Cocina','<FontAwesomeIcon icon="fa-solid fa-kitchen-set" />');
insert into caracteristicas (id, nombre, icono) values ('7','Cerca de la playa','<FontAwesomeIcon icon="fa-solid fa-umbrella-beach" />');
insert into caracteristicas (id, nombre, icono) values ('8','Gimnasios cerca','<FontAwesomeIcon icon="fa-solid fa-dumbbell" />');
insert into caracteristicas (id, nombre, icono) values ('9','Spa','<FontAwesomeIcon icon="fa-solid fa-spa" />');
insert into caracteristicas (id, nombre, icono) values ('10','Bar','<FontAwesomeIcon icon="fa-solid fa-champagne-glasses" />');
select * from caracteristicas ;

### Insertar Politicas

## Politicas Hoteles
insert into politicas (id, titulo, descripcion, producto_id) values ('1','Normas de la casa','CHECK-IN: Recuerda que los horarios de registro son 10:00 am, 10:30 am, 11:00 am y 11:30 am.','1');
insert into politicas (id, titulo, descripcion, producto_id) values ('2','Normas de la casa','CHECK-OUT: La habitación debe ser entregada antes de las 12:00PM horas del día que finalice su estadía.','1');
insert into politicas (id, titulo, descripcion, producto_id) values ('3','Normas de la casa','No fumar','1');

insert into politicas (id, titulo, descripcion, producto_id) values ('4','Salud y seguridad','Se aplican las pautas de distanciamiento social y otras normas relacionadas con el coronavirus','1');
insert into politicas (id, titulo, descripcion, producto_id) values ('5','Salud y seguridad','Detector de humo','1');
insert into politicas (id, titulo, descripcion, producto_id) values ('6','Salud y seguridad','Depósito de seguridad','1');

insert into politicas (id, titulo, descripcion, producto_id) values ('7','Politica de cancelacion','Agregá las fechas de tu viaje para obtener los detalles de cancelacion de esta estadia','1');

insert into politicas (id, titulo, descripcion, producto_id) values ('8','Normas de la casa','CHECK-IN: Recuerda que los horarios de registro es a las 11:00 am ','5');
insert into politicas (id, titulo, descripcion, producto_id) values ('9','Normas de la casa','CHECK-OUT: La habitación debe ser entregada antes de las 9:00PM horas del día que finalice su estadía.','5');
insert into politicas (id, titulo, descripcion, producto_id) values ('10','Normas de la casa','Esta prohibido introducir alimentos y bebidas externas en áreas públicas del hotel. Solo se admite el consumo dentro de su habitación.','5');

insert into politicas (id, titulo, descripcion, producto_id) values ('11','Salud y seguridad','Se aplican las pautas de distanciamiento social y otras normas relacionadas con el coronavirus','5');
insert into politicas (id, titulo, descripcion, producto_id) values ('12','Salud y seguridad','Detector de humo','5');
insert into politicas (id, titulo, descripcion, producto_id) values ('13','Salud y seguridad','Depósito de seguridad','5');

insert into politicas (id, titulo, descripcion, producto_id) values ('14','Politica de cancelacion','Agregá las fechas de tu viaje para obtener los detalles de cancelacion de esta estadia','5');

## Politicas Hostels
insert into politicas (id, titulo, descripcion, producto_id) values ('15','Normas de la casa','CHECK-IN: Recuerda que los horarios de registro son pasadas las 12:00AM.','2');
insert into politicas (id, titulo, descripcion, producto_id) values ('16','Normas de la casa','CHECK-OUT: La habitación debe ser entregada antes de las 18:00PM horas del día que finalice su estadía.','2');
insert into politicas (id, titulo, descripcion, producto_id) values ('17','Normas de la casa','No bebidas alcoholicas','2');

insert into politicas (id, titulo, descripcion, producto_id) values ('18','Salud y seguridad','Se aplican las pautas de distanciamiento social y otras normas relacionadas con el coronavirus','2');
insert into politicas (id, titulo, descripcion, producto_id) values ('19','Salud y seguridad','Detector de humo','2');
insert into politicas (id, titulo, descripcion, producto_id) values ('20','Salud y seguridad','Depósito de seguridad','2');

insert into politicas (id, titulo, descripcion, producto_id) values ('21','Politica de cancelacion','Agregá las fechas de tu viaje para obtener los detalles de cancelacion de esta estadia','2');

insert into politicas (id, titulo, descripcion, producto_id) values ('22','Normas de la casa','CHECK-IN: Recuerda que los horarios de registro es a las 11:00 am ','6');
insert into politicas (id, titulo, descripcion, producto_id) values ('23','Normas de la casa','CHECK-OUT: La habitación debe ser entregada antes de las 23:00PM horas del día que finalice su estadía.','6');
insert into politicas (id, titulo, descripcion, producto_id) values ('24','Normas de la casa','Respetar las horas de descanso de los demas','6');

insert into politicas (id, titulo, descripcion, producto_id) values ('25','Salud y seguridad','Se aplican las pautas de distanciamiento social y otras normas relacionadas con el coronavirus','6');
insert into politicas (id, titulo, descripcion, producto_id) values ('26','Salud y seguridad','Dispensador de gel','6');
insert into politicas (id, titulo, descripcion, producto_id) values ('27','Salud y seguridad','Detector de humo','6');

insert into politicas (id, titulo, descripcion, producto_id) values ('28','Politica de cancelacion',' Para recibir un reembolso completo, los huéspedes tienen que cancelar en un plazo de 48 horas desde el momento en que reserven. ','6');

## Politicas Departamentos

insert into politicas (id, titulo, descripcion, producto_id) values ('29','Normas de la casa','No dejar basura ni otro tipo de objetos en pasillos, escaleras o áreas comunes','3');
insert into politicas (id, titulo, descripcion, producto_id) values ('30','Normas de la casa',' Los niños no pueden jugar en escaleras, pasillos y/o estacionamiento de la propiedad.','3');
insert into politicas (id, titulo, descripcion, producto_id) values ('31','Normas de la casa','Queda estrictamente prohibido la invasión de espacios de estacionamiento de otros vecinos','3');

insert into politicas (id, titulo, descripcion, producto_id) values ('32','Salud y seguridad','Se aplican las pautas de distanciamiento social y otras normas relacionadas con el coronavirus','3');
insert into politicas (id, titulo, descripcion, producto_id) values ('33','Salud y seguridad','Detector de humo','3');
insert into politicas (id, titulo, descripcion, producto_id) values ('34','Salud y seguridad','Depósito de seguridad','3');

insert into politicas (id, titulo, descripcion, producto_id) values ('35','Politica de cancelacion','Agregá las fechas de tu viaje para obtener los detalles de cancelacion de esta estadia','3');

insert into politicas (id, titulo, descripcion, producto_id) values ('36','Normas de la casa','No se permite mascotas','7');
insert into politicas (id, titulo, descripcion, producto_id) values ('37','Normas de la casa','No poner musica a todo volumen','7');
insert into politicas (id, titulo, descripcion, producto_id) values ('38','Normas de la casa','Respetar las horas de descanso de los demas','7');

insert into politicas (id, titulo, descripcion, producto_id) values ('39','Salud y seguridad','Se aplican las pautas de distanciamiento social y otras normas relacionadas con el coronavirus','7');
insert into politicas (id, titulo, descripcion, producto_id) values ('40','Salud y seguridad','Dispensador de gel','7');
insert into politicas (id, titulo, descripcion, producto_id) values ('41','Salud y seguridad','Detector de humo','7');

insert into politicas (id, titulo, descripcion, producto_id) values ('42','Politica de cancelacion',' Para recibir un reembolso completo, los huéspedes tienen que cancelar en un plazo de 48 horas desde el momento en que reserven. ','7');

## Politicas Bed and Breakfast

insert into politicas (id, titulo, descripcion, producto_id) values ('43','Normas de la casa','CHECK-IN: Recuerda que los horarios de registro son 10:00 am, 10:30 am, 11:00 am y 11:30 am.','4');
insert into politicas (id, titulo, descripcion, producto_id) values ('44','Normas de la casa',' CHECK-OUT: La habitación debe ser entregada antes de las 12:00PM horas del día que finalice su estadía.','4');
insert into politicas (id, titulo, descripcion, producto_id) values ('45','Normas de la casa','No molestar a los vecinos','4');

insert into politicas (id, titulo, descripcion, producto_id) values ('46','Salud y seguridad','Se aplican las pautas de distanciamiento social y otras normas relacionadas con el coronavirus','4');
insert into politicas (id, titulo, descripcion, producto_id) values ('47','Salud y seguridad','Detector de humo','4');
insert into politicas (id, titulo, descripcion, producto_id) values ('48','Salud y seguridad','Depósito de seguridad','4');

insert into politicas (id, titulo, descripcion, producto_id) values ('49','Politica de cancelacion','Agregá las fechas de tu viaje para obtener los detalles de cancelacion de esta estadia','4');

insert into politicas (id, titulo, descripcion, producto_id) values ('50','Normas de la casa','CHECK-IN: Recuerda que los horarios de registro son 14:00PM','8');
insert into politicas (id, titulo, descripcion, producto_id) values ('51','Normas de la casa','No se permite la entrada a las habitaciones a personas no registradas','8');
insert into politicas (id, titulo, descripcion, producto_id) values ('52','Normas de la casa','No se permite la entrada de estupefacientes.','8');

insert into politicas (id, titulo, descripcion, producto_id) values ('53','Salud y seguridad','Se aplican las pautas de distanciamiento social y otras normas relacionadas con el coronavirus','8');
insert into politicas (id, titulo, descripcion, producto_id) values ('54','Salud y seguridad','Tapa Bocas','8');
insert into politicas (id, titulo, descripcion, producto_id) values ('55','Salud y seguridad','Detector de humo','8');

insert into politicas (id, titulo, descripcion, producto_id) values ('56','Politica de cancelacion',' Para recibir un reembolso completo, los huéspedes tienen que cancelar en un plazo de 48 horas desde el momento en que reserven. ','8');
select * from politicas ;


### Insertar producto_caracteristica
insert into producto_caracteristica (producto_id, caracteristica_id) values ('1','1');
insert into producto_caracteristica (producto_id, caracteristica_id) values ('1','2');
insert into producto_caracteristica (producto_id, caracteristica_id) values ('1','3');
insert into producto_caracteristica (producto_id, caracteristica_id) values ('1','4');

insert into producto_caracteristica (producto_id, caracteristica_id) values ('2','1');
insert into producto_caracteristica (producto_id, caracteristica_id) values ('2','5');
insert into producto_caracteristica (producto_id, caracteristica_id) values ('2','2');
insert into producto_caracteristica (producto_id, caracteristica_id) values ('2','3');

insert into producto_caracteristica (producto_id, caracteristica_id) values ('3','1');
insert into producto_caracteristica (producto_id, caracteristica_id) values ('3','4');
insert into producto_caracteristica (producto_id, caracteristica_id) values ('3','6');

insert into producto_caracteristica (producto_id, caracteristica_id) values ('4','1');
insert into producto_caracteristica (producto_id, caracteristica_id) values ('4','5');
insert into producto_caracteristica (producto_id, caracteristica_id) values ('4','4');

insert into producto_caracteristica (producto_id, caracteristica_id) values ('5','7');
insert into producto_caracteristica (producto_id, caracteristica_id) values ('5','1');

insert into producto_caracteristica (producto_id, caracteristica_id) values ('6','7');
insert into producto_caracteristica (producto_id, caracteristica_id) values ('6','1');
insert into producto_caracteristica (producto_id, caracteristica_id) values ('6','3');

insert into producto_caracteristica (producto_id, caracteristica_id) values ('7','7');
insert into producto_caracteristica (producto_id, caracteristica_id) values ('7','1');

insert into producto_caracteristica (producto_id, caracteristica_id) values ('8','10');
insert into producto_caracteristica (producto_id, caracteristica_id) values ('8','1');
insert into producto_caracteristica (producto_id, caracteristica_id) values ('8','5');
select * from producto_caracteristica ;

### Insertar roles
insert into roles (id, role_name) values ('1','ROLE_ADMIN');
insert into roles (id, role_name) values ('2','ROLE_HOST');
insert into roles (id, role_name) values ('3','ROLE_USER');
select * from roles ;

### Insertar usuarios
insert into usuarios (id, user_type, nombre, apellido, email, password, esta_habilitado, role_id, ciudad_id) values ('1', 'Cliente' ,'Andrés','Galván','andres@gmail.com', '$2a$10$UXN.85rztXdCnbCyAdjRSeyjkpF5/eza8RlL5izpC342jyrS1H8Hy', true, '1', '2');
insert into usuarios (id, user_type, nombre, apellido, email, password, esta_habilitado, role_id, ciudad_id) values ('2', 'Cliente' ,'Paula','Acuña','paula@gmail.com', '$2a$10$UXN.85rztXdCnbCyAdjRSeyjkpF5/eza8RlL5izpC342jyrS1H8Hy', true, '1', '1');
insert into usuarios (id, user_type, nombre, apellido, email, password, esta_habilitado, role_id, ciudad_id) values ('3', 'Cliente' ,'Lucas','Bertiche','lucas@gmail.com', '$2a$10$UXN.85rztXdCnbCyAdjRSeyjkpF5/eza8RlL5izpC342jyrS1H8Hy', true, '2', '1');
insert into usuarios (id, user_type, nombre, apellido, email, password, esta_habilitado, role_id, ciudad_id) values ('4', 'Cliente' ,'Judith','Grau','judith@gmail.com', '$2a$10$UXN.85rztXdCnbCyAdjRSeyjkpF5/eza8RlL5izpC342jyrS1H8Hy', true, '2', '1');
insert into usuarios (id, user_type, nombre, apellido, email, password, esta_habilitado, role_id, ciudad_id) values ('5', 'Cliente' ,'Rodrigo','Benitez','rodri@gmail.com', '$2a$10$UXN.85rztXdCnbCyAdjRSeyjkpF5/eza8RlL5izpC342jyrS1H8Hy', true, '3', '1');
insert into usuarios (id, user_type, nombre, apellido, email, password, esta_habilitado, role_id, ciudad_id) values ('6', 'Cliente' ,'Pablo','Álvarez','pablo@gmail.com', '$2a$10$UXN.85rztXdCnbCyAdjRSeyjkpF5/eza8RlL5izpC342jyrS1H8Hy', true, '3', '1');
select * from usuarios ;

### Insertar puntuaciones
insert into puntuaciones (id, puntuacion, comentario, producto_id, usuario_id) values ('1','5',"Espectacular, me encantó.",'1','1');
insert into puntuaciones (id, puntuacion, comentario, producto_id, usuario_id) values ('2','1',"Nefasto.",'1','1');
insert into puntuaciones (id, puntuacion, comentario, producto_id, usuario_id) values ('3','5',"Estuvo bien.",'3','1');
insert into puntuaciones (id, puntuacion, comentario, producto_id, usuario_id) values ('4','5',"La cama es súper cómoda.",'3','1');
insert into puntuaciones (id, puntuacion, comentario, producto_id, usuario_id) values ('5','3',"Lindo lugar, pero la atención no era muy buena.",'1','1');
insert into puntuaciones (id, puntuacion, comentario, producto_id, usuario_id) values ('6','4',"Excelente ubicación.",'7','1');
insert into puntuaciones (id, puntuacion, comentario, producto_id, usuario_id) values ('7','3',"Tenía linda vista pero no me gustó el desayuno.",'7','1');

select * from puntuaciones ;

### Insertar favoritos
insert into favoritos (id, producto_id, usuario_id) values ('1','1','1');
insert into favoritos (id, producto_id, usuario_id) values ('2','2','1');
insert into favoritos (id, producto_id, usuario_id) values ('3','3','1');
insert into favoritos (id, producto_id, usuario_id) values ('4','4','2');
insert into favoritos (id, producto_id, usuario_id) values ('5','5','2');
select * from favoritos ;

### Insertar reservas
insert into reservas (id, hora_reserva, fecha_inicial, fecha_final, producto_id, usuario_id) values ('1', '10:47', '2023-05-10', '2023-05-12', '1', '1');
insert into reservas (id, hora_reserva, fecha_inicial, fecha_final, producto_id, usuario_id) values ('2', '15:30', '2023-04-10', '2023-04-12', '2', '2');
select * from reservas ;

SET SQL_SAFE_UPDATES = 1;
SET FOREIGN_KEY_CHECKS=1;