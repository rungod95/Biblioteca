package com.svalero.biblioteca.servlet;

import com.svalero.biblioteca.dao.BookDao;
import com.svalero.biblioteca.dao.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/edit-book")
public class EditBook extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        HttpSession currentSession = request.getSession();
        if (currentSession.getAttribute("role") != null && !currentSession.getAttribute("role").equals("admin")) {
            response.sendRedirect("/library"); // Asumiendo "/library" es tu página principal o de error
            return;
        }

        try {
            if (hasValidationErrors(request, response))
                return;

            int bookId = request.getParameter("bookId") != null ? Integer.parseInt(request.getParameter("bookId")) : 0;
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            String isbn = request.getParameter("isbn");
            String edition = request.getParameter("edition");
            int publicationYear = Integer.parseInt(request.getParameter("publicationYear"));
            String category = request.getParameter("category");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String Photo = request.getParameter("Photo");

            // Utiliza la instancia de Jdbi directamente sin llamar a connect()
            if (bookId == 0) {
                Database.getInstance().withExtension(BookDao.class, dao -> dao.addBook(title, author, isbn, edition, publicationYear, category, quantity, Photo));
                response.sendRedirect("/library/books?message=Book added successfully");
            } else {
                Database.getInstance().withExtension(BookDao.class, dao -> dao.updateBook(bookId, title, author, isbn, edition, publicationYear, category, quantity, Photo));
                response.sendRedirect("/library/books?message=Book updated successfully");
            }
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
            response.sendRedirect("/library/books?error=Error processing the request");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean hasValidationErrors(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Implementa aquí la lógica para validar los campos requeridos como título, autor, etc.
        // y enviar mensajes de error apropiados
        return false; // Ejemplo: return true si hay errores
    }
}


