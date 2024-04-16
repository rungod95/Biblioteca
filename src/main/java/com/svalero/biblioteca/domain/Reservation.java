package com.svalero.biblioteca.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Reservation {
    private int reservationId;
    private int bookId;
    private int userId;
    private Date reservationDate;
    private String status;

}
