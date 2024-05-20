package com.svalero.biblioteca.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookRequest {
    private int requestId;
    private int userId;
    private String bookTitle;
    private String author;
    private String isbn;
    private String urgencyLevel;
    private Date requestDate;
    private String firstName;
    private String lastName;
}
