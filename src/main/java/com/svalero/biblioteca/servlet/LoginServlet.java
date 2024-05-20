package com.svalero.biblioteca.servlet;

import com.svalero.biblioteca.dao.Database;
import com.svalero.biblioteca.dao.UserDao;
import com.svalero.biblioteca.domain.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        PrintWriter out = response.getWriter();

        try {
            User user = Database.getInstance().withExtension(UserDao.class, dao -> dao.getUserByEmail(email));

            if (user != null && BCrypt.checkpw(password, user.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("userId", user.getUserId());
                session.setAttribute("role", user.getRole());
                session.setAttribute("userName", user.getFirstName() + " " + user.getLastName()); // Guarda el nombre completo
                out.print("success"); // Envía "success" si el login es correcto
            } else {
                out.print("failure");
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); //
            out.print("error");
        }
        out.flush();
        out.close();
    }

   }
