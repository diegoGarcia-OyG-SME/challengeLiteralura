package com.aluracursos.challengeLiteratura.principal;

import com.aluracursos.challengeLiteratura.model.*;
import com.aluracursos.challengeLiteratura.repository.LibrosRepository;
import com.aluracursos.challengeLiteratura.service.ConsumoAPI;
import com.aluracursos.challengeLiteratura.service.ConvierteDatos;

import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private static final String API_KEY = "";
    private static final String BASE_URL = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibrosRepository repositorio;
    private String pedazoTitulo;
    private List<Libros> libros;
    private Optional<Libros> libroBuscado;
    public Principal(LibrosRepository repository) {
        this.repositorio = repository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    -----------------------------------
                    Elija una opción a través de su número:
                    1 - Buscar libro por título
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en determinado año
                    5 - Listar libros por idioma
                    0 - Salir
                    """;
            System.out.println(menu);

            try {
                opcion = Integer.parseInt(teclado.nextLine());

            switch (opcion) {
                case 1:
                    buscarLibroWeb();
                    break;
                case 2:
                    buscarLibrosRegistrados();
                    break;
                case 3:
                    buscarAutoresRegistrados();
                    break;
                case 4:
                    buscarAutoresVivos();
                    break;
                case 5:
                    buscarLibrosXIdioma();
                    break;

                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
                } catch (NumberFormatException e) {
                System.out.println("Opción inválida");
                }
        }

    }

    private void buscarLibroWeb() {

        System.out.println("Escribe el nombre del libro que quieres buscar");
        pedazoTitulo = teclado.nextLine();
        libroBuscado = repositorio.encuentraLibroEnDB(pedazoTitulo.toString());

        // Interroga la DB en busca del registro
        if(libroBuscado.isPresent()){
            System.out.println("El libro ya se encuentra registrado"); // Alerta que existe libro y regresa al inicio
        }else {
            // Si no esta registrado procede a consumir API y registrar en la DB
            var json = consumoApi.obtenerDatos(BASE_URL + "?search=" + pedazoTitulo.replace(" ", "+"));
            var libroEncontrado = conversor.obtenerDatos(json, Results.class);
            Optional<DatosLibros> libroBuscado = libroEncontrado.resultados().stream()
                    .filter(l -> l.titulo().toString().toUpperCase().contains(pedazoTitulo.toUpperCase()))
                    .sorted(Comparator.comparing(DatosLibros::numeroDeDescargas).reversed())
                    .findFirst();
            if (libroBuscado.isPresent()) {
                DatosLibros datosLib = libroBuscado.get();
                Libros libro = new Libros(datosLib);
                repositorio.save(libro); //Salva el Libro en la Database


                List<Autores> datosAutor = (List<Autores>) libroBuscado.stream()
                        .flatMap(l -> l.autores().stream()
                                .map(a -> new Autores(a)))
                        .collect(Collectors.toList());

                libro.setAutores(datosAutor);
                repositorio.save(libro); //Salva los Autores en el database


            } else {
                System.out.println("Libro no encontrado");

            }
        }
    }

    private void buscarLibrosRegistrados(){
        libros = repositorio.findAll();
        if (libros.isEmpty()){
            System.out.println("La base de datos esta vacía");
        }else {
            libros.stream()
                    .forEachOrdered(System.out::println);
        }
    }

    private void buscarAutoresRegistrados(){
        List<Autores> autores = repositorio.encuentraAutoresEnDB();
        if(autores.isEmpty()){
            System.out.println("La base de datos esta vacía");
        }else {
            autores.stream()
                    .forEachOrdered(System.out::println);
        }
    }

    private void buscarAutoresVivos(){
        System.out.println("Ingrese el año vivo de Autor(es) que desea buscar ");
        Integer fecha = -1;
        while (fecha != 0) {
            try {
                fecha = Integer.parseInt(teclado.nextLine());
                List<Autores> autores = repositorio.encuentraAutoresVivosEnDB(fecha);

                if (autores.isEmpty()) {
                    System.out.println("No existen coincidencias registradas intente otro año ó ingrese 0 para regresar al menú principal");

                } else {
                    autores.stream()
                            .forEachOrdered(System.out::println);
                    System.out.println("\nPuede ingresar otro año para realizar otra busqueda " +
                            "\nó ingrese 0 para regresar al menú principal");
                    fecha = -1;
                }
            }catch(NumberFormatException e){
                System.out.println("Entrada invalida, favor de ingresar un número entero");

            }
        }
        System.out.println("Regresando al menú principal...\n");
    }

    private void buscarLibrosXIdioma(){
        System.out.println("Ingrese el idioma para buscar los libros:" +
                "\nes - español" +
                "\nen - inglés" +
                "\nfr - francés" +
                "\npt - portugués" +
                "\n0 - para regresar al menú principal");
        Integer opcion = -1;
        while (opcion != 0) {
            try {
                String idiomas = teclado.nextLine();

                if(idiomas.matches("0")) {
                    opcion = Integer.parseInt(idiomas);

                }else {
                    List<Libros> libros = repositorio.librosXIdioma(idiomas);

                    if (libros.isEmpty()) {
                        System.out.println("No existen coincidencias registradas ó ingresó un idioma invalido" +
                                "\nintente otro idioma ó ingrese 0 para regresar al menú principal");

                    } else {
                        libros.stream()
                                .forEachOrdered(System.out::println);
                        System.out.println("\nPuede ingresar otro idioma para realizar otra busqueda " +
                                "\nó ingrese 0 para regresar al menú principal");
                        opcion = -1;
                    }
                }
            }catch(NumberFormatException e){
                System.out.println("Entrada invalida, por favor vuelva a intentar");

            }
        }
        System.out.println("Regresando al menú principal...\n");
    }

}