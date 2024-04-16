package com.svalero.biblioteca.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorUtils {

    public static void sendError(String message, HttpServletResponse response) throws IOException {
        response.getWriter().println("<div class='alert alert-danger' role='alert'>" + message + "</div>");
    }

    public static void sendMessage(String message, HttpServletResponse response) throws IOException {
        response.getWriter().println("<div class='alert alert-success' role='alert'>" + message + "</div>");
    }
}
