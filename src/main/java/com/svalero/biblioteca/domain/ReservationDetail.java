package com.svalero.biblioteca.domain;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
public class ReservationDetail {

    // Getters y setters
    private int reservationId;
    private int bookId;
    private int userId;
    private Date reservationDate;
    private String status;
    private String bookTitle; // Título del libro

    // Constructor
    public ReservationDetail(int reservationId, int bookId, int userId, Date reservationDate, String status, String bookTitle) {
        this.status = this.status;
        this.bookTitle = this.bookTitle;
    }
}