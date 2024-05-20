package com.svalero.biblioteca.servlet;

import com.svalero.biblioteca.dao.BookRequestDao;
import com.svalero.biblioteca.dao.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Date;

@WebServlet("/RequestBookServlet")
public class RequestBookServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        int userId = (int) request.getSession().getAttribute("userId");
        String bookTitle = request.getParameter("bookTitle");
        String author = request.getParameter("author");
        String isbn = request.getParameter("isbn");
        String urgencyLevel = request.getParameter("urgencyLevel");
        Date requestDate = new Date(System.currentTimeMillis());

        try {
            Database.getInstance().withExtension(BookRequestDao.class, dao -> {
                dao.addBookRequest(userId, bookTitle, author, isbn, urgencyLevel, requestDate);
                return null;
            });
            response.sendRedirect("/biblioteca//view-requests?message=Solicitud enviada exitosamente");
        } catch (Exception e) {
            e.printStackTrace();
            String errorMessage = URLEncoder.encode("Error al procesar la solicitud: " + e.getMessage(), StandardCharsets.UTF_8);
            response.sendRedirect("/biblioteca?error=" + errorMessage);
        }
    }
}
