package com.svalero.biblioteca.dao;

import com.svalero.biblioteca.domain.Loan;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoanMapper implements RowMapper<Loan> {
    @Override
    public Loan map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Loan(
                rs.getInt("loanId"),
                rs.getInt("bookId"),
                rs.getInt("userId"),
                rs.getDate("loanDate"),
                rs.getDate("expectedReturnDate"),
                rs.getDate("actualReturnDate")
        );
    }
}

