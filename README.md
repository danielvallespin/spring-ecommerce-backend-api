# Ecommerce API v 1.0

Desarrollado por: Daniel Vallespín Mellado

Este es un proyecto personal escalable que puede seguir creciendo con el tiempo.

API REST para un sistema de e-commerce construida con **Spring Boot**, **Hibernate/JPA**, **MySQL**, **JWT** y **Spring Security**.

Este proyecto ha sido desarrollado con **Java 17.0.2** y **Spring Boot 3.5.10**.

<br>

## Requisitos

 - [Java 17.0.2](https://download.java.net/java/GA/jdk17.0.2/dfd4a8d0985749f896bed50d7138ee7f/8/GPL/openjdk-17.0.2_windows-x64_bin.zip) (Url para windows)
- Docker para poder ejecutar la base de datos (**MySQL 8.1**)
- Servidor de aplicaciones para desplegar la API (por ejemplo **Tomcat**)

<br>

## Instalación

1. Crear el contenedor Docker utilizando el fichero **docker-compose.yml**

    - Ejecutar el siguiente comando en la carpeta `docker`:


2. Descargar el fichero `.war` adjunto al proyecto y desplegarlo en el servidor de aplicaciones.
    - Para Tomcat solo hay que añadirlo

3. Acceder a **Swagger** para poder probar la API.

<br>

## Swagger

Este proyecto está documentado con **Swagger**. Accediendo a la interfaz podrás encontrar todos los endpoints disponibles y la información necesaria para utilizarlos.

URL de acceso:  (En caso de desplegar en tomcat)

Ip del servidor + :Puerto + Nombre .war + /swagger-ui/index.html

Por ejemplo:
127.0.0.1:8080/ecommerce-api/swagger-ui/index.html

    IMPORTANTE!

Los endpoints tienen diferentes posibilidades de acceso segun permisos

- **Sin indicación** → Requiere un usuario autenticado
- **(SIN JWT)** → Accesible sin autenticación
- **(Solo para admins)** → Acceso exclusivo para usuarios con rol administrador

<br>

## Tecnologías utilizadas

- ![Java](https://img.shields.io/badge/Java-17-orange)
- ![Spring Boot](https://img.shields.io/badge/SpringBoot-3-green)
- ![Spring Security](https://img.shields.io/badge/SpringSecurity-security-green)
- ![JWT](https://img.shields.io/badge/JWT-authentication-blue)
- ![Hibernate](https://img.shields.io/badge/Hibernate-JPA-yellow)
- ![MySQL](https://img.shields.io/badge/MySQL-8-blue)
- ![Docker](https://img.shields.io/badge/Docker-enabled-blue)
- ![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-brightgreen)
- ![Maven](https://img.shields.io/badge/Maven-build-red)
- ![Tomcat](https://img.shields.io/badge/Tomcat-server-yellow)

## Diseño relacional de la base de datos

[Modelo](https://drive.google.com/file/d/1yr2U40xmAGL0axQAH_OCmh9ak6j0ERHP/view?usp=sharing)

<br>

## Endpoints principales

> ⚠️ La documentación completa de la API está disponible en Swagger:
>
> En caso de tener algun problema al ejecutar un endpoint consultar en **Swagger**
>
> `http://IP_DEL_SERVIDOR/swagger-ui/index.html`

---

## Autenticación

| Método | Endpoint | Descripción |
|------|------|-------------|
POST | /login | Autenticación de usuario (SIN JWT) |


---

## Usuarios

| Método | Endpoint | Descripción | Permiso |
|------|------|--------------|-------|
GET | /user/my | Obtener info de mi usuario | User |
GET | /user/{userId} | Obtener usuario por id | Admin |
GET | /user/all | Obtener todos los usuarios | Admin |
POST | /user | Registro de usuario avanzado | Admin |
POST | /user/singup | Registro de usuario | SIN JWT |
PATCH | /user/enable/{userId} | Habilitar usuario | Admin |
PATCH | /user/disable/{userId} | Deshabilitar usuario | Admin |
PATCH | /user/change-password | Cambiar la contraseña | User |

---

## Productos

| Método | Endpoint | Descripción | Permiso |
|------|------|-------------|-------|
GET | /product/{productId} | Obtener producto con detalle por id | SIN JWT |
GET | /product/all | Obtener lista de productos páginada | SIN JWT |
POST | /product/add | Crear producto | Admin |
PATCH | /product/{productId} | Modificar producto por ID | Admin |
PATCH | /product/visible/{productId} | Hacer visible un producto por id | Admin |
PATCH | /product/invisible/{productId} | Hacer invisible un producto por id | Admin |

---

## Carrito

| Método | Endpoint | Descripción | Permiso |
|------|------|-------------|-------|
GET | /cart/my | Obtener el detalle de mi carrito | User |
POST | /cart/add-item | Añadir producto al carrito | User |
PUT | /cart/change-quantity | Cambiar la cantidad de un producto del carrito | User |
DELETE | /cart/{productId} | Eliminar producto del carrito | User |
DELETE | /cart/empty | Vaciar carrito | User |

---

## Wishlist

| Método | Endpoint | Descripción | Permiso |
|------|------|-------------|-------|
GET | /wishlist/my/{wishlistId} | Obtener lista de deseos por id | User |
GET | /wishlist/my/all | Obtener todas las lista de deseos del usuario | User |
POST | /wishlist | Crear nueva lista de deseos | User |
POST | /wishlist/add-item | Añadir producto a la lista de deseos | User |
DELETE | /wishlist/delete/{wishlistId} | Eliminar lista de deseos | User |
DELETE | /wishlist/delete-item | Eliminar producto de la lista de deseos | User |

---

## Reviews

| Método | Endpoint | Descripción | Permiso |
|------|------|-------------|-------|
GET | /review/{productId} | Obtener reviews de un producto | SIN JWT |
GET | /review/my/{productId} | Obtener review del usuario por id | User |
GET | /review/my/all | Obtener todas las reviews del usuario | User |
POST | /review/{productId} | Crear review | User |
PATCH | /review/{productId} | Modificar review | User |
DELETE | /review/{productId} | Eliminar review | User |

---

## Métodos de pago

| Método | Endpoint | Descripción | Permiso |
|------|------|-------------|-------|
GET | /payment-method/my/all | Obtener todos los métodos de pago del usuario | User |
GET | /payment-method/my/{paymentId} | Obtener método de pago del usuario por id | User |
POST | /payment-method/add | Añadir método de pago | User |
PACTH | /payment-method/disable/{paymentId} | Deshabilita un método de pago del usuario | User |
PACTH | /payment-method/default/{paymentId} | Marca como principal un método de pago del usuario | User |

---

## Direcciones

| Método | Endpoint | Descripción | Permiso |
|------|------|-------------|-------|
GET | /address/my/all | Obtener todas las direcciones del usuario | User |
GET | /address/my/{addressId} | Obtener dirección del usuario por id | User |
POST | /address/add | Crear dirección | User |
PATCH | /address/{addressId} | Modificar dirección | User |
PATCH | /address/default/{addressId} | Marcar dirección como principal | User |
DELETE | /address/{addressId} | Eliminar dirección | User |

---

## Pedidos

| Método | Endpoint | Descripción | Permiso |
|------|------|---------------|-------|
GET | /order/all | Obtener todos los pedidos | Admin |
GET | /order/{orderId} | Obtener pedidos por id | Admin |
GET | /order/my/all | Obtener todos los pedido del usuario | User |
GET | /order/my/{orderId} | Obtener pedido del usuario por id | User |
POST | /order/create | Crear pedido | User |
