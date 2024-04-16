package com.svalero.biblioteca.dao;

import com.svalero.biblioteca.domain.Reservation;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationMapper implements RowMapper<Reservation> {
    @Override
    public Reservation map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Reservation(
                rs.getInt("reservationId"),
                rs.getInt("bookId"),
                rs.getInt("userId"),
                rs.getDate("reservationDate"),
                rs.getString("status")
        );
    }
}
