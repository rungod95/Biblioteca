package com.svalero.biblioteca.dao;

import com.svalero.biblioteca.domain.User;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;

import java.util.List;

public interface UserDao {

    @SqlQuery("SELECT * FROM users")
    @RegisterRowMapper(UserMapper.class)
    List<User> getAllUsers();

    @SqlQuery("SELECT * FROM users WHERE email = :email")
    @RegisterRowMapper(UserMapper.class)
    User getUserByEmail(@Bind("email") String email);

    @SqlUpdate("INSERT INTO users (firstName, lastName, email, phoneNumber, address, role, password) VALUES (:firstName, :lastName, :email, :phoneNumber, :address, :role, :password)")
    void addUser(@Bind("firstName") String firstName,
                 @Bind("lastName") String lastName,
                 @Bind("email") String email,
                 @Bind("phoneNumber") String phoneNumber,
                 @Bind("address") String address,
                 @Bind("role") String role,
                 @Bind("password") String password);

    @SqlUpdate("UPDATE users SET firstName = :firstName, lastName = :lastName, email = :email, phoneNumber = :phoneNumber, address = :address WHERE userId = :userId")
    void updateUser(@Bind("userId") int userId, @Bind("firstName") String firstName, @Bind("lastName") String lastName, @Bind("email") String email, @Bind("phoneNumber") String phoneNumber, @Bind("address") String address);


    @SqlQuery("SELECT password FROM users WHERE userId = :userId")
    String getPasswordByUserId(@Bind("userId") int userId);

    @SqlUpdate("UPDATE users SET password = :password WHERE userId = :userId")
    void updatePassword(@Bind("userId") int userId, @Bind("password") String password);

    @SqlQuery("SELECT password FROM users WHERE email = :email")
    String getPasswordByUserEmail(@Bind("email") String email);




}
