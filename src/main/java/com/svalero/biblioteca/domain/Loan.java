package com.svalero.biblioteca.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
    private int loanId;
    private int bookId;
    private int userId;
    private Date loanDate;
    private Date expectedReturnDate;
    private Date actualReturnDate;

    // Constructor, getters y setters
}