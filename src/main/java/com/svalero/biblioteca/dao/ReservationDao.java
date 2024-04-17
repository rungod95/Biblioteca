package com.svalero.biblioteca.dao;

import com.svalero.biblioteca.domain.Reservation;
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

    @SqlQuery("SELECT * FROM reservations")
    @RegisterRowMapper(ReservationMapper.class)
    List<Reservation> getAllReservations();

    @SqlQuery("SELECT r.*, b.title as bookTitle FROM reservations r INNER JOIN books b ON r.bookId = b.bookId WHERE r.userId = :userId")
    @RegisterRowMapper(ReservationDetailMapper.class)
        //
    List<ReservationDetail> findReservationsByUserId(@Bind("userId") int userId);

    @SqlUpdate("UPDATE reservations SET status = :status WHERE reservationId = :reservationId")
    void updateReservationStatus(@Bind("reservationId") int reservationId, @Bind("status") String status);
}
