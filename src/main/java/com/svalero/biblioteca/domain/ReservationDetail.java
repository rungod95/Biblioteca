package com.svalero.biblioteca.domain;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
public class ReservationDetail {
    public int reservationId;
    public int bookId;
    public int userId;
    public Date reservationDate;
    public String status;
    public String bookTitle; // Título del libro

    // Constructor
    public ReservationDetail(int reservationId, int bookId, int userId, Date reservationDate, String status, String bookTitle) {
        this.reservationId = reservationId;
        this.bookId = bookId;
        this.userId = userId;
        this.reservationDate = reservationDate;
        this.status = status;
        this.bookTitle = bookTitle;
    }
}
