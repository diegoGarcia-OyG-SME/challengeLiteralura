package com.aluracursos.challengeLiteratura.model;

import jakarta.persistence.*;

import java.util.stream.Collectors;

@Entity
@Table(name="autor")
public class Autores {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long Id;
        //@Column(unique = true)
        private String nombre;
        private Integer fechaNacimiento;
        private Integer fechaMuerte;
        @ManyToOne
        private Libros libros;

        public Autores(){}

        public Autores(DatosAutor datosAutores){
            this.nombre = datosAutores.nombre();
            this.fechaNacimiento = datosAutores.fechaNacimiento();
            this.fechaMuerte = datosAutores.fechaMuerte();
        }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Integer fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getFechaMuerte() {
        return fechaMuerte;
    }

    public void setFechaMuerte(Integer fechaMuerte) {
        this.fechaMuerte = fechaMuerte;
    }

    public Libros getLibros() {
        return libros;
    }

    public void setLibros(Libros libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {

        return
                "\nAutor(es): " + nombre.toString().replaceAll("\\[|\\]", "") +
                "\nFechas de Nacimiento: " + fechaNacimiento +
                "\nFecha de fallecimiento: " + fechaMuerte +
                "\nLibros: " + libros.getTitulo().replaceAll("\\[","")
                        .replaceAll("\\]"," / ");
    }
}
