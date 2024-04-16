package com.svalero.biblioteca.dao;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface LoanDao {

    @SqlUpdate("INSERT INTO Loans (bookId, userId, loanDate, expectedReturnDate) VALUES (:bookId, :userId, :loanDate, :expectedReturnDate)")
    void addLoan(@Bind("bookId") int bookId, @Bind("userId") int userId, @Bind("loanDate") java.sql.Date loanDate, @Bind("expectedReturnDate") java.sql.Date expectedReturnDate);

    // Más métodos según sea necesario
}