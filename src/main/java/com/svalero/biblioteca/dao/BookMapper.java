package com.svalero.biblioteca.dao;

import com.svalero.biblioteca.domain.BookS;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<BookS> {

    @Override
    public BookS map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new BookS(rs.getInt("bookid"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getString("isbn"),
                rs.getString("edition"),
                rs.getInt("publication_year"),
                rs.getString("category"),
                rs.getInt("quantity"),
                rs.getString("Photo"));
    }
}
