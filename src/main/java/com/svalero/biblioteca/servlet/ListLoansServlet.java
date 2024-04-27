package com.svalero.biblioteca.servlet;

import com.svalero.biblioteca.dao.Database;
import com.svalero.biblioteca.dao.LoanDao;
import com.svalero.biblioteca.domain.Loan;
import org.jdbi.v3.core.Jdbi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/listLoans")
public class ListLoansServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Jdbi jdbi = null;
        try {
            jdbi = Database.getInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        List<Loan> loans = jdbi.withHandle(handle -> {
            LoanDao loanDao = handle.attach(LoanDao.class);
            return loanDao.findLoansByUserId(userId);
        });

        request.setAttribute("loans", loans);
        request.getRequestDispatcher("loans.jsp").forward(request, response);
    }
}
