package com.svalero.biblioteca.servlet;

import com.svalero.biblioteca.dao.BookDao;
import com.svalero.biblioteca.domain.BookS;
import org.jdbi.v3.core.Jdbi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.svalero.biblioteca.dao.Database.getInstance;

@WebServlet("/listBooks")
public class ListBooksServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Jdbi jdbi = getInstance();
            if (jdbi != null) {
                List<BookS> books = jdbi.withExtension(BookDao.class, BookDao::getAllBooks);
                request.setAttribute("books", books);
            } else {
                throw new RuntimeException("No se pudo obtener la conexión a la base de datos.");
            }
            request.getRequestDispatcher("/listBooks.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error al obtener la lista de libros", e);
        }
    }
}
