package com.svalero.biblioteca.dao;

import com.svalero.biblioteca.domain.Loan;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.sql.Date;
import java.util.List;

public interface LoanDao {

    @SqlUpdate("INSERT INTO loans (bookId, userId, loanDate, expectedReturnDate) VALUES (:bookId, :userId, :loanDate, :expectedReturnDate)")
    void addLoan(@Bind("bookId") int bookId, @Bind("userId") int userId, @Bind("loanDate") Date loanDate, @Bind("expectedReturnDate") Date expectedReturnDate);

    @SqlUpdate("UPDATE loans SET actualReturnDate = :actualReturnDate WHERE loanId = :loanId AND actualReturnDate IS NULL")
    boolean updateReturnDate(@Bind("loanId") int loanId, @Bind("actualReturnDate") Date actualReturnDate);


    @SqlQuery("SELECT l.*, b.title as bookTitle FROM loans l JOIN books b ON l.bookId = b.bookId WHERE l.userId = :userId")
    @RegisterRowMapper(LoanMapper.class)
    List<Loan> findLoansByUserId(@Bind("userId") int userId);

}

