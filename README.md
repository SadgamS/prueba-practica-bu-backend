# Backend Prueba Practica

## Tecnologias usadas

- Java 21
- Spring Boot 3.3.3, Framework de Java para el desarrollo de aplicaciones web
- Maven, Herramienta de gestión de proyectos de software
- PostgreSQL, Base de datos relacional
- Spring Data JPA, Framework de persistencia de Java
- Spring Security, Framework de seguridad de Java
- JWT, para la autenticación de usuarios
- Flyway, para la migración de base de datos

## Requisitos

- Java 21
- Maven
- PostgreSQL

## Instalación

1. Configurar las variables de entorno con archivo .env en la raíz del proyecto, usar el archivo .env.example como referencia

```
cp .env.example .env
```

El archivo .env debe tener la siguiente estructura

| Variable                   | Descripción                    | Valor Ejemplo                                      |
| -------------------------- | ------------------------------ | -------------------------------------------------- |
| SPRING_DATASOURSE_URL      | URL de la base de datos        | jdbc:postgresql://localhost:5432/backend_prueba    |
| SPRING_DATASOURSE_USERNAME | Usuario de la base de datos    | postgres                                           |
| SPRING_DATASOURSE_PASSWORD | Contraseña de la base de datos | postgres                                           |
| JWT_SECRET_KEY             | Secret para JWT                | F6EADCCD2F77598943B6FB8FC74E8ESA3412MN8943BF36AESD |
| JWT_EXPIRATION_TIME        | Tiempo de expiración del token | 86400000                                           |

2. Crear la base de datos en PostgreSQL

```
CREATE DATABASE backend_prueba;
```
Nota. No es necesario crear las tablas y esquemas, Flyway se encargará de crearlas, puede revisar las migraciones en src/main/resources/db/migration

3. Instalar las dependencias del proyecto

```
mvn clean install
```

4. Ejecutar el proyecto

```
mvn spring-boot:run
```

## Autor

- Brayan Dennis Aguilar Aparicio


