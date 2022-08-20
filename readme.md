# Secury module API

## Descripción

API con la cual se puede registrar usuarios, en la que ellos pueden iniciar sesiones y verificar un token con el cual se verifica si una sesión determinada está activa.

## Requerimientos

* Maven
* JDK 17
* Tener libre el puerto 12000

## Documentación

Hay un diagrama de actividades en el siguiente [archivo pdf](/doc/RegisterEndpointActivityDiagram.pdf). Actualmente describe la interacción entre objetos al consumir el endpoint ```/register```.
//TODO: Agregar los demás endpoints.

## Cómo ejecutarlo

Se puede iniciar corriendo los siguientes comandos.

```
mvn package
java -jar .\target\SecurityApi-1.0-SNAPSHOT.jar
```

Una vez corriendo la aplicación, se puede acceder a Swagger desde la url:

```
http://localhost:12000/swagger-ui.html
```

## Ejemplo

Iniciar la aplicación.
Ingresar a ```http://localhost:12000/swagger-ui.html``` o ingresar Postman.

### /register [POST]

Para consumir endpoint ```http://localhost:'12000/register```, hay que enviar una petición HTTP del tipo POST a dicha URL. Además  hay que agregar datos al body de la solicitud HTTP. Se agregan a continuación datos de prueba.

```json
{
  "name": "Nicolás Larena",
  "email": "nicolarena@hotmail.com",
  "password": "123",
  "phones": [
    {
      "number": "123456",
      "cityCode": "2324",
      "countryCode": "54"
    }
  ]
}
```

Luego de esto, si se puede registar al usuario. Se obtiene la siguiente respuesta.

```json
{
    "data": {
        "id": "402881a682bc15200182bc15381c0001",
        "email": "nic.lar.universidad@gmail.com",
        "created": "2022-08-20T16:28:03.455+00:00",
        "modified": "2022-08-20T16:28:03.455+00:00",
        "lastLogin": null,
        "token": null,
        "phones": [
            {
                "id": "402881a682bc15200182bc1538090000",
                "number": 123456,
                "cityCode": 2324,
                "countryCode": 54
            }
        ],
        "active": true
    },
    "error": null
}
```

Si el usuario ya existe, en cambio, se recibirá un mensaje de error.

```json
{
    "data": null,
    "error": {
        "message": "Ya hay un usuario registrado con el mail nic.lar.universidad@gmail.com"
    }
}
```

#### Validación de email

La dirección de email, es una clave única del usuario. Por lo que no puede haber dos usuarios con el mismo email.

Si se ingresa un email inválido, también se reciben mensajes de error. Si no tiene un formato de email, de acuerdo a la siguiente expresión regular ```[_A-Za-z\d-]+(\.[_A-Za-z\d-]+)*@[A-Za-z\d-]+(\.[A-Za-z\d]+)*(\.[A-Za-z]{2,})$``` se obtiene la siguiente respuesta.

```json
{
    "data": null,
    "error": {
        "message": "El formato del email es inválido"
    }
}
```

Si no se envía un mail, o se envía el campo vacío se recibe el siguiente error:

```json
{
    "data": null,
    "error": {
        "message": "El campo de email está vacío"
    }
}
```

#### Validación de contraseña

La contraseña debe seguir una expresión regular configurable. La cual se modifica a través de la propiedad ```com.cl.evaluation.register.validation.password.regex```. Nota: se puede agregar como parámetro al iniciar la aplicación o desde el archivo ```/resources/application.properties```.
Por defecto se puede agregar cualquier formato de contraseña, salvo una vacía o nula.

Pero si, por ejemplo, se configura esta propiedad con la siguiente expresión regular:

```
com.cl.evaluation.register.validation.password.regex=\\d*
```

Al intentar registar un usuario con la contraseña ```"abc"```, se recibirá la siguiente respuesta con mensaje de error.

```json
{
    "data": null,
    "error": {
        "message": "El formato de la contraseña es inválido"
    }
}
```

