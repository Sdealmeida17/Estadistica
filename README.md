# 📋 Tarea Semanal: Proyecto E2E - Entrega 2

## Descripción 💡

En esta entrega, vamos a llevar la implementación del primer laboratorio a un nivel más eficiente, aplicando los
conceptos que hemos aprendido durante la semana: **DTO** y **Manejo de Excepciones**.

En esta fase del proyecto, se te proporciona una solución inicial al laboratorio anterior. Tu tarea consiste en mejorar
esta solución implementando DTO y manejando excepciones adecuadamente. Además, debes asegurarte de que los códigos de
estado HTTP de error y éxito se manejen correctamente. A continuación, se detallan los puntos que debes tener en cuenta
en tu implementación:

## Manejo de Excepciones

Debes manejar las distintas situaciones problemáticas que puedan surgir durante el procesamiento de las solicitudes. Los
endpoints serán probados bajo ciertas condiciones de fallo, por lo que no será suficiente hardcodear los `HttpStatus`.

### Estados de Error a Considerar

- **404:** Para cualquier recurso no encontrado en cualquier tipo de solicitud.
- **409:** Cuando la solicitud entra en conflicto con el estado actual del servidor. Por ejemplo, intentar crear un
  recurso con un atributo único que ya pertenece a otro registro en la base de datos.
- **400** y **405:** Serán manejados automáticamente por Spring Boot.

### Estados de Éxito a Considerar

- **200:** Para toda solicitud procesada y devuelta con éxito, que no requiera información adicional más allá de la
  solicitada.
- **201:** Para toda solicitud que cree recursos. Además de la información solicitada, debe devolver el URI al cual se
  debe hacer un GET para acceder al nuevo recurso. Por ejemplo, si se crea un recurso "carro" con el ID 1, se debe
  devolver un código 201 junto con el URI `carro/1`, asumiendo que el endpoint GET está definido como `carro/{id}`. Este
  uri se devuelve en los headers, ver [este link](https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Location)
  para más información.
- **204:** Para los endpoints de tipo `DELETE`.

## DTO

Dentro de la mayoría de las entidades, encontrarás los DTO que debes utilizar. A continuación, se describe su uso:

- `DriverDto`:
    - Tipo de `response body` para los endpoints `GET /driver/{id}` y `PATCH /driver/{id}/car`
    - Tipo de `request body` para el endpoint `PUT /driver/{id}`


- `DriverRequestDto`:
    - Tipo de `request body` para el endpoint `POST /driver`


- `PassengerResponseDto`:
    - Tipo de `response body` para el endpoint `GET /passenger/{id}`


- `PassengerRequestDto`:
    - Tipo de `request body` para el endpoint `POST /passenger`


- `PassengerLocationDto`:
    - Tipo de `request body` para el endpoint `PATCH /passenger/{id}`
    - Tipo de los elementos de la lista retornada en el endpoint `GET /passenger/{id}/places`


- `ReviewRequestDto`:
    - Tipo de `request body` para el endpoint `POST /review`


- `DriverReviewResponseDto` ([Proyección](https://www.baeldung.com/spring-data-jpa-projections)):
    - Tipo de elementos de la página retornada por el endpoint `GET /review/{driverId}`


- `RideRequestDto`:
    - Tipo de `request body` para el endpoint `POST /ride`


- `RideResponseDto`:
    - Tipo de elementos de la página retornada por el endpoint `GET /ride/{passengerId}`

Los DTO `CoordinateDto` y `VehicleDto` son utilizados dentro de algunos de los DTO mencionados previamente.

### Validaciones

| DTO                  | Constraint                                                                                                                                                                                                                                                     |
|----------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| DriverDto            | - `category`: no nulo.<br>- `trips` y `avgRating`: no nulos y mayores o iguales a 0.<br>- `firstName` y `lastName`: no nulos y longitud entre 2 y 50 caracteres. <br>-`vehicle` (`VehicleBasicDto`): no nulo.                                                  |
| DriverRequestDto     | - Todos los atributos: no nulos.<br>- `firstName` y `lastName`: longitud entre 2 y 50 caracteres.<br>- `email`: debe ser un email válido.<br>- `phoneNummber`: longitud entre 9 y 15 caracteres.<br>- `password`: longitud entre 6 y 50 caracteres.            |
| CoordinateDto        | - `latitude` y `longitude`: no nulos, y entre -90 y 90 para `latitude`, y -180 y 180 para `longitude`.                                                                                                                                                         |
| PassengerResponseDto | - `firstName` y `lastName`: no nulos y longitud entre 2 y 50 caracteres.<br>- `phoneNummber`: no nulo, y longitud entre 9 y 15 caracteres.<br>- `avgRating`: no nulo y entre 0.0 y 5.0.                                                                        |
| PassengerRequestDto  | - `firstName` y `lastName`: no nulos y longitud entre 2 y 50 caracteres.<br>- `phoneNummber`: no nulo, y longitud entre 9 y 15 caracteres.<br>- `password`: longitud entre 6 y 50 caracteres. <br>- `email`: deben ser un email válido. <br>- `role`: no nulo. |
| PassengerLocationDto | - `description`: no nulo y longitud entre 2 y 255 caracteres. <br>- `coordiante` (`CoordianteDto`): no nulo.                                                                                                                                                   |
| ReviewRequestDto     | - Todos los atributos: no nulos.<br>- `rating`: entre 0 y 5.                                                                                                                                                                                                   |
| RideRequestDto       | - Todos los atributos: no nulos.<br>- `destinationName` y `originName`: longitud entre 2 y 255 caracteres.<br>- `price`: mayor a 0.                                                                                                                            |
| RideResponseDto      | - Todos los atributos: no nulos.<br>- `destinationName` y `originName`: longitud entre 2 y 255 caracteres.<br>- `price`: mayor a 0.                                                                                                                            |
| VehicleBasicDto      | - `brand`, `model`, `licensePlate` y `color`: no nulos y longitud entre 2 y 50 caracteres.<br>- `fabricationYear`: no nulo y entre 1900 y el año actual.<br>                                                                                                   |

## Consideraciones Importantes

Los únicos cambios que deberán hacer en los endpoints respecto al laboratorio anterior son:

- Añadir el endpoint `POST /passenger`, para crear un pasajero usando el DTO `PassengerRequestDto`.

- Añadir el endpoint `GET /review/{driverId}`, para obtener todas las `review` donde el atributo `target` haga
  referencia al `driverId`.
  Este endpoint debe devolver un objeto `Page` de `DriverReviewResponseDto`.
