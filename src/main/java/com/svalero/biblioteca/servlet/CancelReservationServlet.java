package com.svalero.biblioteca.servlet;

import com.svalero.biblioteca.dao.BookDao;
import com.svalero.biblioteca.dao.Database;
import com.svalero.biblioteca.dao.ReservationDao;
import com.svalero.biblioteca.domain.ReservationDetail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cancelReservation")
public class CancelReservationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int reservationId = Integer.parseInt(request.getParameter("reservationId"));

        try {
            Database.getInstance().inTransaction(handle -> {
                ReservationDao reservationDao = handle.attach(ReservationDao.class);
                BookDao bookDao = handle.attach(BookDao.class);

                // Primero obtenemos la información de la reserva para saber qué libro incrementar
                ReservationDetail reservation = reservationDao.findReservationById(reservationId);
                if (reservation != null) {
                    // Incrementamos la cantidad de libros disponibles
                    bookDao.increaseBookQuantity(reservation.getBookId());
                    // Ahora eliminamos la reserva
                    reservationDao.deleteReservation(reservationId);
                } else {
                    throw new ServletException("No se encontró la reserva con ID: " + reservationId);
                }

                return null;
            });
            response.sendRedirect("listReservations?success=La reserva ha sido cancelada y el libro ha sido devuelto.");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("listReservations?error=No se pudo cancelar la reserva.");
        }
    }
}


