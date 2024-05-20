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
    private int publicationYear; //
    private String category;
    private int quantity; //
    private String Photo;
    private boolean isActive;

    public BookS(int id, String title, String author, String isbn, String edition, int publicationYear, String category, int quantity, String photo) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.edition = edition;
        this.publicationYear = publicationYear;
        this.category = category;
        this.quantity = quantity;
        this.Photo = photo;
        this.isActive = true; //
    }
}


