package com.svalero.biblioteca.dao;

import com.svalero.biblioteca.domain.ReservationDetail;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationDetailMapper implements RowMapper<ReservationDetail> {
    @Override
    public ReservationDetail map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new ReservationDetail(
                rs.getInt("reservationId"),
                rs.getInt("bookId"),
                rs.getInt("userId"),
                rs.getDate("reservationDate"),
                rs.getString("status"),
                rs.getString("bookTitle") // Asegurarse de que 'bookTitle' es el alias correcto en la consulta SQL
        );
    }
}
