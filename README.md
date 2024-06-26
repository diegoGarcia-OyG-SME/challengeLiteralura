Biblioteca Digital - Proyecto Alura

Este proyecto es una aplicación de consola que permite a los usuarios interactuar con una biblioteca digital. La aplicación se conecta a la API de Gutendex para obtener información sobre los libros disponibles y los almacena en una base de datos. Los usuarios pueden realizar diversas operaciones, como buscar libros por título, listar los libros registrados, listar los autores registrados, listar los autores vivos en un determinado año y buscar libros por idioma.
Características

Búsqueda de libros por título: Los usuarios pueden buscar libros por su título. Si el libro no está registrado en la base de datos, la aplicación lo buscará en la API de Gutendex, lo guardará en la base de datos y mostrará la información del libro.
Listado de libros registrados: Los usuarios pueden ver la lista de todos los libros registrados en la base de datos.
Listado de autores registrados: Los usuarios pueden ver la lista de todos los autores registrados en la base de datos.
Listado de autores vivos en un determinado año: Los usuarios pueden buscar la lista de autores que estaban vivos en un determinado año.
Búsqueda de libros por idioma: Los usuarios pueden buscar libros por su idioma (español, inglés, francés o portugués).

Tecnologías utilizadas
Java
Spring Boot
JPA (Java Persistence API)
PostgreSQL
Gutendex API

Estructura del proyecto
El proyecto está organizado de la siguiente manera:
com.aluracursos.challengeLiteratura.principal: Contiene la clase Principal, que es el punto de entrada de la aplicación y maneja la lógica de la interfaz de usuario.
com.aluracursos.challengeLiteratura.model: Contiene las clases de modelo, como Libros y Autores.
com.aluracursos.challengeLiteratura.repository: Contiene la interfaz LibrosRepository, que define los métodos para interactuar con la base de datos.
com.aluracursos.challengeLiteratura.service: Contiene las clases de servicio, como ConsumoAPI y ConvierteDatos, que se encargan de interactuar con la API de Gutendex y convertir los datos.

Cómo ejecutar el proyecto
Clona el repositorio en tu máquina local.
Asegúrate de tener Java y PostgreSQL instalados en tu sistema.
Crea una base de datos PostgreSQL con el nombre de tu elección.
Actualiza la información de conexión a la base de datos en el archivo application.properties.
Ejecuta la aplicación utilizando tu IDE o desde la línea de comandos con el siguiente comando: ./gradlew bootRun.

Uso de la aplicación
Una vez que la aplicación esté en ejecución, podrás interactuar con ella a través de la consola. El menú principal te permitirá seleccionar las diferentes opciones disponibles.

Contribución
Si deseas contribuir a este proyecto, puedes seguir los siguientes pasos:
Haz un fork del repositorio.
Crea una nueva rama con tu contribución.
Realiza los cambios necesarios y asegúrate de que la aplicación sigue funcionando correctamente.
Envía un pull request con tus cambios.

¡Gracias por tu interés en este proyecto!
