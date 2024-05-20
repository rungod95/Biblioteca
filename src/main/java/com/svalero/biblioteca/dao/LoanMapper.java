package com.svalero.biblioteca.dao;

import com.svalero.biblioteca.domain.Loan;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class LoanMapper implements RowMapper<Loan> {
    @Override
    public Loan map(ResultSet rs, StatementContext ctx) throws SQLException {
        int loanId = rs.getInt("loanId");
        int bookId = rs.getInt("bookId");
        int userId = rs.getInt("userId");
        Date loanDate = rs.getDate("loanDate");
        Date expectedReturnDate = rs.getDate("expectedReturnDate");
        Date actualReturnDate = rs.getDate("actualReturnDate");
        String bookTitle = rs.getString("bookTitle");

        if (rs.wasNull()) {
            actualReturnDate = null;
        }

        return new Loan(loanId, bookId, userId, loanDate, expectedReturnDate, actualReturnDate, bookTitle);
    }
}
