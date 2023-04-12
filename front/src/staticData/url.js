// /------------- IP EC2 BACKEND ----------------/
// export const IP_EC2 = "http://localhost"
export const IP_EC2 = "http://3.145.84.116"

/* productos */
export const PRODUCTOS_RANDOM = IP_EC2+":8090/productos/random"
export const CATEGORY = IP_EC2+":8090/categorias"
export const PRODUCTOS_ID = IP_EC2+":8090/productos/"
export const BUSCARDOR_CIUDAD = IP_EC2+":8090/ciudades"
export const BUSCARDOR_PRODUCTOS_POR_CIUDAD = IP_EC2+":8090/productos/ciudad/"
export const BUSCARDOR_PRODUCTOS_POR_CATEGORIA = IP_EC2+":8090/productos/categoria/"
export const PRODUCTOS = IP_EC2+":8090/productos"
export const CARACTERISTICAS =IP_EC2+":8090/caracteristicas"

export const CREAR_PRODUCTO = IP_EC2 + ":8090/productos"

/*buscador fecha */
export const BUSCARDOR_PRODUCTOS_POR_FECHA = IP_EC2+":8090/productos/fecha/"

/* puntuacion */

export const ENVIAR_PUNTUACION = IP_EC2+":8090/puntuaciones"

/* reserva*/

export const ENVIAR_RESERVA = IP_EC2+":8090/reservas"
export const BORRAR_RESERVA = IP_EC2+":8090/reservas/"

/* usuario */

export const AGREGAR_USUARIO = IP_EC2 + ":8090/usuarios"
export const AGREGAR_USUARIO_LOGIN = IP_EC2 + ":8090/login"
export const BUSCAR_USUARIO_POR_ID = IP_EC2+":8090/usuarios/buscarId/"
export const BUSCAR_USUARIO_POR_EMAIL = IP_EC2+":8090/usuarios/buscarEmail/"
export const BUSCAR_RESERVAS_DEL_USUARIO = IP_EC2+":8090/reservas/usuario/"

/* favoritos */
export const AGREGAR_A_FAVORITOS = IP_EC2 + ":8090/favoritos"

/* tokenEmail*/

export const TOKEN_EMAIL_CONFIRMATED = IP_EC2 +":8090/usuarios/confirmar-cuenta?token="

/* users */

export const USUARIOS = IP_EC2 + ":8090/usuarios"