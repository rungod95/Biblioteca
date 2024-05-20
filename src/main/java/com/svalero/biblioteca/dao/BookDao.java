package com.svalero.biblioteca.dao;

import com.svalero.biblioteca.domain.BookS;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;

import java.util.List;

public interface BookDao {

    @SqlUpdate("INSERT INTO books (title, author, isbn, edition, publication_year, category, quantity, Photo) VALUES (:title, :author, :isbn, :edition, :publication_year, :category, :quantity, :Photo)")
    int addBook(@Bind("title") String title,
                @Bind("author") String author,
                @Bind("isbn") String isbn,
                @Bind("edition") String edition,
                @Bind("publication_year") int publicationYear,
                @Bind("category") String category,
                @Bind("quantity") int quantity,
                @Bind("Photo") String Photo);


    @SqlQuery("SELECT * FROM books")
    @RegisterRowMapper(BookMapper.class)
    List<BookS> getAllBooks();

    @SqlQuery("SELECT * FROM books WHERE bookid = :bookid")
    @RegisterRowMapper(BookMapper.class)
    BookS getBook(@Bind("bookid") int bookId);

    @SqlUpdate("UPDATE books SET title = :title, author = :author, isbn = :isbn, edition = :edition, publication_year = :publication_year, category = :category, quantity = :quantity, Photo = :Photo WHERE bookid = :bookid")
    int updateBook(@Bind("bookid") int bookId,
                   @Bind("title") String title,
                   @Bind("author") String author,
                   @Bind("isbn") String isbn,
                   @Bind("edition") String edition,
                   @Bind("publication_year") int publicationYear,
                   @Bind("category") String category,
                   @Bind("quantity") int quantity,
                   @Bind("Photo") String Photo);

    @SqlUpdate("DELETE FROM books WHERE bookid = :bookid")
    int removeBook(@Bind("bookid") int bookId);

    @SqlUpdate("UPDATE books SET quantity = quantity - 1 WHERE bookid = :bookid AND quantity > 0")
    int decreaseBookQuantity(@Bind("bookid") int bookId);

    @SqlQuery("SELECT * FROM books WHERE title LIKE :search OR author LIKE :search OR category LIKE :search")
    @RegisterRowMapper(BookMapper.class)
    List<BookS> searchBooks(@Bind("search") String search);

    @SqlUpdate("UPDATE books SET is_Active = :isActive WHERE bookId = :bookId")
    void changeBookVisibility(@Bind("bookId") int bookId, @Bind("isActive") int isActive);


    @SqlUpdate("UPDATE books SET quantity = quantity + 1 WHERE bookId = :bookId")
    void increaseBookQuantity(@Bind("bookId") int bookId);
    @SqlQuery("SELECT quantity FROM books WHERE bookid = :bookId")
    int getBookQuantity(@Bind("bookId") int bookId);


}