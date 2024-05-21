package com.svalero.biblioteca.dao;

import com.svalero.biblioteca.domain.BookRequest;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRequestMapper implements RowMapper<BookRequest> {
    @Override
    public BookRequest map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new BookRequest(
                rs.getInt("requestId"),
                rs.getInt("userId"),
                rs.getString("bookTitle"),
                rs.getString("author"),
                rs.getString("isbn"),
                rs.getString("urgencyLevel"),
                rs.getDate("requestDate"),
                rs.getString("firstName"),   // Añadido
                rs.getString("lastName")     // Añadido
        );
    }
}