### /login [POST]

Una vez registrado un usuario, puede autenticarse. A través de una petición HTTP del tipo POST a la URL ```localhost:'12000/login```. Agregando al body un JSON con los campos ```email``` y ```password```. Se agrega a continuación un body de ejemplo, que corresponde a los datos enviados al primer ejemplo.

```json
{
  "email": "nic.lar.universidad@gmail.com",
  "password": "123"
}
```

Si la contraseña es correcta, se recibirán nuevamente los datos del usuario. Ahora, se obtendrán datos en los campos ```lastLogin``` y ```token```.
La generación del token se genera y verifica con JWT.

```json
{
  "data": {
    "id": "402881a682bc3c6b0182bc457cf40005",
    "email": "nic.lar.universidad@gmail.com",
    "created": "2022-08-20T17:20:46.834+00:00",
    "modified": "2022-08-20T17:20:46.834+00:00",
    "lastLogin": "2022-08-20T17:20:49.116+00:00",
    "token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoibmljLmxhci51bml2ZXJzaWRhZEBnbWFpbC5jb20iLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwiaWF0IjoxNjYxMDE2MDQ5LCJleHAiOjE2NjEwMTY2NDl9.PXnd4Rmq6gOO-t6UIJk-5BjwaFDssQJTW1ZEclTkB1WF94HcCaxVtlygYWtG85DmE4rtrUvAMRbhzq1pOtcODA",
    "phones": [
      {
        "id": "402881a682bc3c6b0182bc457cf30004",
        "number": 123456,
        "cityCode": 2324,
        "countryCode": 54
      }
    ],
    "active": true
  },
  "error": null
}
```

Si la contraseña es incorrecta se recibirá la siguiente respuesta.

```json
{
    "data": null,
    "error": {
        "message": "Contraseña incorrecta"
    }
}
```

Si ocurre que, el usuario no existe, porque fue de dado de baja del sistema, mientras estaba activa su sesión. Entonces, se obtendrá la siguiente respuesta.

```json
{
    "data": null,
    "error": {
        "message": "Usuario no registrado"
    }
}
```

#### /validate-session [POST]

Una vez iniciada sesión, se podrá comprobar la validez del mismo. Para ello, se puede enviar un POST a la URL ```/localhost:'12000/validate-session```. Dentro del body se debe mandar el token como texto plano.
//TODO: mandar el token en el header.

Se agrega a continuación un ejemplo de datos enviados en el body.

```
Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoibmljLmxhci51bml2ZXJzaWRhZEBnbWFpbC5jb20iLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwiaWF0IjoxNjYxMDE2MDQ5LCJleHAiOjE2NjEwMTY2NDl9.PXnd4Rmq6gOO-t6UIJk-5BjwaFDssQJTW1ZEclTkB1WF94HcCaxVtlygYWtG85DmE4rtrUvAMRbhzq1pOtcODA
```

Se obtendrá como respuesta, los datos del usuario que corresponde el token.

```json
{
  "data": {
    "id": "402881a682bc3c6b0182bc457cf40005",
    "email": "nic.lar.universidad@gmail.com",
    "created": "2022-08-20T17:20:46.834+00:00",
    "modified": "2022-08-20T17:20:46.834+00:00",
    "lastLogin": "2022-08-20T17:20:49.116+00:00",
    "token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoibmljLmxhci51bml2ZXJzaWRhZEBnbWFpbC5jb20iLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwiaWF0IjoxNjYxMDE2MDQ5LCJleHAiOjE2NjEwMTY2NDl9.PXnd4Rmq6gOO-t6UIJk-5BjwaFDssQJTW1ZEclTkB1WF94HcCaxVtlygYWtG85DmE4rtrUvAMRbhzq1pOtcODA",
    "phones": [
      {
        "id": "402881a682bc3c6b0182bc457cf30004",
        "number": 123456,
        "cityCode": 2324,
        "countryCode": 54
      }
    ],
    "active": true
  },
  "error": null
}
```

