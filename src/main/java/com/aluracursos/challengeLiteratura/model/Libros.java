package com.aluracursos.challengeLiteratura.model;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Entity
@Table(name="libro")
public class Libros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;
    private Integer numeroDeDescargas;
    private List<String> idiomas;
    @OneToMany(mappedBy = "libros", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autores> autores;

    public Libros(){}

    public Libros(DatosLibros datosLibros){
        this.titulo = datosLibros.titulo();
        this.numeroDeDescargas = datosLibros.numeroDeDescargas();
        this.idiomas = datosLibros.idiomas();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Integer numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public List<Autores> getAutores() {
        return autores;
    }

    public void setAutores(List<Autores> autores) {
        autores.forEach(a-> a.setLibros(this));
        this.autores = autores;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    @Override
    public String toString() {
        String autorClean = autores.stream()
                .map(n-> n.getNombre())
                .collect(Collectors.joining(" / "));


        return "\n----- LIBRO ----- " +
                "\nTítulo: " + titulo +
                "\nAutor(es): " + autorClean.replaceAll("\\[|\\]", "")+
                "\nIdioma(s): " + idiomas.toString().replaceAll("\\[|\\]", "") +
                "\nNúmero de descargas: " + numeroDeDescargas;
    }
}
