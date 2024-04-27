package com.svalero.biblioteca.servlet;

import com.svalero.biblioteca.dao.BookDao;
import com.svalero.biblioteca.dao.Database;
import com.svalero.biblioteca.dao.ReservationDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/reserveBook")
public class ReserveBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("userId") == null) {
            // Si no hay usuario logueado, redirige al login
            response.sendRedirect("login.jsp?error=" + URLEncoder.encode("Por favor inicia sesión para reservar libros.", StandardCharsets.UTF_8));
            return;
        }

        int bookId = Integer.parseInt(request.getParameter("bookId"));
        int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
        java.sql.Date reservationDate = new java.sql.Date(System.currentTimeMillis());
        String status = "Reserved";

        try {
            Database.getInstance().inTransaction(handle -> {
                ReservationDao reservationDao = handle.attach(ReservationDao.class);
                BookDao bookDao = handle.attach(BookDao.class);

                int updatedRows = bookDao.decreaseBookQuantity(bookId);
                if (updatedRows == 0) {
                    throw new IllegalStateException("No se pudo reservar el libro, posiblemente debido a falta de stock.");
                }

                reservationDao.addReservation(bookId, userId, reservationDate, status);
                return null;
            });

            response.sendRedirect("listReservations");  //
        } catch (Exception e) {
            // Si ocurre una excepción, redirige a una página que sabes que existe
            response.sendRedirect("index.jsp?error=" + URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8));  // Asume que 'errorPage.jsp' existe
        }
    }
}