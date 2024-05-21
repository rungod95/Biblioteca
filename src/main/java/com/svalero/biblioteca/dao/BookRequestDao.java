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

    @SqlQuery("SELECT br.*, u.firstName, u.lastName FROM BookRequests br JOIN Users u ON br.userId = u.userId ORDER BY CASE br.urgencyLevel WHEN 'High' THEN 1 WHEN 'Medium' THEN 2 ELSE 3 END")
    List<BookRequest> getAllRequests();

    @SqlQuery("SELECT br.*, u.firstName, u.lastName FROM BookRequests br JOIN Users u ON br.userId = u.userId WHERE br.userId = :userId")
    List<BookRequest> getRequestsByUser(@Bind("userId") int userId);
    @SqlQuery("SELECT br.*, u.firstName, u.lastName FROM BookRequests br JOIN Users u ON br.userId = u.userId WHERE LOWER(br.bookTitle) LIKE :search OR LOWER(br.author) LIKE :search OR LOWER(br.isbn) LIKE :search OR LOWER(br.urgencyLevel) LIKE :search")
    List<BookRequest> searchBookRequests(@Bind("search") String search);

}
