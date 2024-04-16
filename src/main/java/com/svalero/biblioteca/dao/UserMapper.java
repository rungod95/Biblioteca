package com.svalero.biblioteca.dao;

import com.svalero.biblioteca.domain.User;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new User(
                rs.getInt("USERID"), // Asumiendo que USERID es un entero
                rs.getString("FIRSTNAME"),
                rs.getString("LASTNAME"),
                rs.getString("EMAIL"),
                rs.getString("PHONENUMBER"),
                rs.getString("ADDRESS"),
                rs.getString("ROLE"),
                rs.getString("PASSWORD") // Asume que no cargarás o expondrás este valor sin cuidado
        );
    }
}