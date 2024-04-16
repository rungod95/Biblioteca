package com.svalero.biblioteca.servlet;

import com.svalero.biblioteca.dao.Database;
import com.svalero.biblioteca.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.svalero.biblioteca.util.PasswordUtils.hashPassword;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    // Dentro del método doPost del servlet de registro
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email" +
                "");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String password = request.getParameter("password");

        String hashedPassword = hashPassword(password);
        String role = "user"; // Asignar el rol "user" por defecto

        try {
            // Aquí asumimos que tienes un método `addUser` que acepta estos parámetros
            Database.getInstance().withExtension(UserDao.class, dao -> {
                dao.addUser(firstName, lastName, email, phoneNumber, address, role, hashedPassword);
                return null; // Dependiendo de cómo esté configurado tu método, es posible que necesites retornar algo aquí
            });

            // Redirige o muestra un mensaje de éxito después de registrar al usuario
            // Por ejemplo:
            response.sendRedirect("/biblioteca/login.jsp"); // Redirige al usuario a la página de inicio de sesión
        } catch (Exception e) {
            // Manejar la excepción adecuadamente
            throw new RuntimeException("Error al registrar el usuario", e);
        }
    }
}
