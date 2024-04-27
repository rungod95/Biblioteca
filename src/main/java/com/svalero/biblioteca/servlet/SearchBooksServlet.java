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

@WebServlet("/searchBooks")
public class SearchBooksServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String searchQuery = request.getParameter("searchQuery");
        if (searchQuery != null && !searchQuery.isEmpty()) {

            searchQuery = "%" + searchQuery.trim() + "%";
        }

        try {

            String finalSearchQuery = searchQuery;
            List<BookS> books = Database.getInstance().withExtension(BookDao.class, dao -> dao.searchBooks(finalSearchQuery));

            request.setAttribute("books", books);

            request.getRequestDispatcher("searchresults.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // Enviar al usuario a una página de error si algo sale mal
            response.sendRedirect("error.jsp?message=" + URLEncoder.encode("Error during book search: " + e.getMessage(), "UTF-8"));
        }
    }
}
