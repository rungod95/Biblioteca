package com.svalero.biblioteca.servlet;

import com.svalero.biblioteca.dao.Database;
import com.svalero.biblioteca.dao.ReservationDao;
import com.svalero.biblioteca.domain.ReservationDetail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/listReservations")
public class ListReservationsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("userId") != null) {
            int userId = (int) session.getAttribute("userId");



            try {
                List<ReservationDetail> myReservations = Database.getInstance()
                        .withExtension(ReservationDao.class, dao -> dao.findReservationsByUserId(userId));


                System.out.println("Reservas encontradas: " + myReservations.size());

                if (!myReservations.isEmpty()) {
                    for(ReservationDetail res : myReservations) {
                        System.out.println("Reserva: " + res.getBookTitle() + ", Fecha: " + res.getReservationDate());
                    }
                }

                request.setAttribute("reservations", myReservations);
                request.getRequestDispatcher("reservations.jsp").forward(request, response);
            } catch(Exception e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp?error=" + URLEncoder.encode("Error al recuperar las reservas.", StandardCharsets.UTF_8));
            }
        } else {
            response.sendRedirect("login.jsp?error=" + URLEncoder.encode("Por favor inicia sesión para ver tus reservas.", StandardCharsets.UTF_8));
        }
    }
}
