package com.svalero.biblioteca.servlet;

import com.svalero.biblioteca.dao.BookDao;
import com.svalero.biblioteca.dao.LoanDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

import static com.svalero.biblioteca.dao.Database.jdbi;

@WebServlet("/returnBook")
public class ReturnBookServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int loanId = Integer.parseInt(request.getParameter("loanId"));

        try {
            jdbi.inTransaction(handle -> {
                LoanDao loanDao = handle.attach(LoanDao.class);
                BookDao bookDao = handle.attach(BookDao.class);


                boolean updated = loanDao.updateReturnDate(loanId, new Date(System.currentTimeMillis()));
                if (!updated) {
                    throw new IllegalStateException("No se pudo actualizar la fecha de devolución.");
                }

                // Obtengo el bookId desde el préstamo para incrementar la cantidad.
                int bookId = handle.createQuery("SELECT bookId FROM loans WHERE loanId = :loanId")
                        .bind("loanId", loanId)
                        .mapTo(Integer.class)
                        .findOnly();

                // aumenta la cantidad de libros
                bookDao.increaseBookQuantity(bookId);

                return null;  // Para cumplir con la interfaz de inTransaction
            });
            response.sendRedirect("listLoans?success=Book returned");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing book return");
        }
    }
}


