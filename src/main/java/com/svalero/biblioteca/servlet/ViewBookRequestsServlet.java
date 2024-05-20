package com.svalero.biblioteca.servlet;

import com.svalero.biblioteca.dao.BookRequestDao;
import com.svalero.biblioteca.dao.Database;
import com.svalero.biblioteca.domain.BookRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/view-requests")
public class ViewBookRequestsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = (int) request.getSession().getAttribute("userId");
        String role = (String) request.getSession().getAttribute("role");

        try {
            List<BookRequest> bookRequests;
            if ("admin".equals(role)) {
                bookRequests = Database.getInstance().withExtension(BookRequestDao.class, BookRequestDao::getAllRequestsWithUser);
            } else {
                bookRequests = Database.getInstance().withExtension(BookRequestDao.class, dao -> dao.getRequestsByUser(userId));
            }
            request.setAttribute("bookRequests", bookRequests);
            request.setAttribute("role", role); // Añadir esto para pasar el rol a la JSP
            request.getRequestDispatcher("/view-requests.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error al obtener las solicitudes de libros", e);
        }
    }
}
