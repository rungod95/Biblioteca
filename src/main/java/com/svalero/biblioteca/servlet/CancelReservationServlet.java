package com.svalero.biblioteca.servlet;

import com.svalero.biblioteca.dao.Database;
import com.svalero.biblioteca.dao.ReservationDao;

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
        // Asume que tienes una instancia de tu clase DAO accesible aquí
        try {
            Database.getInstance().inTransaction(handle -> {
                ReservationDao reservationDao = handle.attach(ReservationDao.class);
                // Cambia el estado de la reserva a "Cancelada" o algo similar
                reservationDao.cancelReservation(reservationId);
                return null;
            });
            response.sendRedirect("reservations.jsp?success=La reserva ha sido cancelada con éxito.");
        } catch (Exception e) {
            response.sendRedirect("reservations.jsp?error=No se pudo cancelar la reserva.");
        }
    }
}
