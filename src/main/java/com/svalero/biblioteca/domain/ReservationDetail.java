package com.svalero.biblioteca.domain;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
public class ReservationDetail {
    private int reservationId;
    private int bookId;
    private int userId;
    private Date reservationDate;
    private String status;
    private String bookTitle; // Añadir el título del libro como un campo en la clase

    // Constructor completo
    public ReservationDetail(int reservationId, int bookId, int userId, Date reservationDate, String status, String bookTitle) {
        this.reservationId = reservationId;
        this.bookId = bookId;
        this.userId = userId;
        this.reservationDate = reservationDate;
        this.status = status;
        this.bookTitle = bookTitle; // Asegurarse de asignar el título del libro
    }

    // Constructor vacío si es necesario para otros usos
    public ReservationDetail() {
    }

    // No necesitas otros getters y setters si usas Lombok @Getter y @Setter,
    // a menos que necesites lógica personalizada en esos métodos.
}
