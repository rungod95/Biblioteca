package com.svalero.biblioteca.servlet;

import com.svalero.biblioteca.dao.BookDao;
import com.svalero.biblioteca.dao.Database;
import com.svalero.biblioteca.dao.LoanDao;
import com.svalero.biblioteca.dao.ReservationDao;
import com.svalero.biblioteca.domain.ReservationDetail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

@WebServlet("/processLoan")
public class ProcessLoanServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int reservationId = Integer.parseInt(request.getParameter("reservationId"));
        int userId = (int) request.getSession().getAttribute("userId");

        try {
            Database.getInstance().inTransaction(handle -> {
                ReservationDao reservationDao = handle.attach(ReservationDao.class);
                LoanDao loanDao = handle.attach(LoanDao.class);
                BookDao bookDao = handle.attach(BookDao.class);

                // Confirmar la reserva y obtener detalles
                ReservationDetail reservation = reservationDao.findReservationById(reservationId);
                if (reservation == null || !reservation.getStatus().equals("Reserved")) {
                    throw new IllegalStateException("La reserva no está disponible para ser procesada.");
                }

                // Crear préstamo
                Date loanDate = new Date(System.currentTimeMillis());
                Date returnDate = calculateExpectedReturnDate(loanDate);
                loanDao.addLoan(reservation.getBookId(), userId, loanDate, returnDate);

                // Actualizar estado de la reserva
                reservationDao.updateReservationStatus(reservationId, "Prestado");

                                return null;
            });
            response.sendRedirect("listLoans");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al procesar el préstamo: " + e.getMessage());
        }
    }

    private Date calculateExpectedReturnDate(Date loanDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(loanDate);
        calendar.add(Calendar.DAY_OF_MONTH, 30);
        return new Date(calendar.getTimeInMillis());
    }
}
