# msproductos

Microservicio desarrollado con Spring Boot para la gestión de productos.

## 🚀 Tecnologías

- Java 21
- Spring Boot 3.4.4
- Spring Data JPA
- PostgreSQL
- Lombok

## ⚙️ Configuración

Archivo `application.properties`:

```properties
spring.application.name=msproductos
spring.datasource.url=jdbc:postgresql://localhost:5434/proveedores
spring.datasource.username=dbproveedores
spring.datasource.password=dbproveedores
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
server.port=8081
```

## 📦 Endpoints

| Método | Ruta                   | Descripción              |
|--------|------------------------|--------------------------|
| GET    | /api/v1/productos?page=0&size=100      | Obtener lista paginada   |
| GET    | /api/v1/productos/{id} | Obtener por ID           |
| POST   | /api/v1/productos      | Crear producto           |

## ▶️ Ejecutar

```bash
mvn clean install
mvn spring-boot:run
```

Puerto por defecto: `8081`