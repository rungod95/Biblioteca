package com.svalero.biblioteca.servlet;

import com.svalero.biblioteca.dao.BookDao;
import com.svalero.biblioteca.dao.Database;
import com.svalero.biblioteca.dao.LoanDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Date;

@WebServlet("/loanBook")
public class LoanBookServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
        Date loanDate = new Date(System.currentTimeMillis());
        Date expectedReturnDate = Date.valueOf(request.getParameter("expectedReturnDate"));

        try {
            Database.getInstance().inTransaction(handle -> {
                LoanDao loanDao = handle.attach(LoanDao.class);
                BookDao bookDao = handle.attach(BookDao.class);

                loanDao.addLoan(bookId, userId, loanDate, expectedReturnDate);
                int updatedRows = bookDao.decreaseBookQuantity(bookId);
                if (updatedRows == 0) {
                    throw new IllegalStateException("No hay suficientes libros disponibles para el préstamo.");
                }
                return null;
            });
            response.sendRedirect("loans.jsp");
        } catch (Exception e) {
            response.sendRedirect("loanBook.jsp?error=" + URLEncoder.encode("Error al procesar el préstamo: " + e.getMessage(), StandardCharsets.UTF_8));
        }
    }
}
