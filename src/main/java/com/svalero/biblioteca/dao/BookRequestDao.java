package com.svalero.biblioteca.dao;

import com.svalero.biblioteca.domain.BookRequest;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.sql.Date;
import java.util.List;

@RegisterRowMapper(BookRequestMapper.class)
public interface BookRequestDao {

    @SqlUpdate("INSERT INTO BookRequests (userId, bookTitle, author, isbn, urgencyLevel, requestDate) VALUES (:userId, :bookTitle, :author, :isbn, :urgencyLevel, :requestDate)")
    void addBookRequest(@Bind("userId") int userId, @Bind("bookTitle") String bookTitle, @Bind("author") String author, @Bind("isbn") String isbn, @Bind("urgencyLevel") String urgencyLevel, @Bind("requestDate") Date requestDate);

    @SqlQuery("SELECT br.*, u.firstName, u.lastName FROM BookRequests br JOIN Users u ON br.userId = u.userId ORDER BY CASE br.urgencyLevel WHEN 'Alto' THEN 1 WHEN 'Medio' THEN 2 ELSE 3 END")
    List<BookRequest> getAllRequestsWithUser();

    @SqlQuery("SELECT br.*, u.firstName, u.lastName FROM BookRequests br JOIN Users u ON br.userId = u.userId WHERE br.userId = :userId")
    List<BookRequest> getRequestsByUser(@Bind("userId") int userId);
}
