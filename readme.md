# Secury module API

## Descripción

API con la cual se puede registrar usuarios, en la que ellos pueden iniciar sesiones y verificar un token con el cual se verifica si una sesión determina está activa.

## Requerimientos

* Maven 18
* JDK 18

## Cómo ejecutarlo

Se puede iniciar corriendo los siguientes comandos.

```
mvn package
java -jar .\target\SecurityApi-1.0-SNAPSHOT.jar
```

Una vez corriendo la aplicación, se puede acceder a Swagger desde la url:

```
http://localhost:8080/swagger-ui.html
```