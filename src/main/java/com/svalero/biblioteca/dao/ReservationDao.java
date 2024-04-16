package com.svalero.biblioteca.dao;

import com.svalero.biblioteca.domain.ReservationDetail;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface ReservationDao {

    @SqlUpdate("INSERT INTO Reservations (bookId, userId, reservationDate, status) VALUES (:bookId, :userId, :reservationDate, :status)")
    void addReservation(@Bind("bookId") int bookId, @Bind("userId") int userId, @Bind("reservationDate") java.sql.Date reservationDate, @Bind("status") String status);

    @SqlUpdate("UPDATE reservations SET status = 'Cancelada' WHERE reservationId = :reservationId")
    void cancelReservation(@Bind("reservationId") int reservationId);

    // Y en ReservationDao
    @SqlQuery("SELECT r.reservationId, r.bookId, r.userId, r.reservationDate, r.status, b.title as bookTitle FROM reservations r JOIN books b ON r.bookId = b.bookid")
    @RegisterRowMapper(ReservationDetailMapper.class)
    List<ReservationDetail> getAllReservations();

}
