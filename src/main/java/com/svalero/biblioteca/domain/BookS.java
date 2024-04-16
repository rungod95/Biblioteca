package com.svalero.biblioteca.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookS {
    private int id;
    private String title;
    private String author;
    private String isbn;
    private String edition;
    private int publicationYear; // Asumiendo que el año de publicación se manejará como un entero
    private String category;
    private int quantity; // Asumiendo cantidad como el número de copias disponibles
    private String Photo;
}

