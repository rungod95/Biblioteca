package com.svalero.biblioteca.servlet;

import com.svalero.biblioteca.dao.BookDao;
import com.svalero.biblioteca.dao.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/remove-book")
public class RemoveBook extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookId = Integer.parseInt(request.getParameter("bookId"));

        try {

            int affectedRows = Database.getInstance().withExtension(BookDao.class,
                    dao -> dao.removeBook(bookId));
            if (affectedRows > 0) {
                response.sendRedirect("index.jsp?message=Book removed successfully");
            } else {
                response.sendRedirect("index.jsp?error=Book could not be removed");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp?error=Invalid book ID");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
