package com.aluracursos.challengeLiteratura.repository;

import com.aluracursos.challengeLiteratura.model.Autores;
import com.aluracursos.challengeLiteratura.model.Libros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibrosRepository extends JpaRepository<Libros, Long> {
// Encuentra todos los Libros Registrados
@Query(value="SELECT libro.*, autor.* FROM libro LEFT JOIN autor ON libro.id = autor.libros_id", nativeQuery = true)
List<Libros> librosRegistrados();

//Encuentra Libro por porcion de titulo
@Query(value="SELECT l FROM Libros l WHERE l.titulo ILIKE %:pedazoTitulo%")
Optional<Libros> encuentraLibroEnDB(String pedazoTitulo);

//Encuentra los autores registrados
@Query(value="SELECT DISTINCT a FROM Autores a")
List<Autores> encuentraAutoresEnDB();

//Encuentra los autores registrados
@Query(value="SELECT DISTINCT a FROM Autores a WHERE a.fechaNacimiento<=:fecha AND a.fechaMuerte >= :fecha" )
List<Autores> encuentraAutoresVivosEnDB(Integer fecha);

// Encuentra todos los Libros por Idioma
@Query(value="SELECT l FROM Libros l WHERE ARRAY_CONTAINS(l.idiomas, LOWER(:idioma))")
List<Libros> librosXIdioma(String idioma);
}



