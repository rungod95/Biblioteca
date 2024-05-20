package com.svalero.biblioteca.servlet;

import com.svalero.biblioteca.dao.Database;
import com.svalero.biblioteca.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = (int) request.getSession().getAttribute("userId");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");

        try {
            Database.getInstance().useHandle(handle -> {
                UserDao dao = handle.attach(UserDao.class);
                dao.updateUser(userId, firstName, lastName, email, phoneNumber, address);
            });
            response.sendRedirect("profile.jsp?success=Información actualizada correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("profile.jsp?error=" + URLEncoder.encode("Error al actualizar el perfil: " + e.getMessage(), "UTF-8"));
        }
    }
}

