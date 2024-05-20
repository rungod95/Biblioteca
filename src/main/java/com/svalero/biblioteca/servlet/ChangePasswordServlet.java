package com.svalero.biblioteca.servlet;

import com.svalero.biblioteca.dao.Database;
import com.svalero.biblioteca.dao.UserDao;
import com.svalero.biblioteca.util.PasswordUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        int userId = (int) request.getSession().getAttribute("userId");

        if (!newPassword.equals(confirmPassword)) {
            response.sendRedirect("profile.jsp?error=" + URLEncoder.encode("Las contraseñas no coinciden", "UTF-8"));
            return;
        }

        try {
            Database.getInstance().useHandle(handle -> {
                UserDao dao = handle.attach(UserDao.class);
                String storedPassword = dao.getPasswordByUserId(userId);


                if (!PasswordUtils.checkPassword(currentPassword, storedPassword)) {
                    throw new Exception("La contraseña actual no es correcta.");
                }

                String hashedPassword = PasswordUtils.hashPassword(newPassword);
                dao.updatePassword(userId, hashedPassword);
            });
            response.sendRedirect("profile.jsp?success=Contraseña actualizada correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al cambiar la contraseña: " + e.getMessage());
        }
    }
}
