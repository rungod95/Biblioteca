package com.svalero.biblioteca.servlet;

import com.svalero.biblioteca.dao.BookDao;
import com.svalero.biblioteca.dao.Database;
import com.svalero.biblioteca.domain.BookS;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@WebServlet("/listBooks")
public class ListBooksServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        displayBooks(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("changeVisibility".equals(action)) {
            changeVisibility(request, response);
        } else {
            displayBooks(request, response);
        }
    }

    private void displayBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<BookS> books = Database.getInstance().withExtension(BookDao.class, BookDao::getAllBooks);
            request.setAttribute("books", books);
            request.getRequestDispatcher("/listBooks.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error al obtener la lista de libros", e);
        }
    }

    private void changeVisibility(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        boolean shouldBeActive = Boolean.parseBoolean(request.getParameter("shouldBeActive"));
        int activeValue = shouldBeActive ? 1 : 0;

        try {
            Database.getInstance().useHandle(handle -> {
                BookDao dao = handle.attach(BookDao.class);
                dao.changeBookVisibility(bookId, activeValue);
            });
            response.sendRedirect("listBooks");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("listBooks?error=" + URLEncoder.encode("Error changing book visibility: " + e.getMessage(), "UTF-8"));
        }
    }
}
