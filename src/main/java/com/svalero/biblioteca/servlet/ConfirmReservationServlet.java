package com.svalero.biblioteca.servlet;

import com.svalero.biblioteca.dao.Database;
import com.svalero.biblioteca.dao.ReservationDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/confirmReservation")
public class ConfirmReservationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int reservationId = Integer.parseInt(request.getParameter("reservationId"));
        try {
            Database.getInstance().withExtension(ReservationDao.class, dao -> {
                dao.updateReservationStatus(reservationId, "Confirmed");
                return null;
            });
            response.sendRedirect("reservations.jsp"); // Redirecciona de vuelta a la lista de reservas con el estado actualizado
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al confirmar la reserva");
        }
    }
}