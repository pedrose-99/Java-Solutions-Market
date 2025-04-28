#  CRM Spring Boot + PostgreSQL

Una aplicación de gestión de relaciones con clientes (CRM) construida con Spring Boot, PostgreSQL y expuesta como API REST. Permite crear y listar clientes fácilmente.

## Contenidos

- [Tecnologías](#tecnologías)
- [Instalación](#instalación)
- [Configuración](#configuración)
- [Uso](#uso)
- [API Endpoints](#api-endpoints)




##  Tecnologías

- Java 21
- Spring Boot 3
- PostgreSQL
- Spring Data JPA
- Swagger OpenAPI
- Lombok

##  Instalación

1. Clona el repositorio:
   ```bash
   git clone git@github.com:pedrose-99/Java-Solutions-Market.git
   ```
2. Instalar Dbeaver como herramienta de administración de bases de datos.

3. Asegurate de tener instalado PostgreSQL.

4. Crea una base de datos llamada CRM


##  Configuración del entorno

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

Para ejecutar el proyecto podemos usar nuestro IDE o ejecutar en terminal:

```bash
    ./mvnw spring-boot:run
```
---
## API Endpoints
Podremos levantar el servidor y acceder a:
- API REST: `http://localhost:8080/api/clients`
- Documentación Swagger: `http://localhost:8080/swagger-ui.html`
