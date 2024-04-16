package com.svalero.biblioteca.servlet;

import com.svalero.biblioteca.dao.Database;
import com.svalero.biblioteca.dao.ReservationDao;
import com.svalero.biblioteca.domain.ReservationDetail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/listReservations")
public class ListReservationsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<ReservationDetail> reservations = Database.getInstance().withExtension(ReservationDao.class, ReservationDao::getAllReservations);

            // Debugging: imprimir el número de reservaciones recuperadas
            System.out.println("Reservations found: " + (reservations != null ? reservations.size() : "None"));

            request.setAttribute("reservations", reservations);
            request.getRequestDispatcher("reservations.jsp").forward(request, response);

        } catch (Exception e) {
            System.out.println("Error al obtener las reservas: " + e.getMessage()); // Más depuración
            throw new RuntimeException("Error al obtener las reservas", e);
        }
    }
}
