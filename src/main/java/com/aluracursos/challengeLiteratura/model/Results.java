package com.aluracursos.challengeLiteratura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public record Results(
        @JsonAlias("count") Integer totaldeRecords,
        @JsonAlias("results") List<DatosLibros> resultados
        //@JsonAlias("languages") List<Lenguajes> lenguajes,
        //@JsonAlias("download_count") Integer numeroDeDescargas
) {
}
