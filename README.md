#  CRM Spring Boot + PostgreSQL + React

Una aplicación de gestión de relaciones con clientes (CRM) construida con Spring Boot, PostgreSQL y un frontend desarrollado en React. Permite crear y listar clientes fácilmente.

## Contenidos

- [Tecnologías](#tecnologías)
- [Instalación](#instalación)
- [Configuración](#configuración)
- [Uso](#uso)
- [Automatización con Makefile](#automatización-con-makefile)
- [API Endpoints](#api-endpoints)

##  Tecnologías

- Java 21
- Spring Boot 3
- PostgreSQL
- Spring Data JPA
- Swagger OpenAPI
- Lombok
- React con Vite

##  Instalación

1. Clona el repositorio:
   ```bash
   git clone git@github.com:pedrose-99/Java-Solutions-Market.git
   ```
2. Instalar Dbeaver como herramienta de administración de bases de datos.

3. Asegurate de tener instalado PostgreSQL.

4. Crea una base de datos llamada CRM

5. Instalar con el Makefile las dependencias necesarias


##  Configuración

```markdown
##  Configuración

Archivo `src/main/resources/application.properties`:

#PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/CRM
spring.datasource.username=postgres
spring.datasource.password=$TUCONTRASEÑADEPOSTGRES
spring.datasource.driver-class-name=org.postgresql.Driver

#JPA
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
```

## Uso

Para ejecutar el backend podemos usar nuestro IDE o escribir en terminal:

```bash
    ./mvnw spring-boot:run
```
Para ejecutar el frontend dentro de su carpeta al escribir en terminal:

```bash
    npm install
    npm run dev
```

##  Automatización con Makefile

Este proyecto incluye un `Makefile` que facilita el arranque y gestión del backend y frontend.

### Comandos disponibles:

- `make start`: Inicia el backend (Spring Boot) y frontend (React) en dos terminales separadas.
- `make start-windows`: Inicia el backend (Spring Boot) y frontend (React) en dos terminales separadas en Windows.
- `make stop`: Detiene ambos procesos. Intenta matar React si está corriendo en el puerto 5173.
- `make clean`: Elimina archivos de log temporales si los hay.
- `make kill-frontend`: Mata cualquier proceso que esté utilizando el puerto 5173 (usualmente React).
- `make check-deps`: Verifica si tienes las dependencias necesarias según tu sistema operativo (Linux o Windows).
- `make install-deps-linux`: Instala las dependencias del frontend (React) si estás en Linux.
- `make install-deps-windows`: Instala las dependencias del frontend (React) si estás en Windows.

>  Requiere `make` y `gnome-terminal` en Linux para que pueda abrir terminales nuevas automáticamente.

> Requiere`make` y `una terminal bash` en windows para que se ejecute el programa.
---
## API Endpoints
Podremos levantar el servidor y acceder a:
- Frontend: `http://localhost:5173/`
- Documentación Swagger: `http://localhost:8080/api/docs`
