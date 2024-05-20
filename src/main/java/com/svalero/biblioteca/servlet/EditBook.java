package com.svalero.biblioteca.servlet;

import com.svalero.biblioteca.dao.BookDao;
import com.svalero.biblioteca.dao.Database;
import com.svalero.biblioteca.domain.BookS;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@WebServlet("/edit-book")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 5, // 5 MB
        maxRequestSize = 1024 * 1024 * 5 * 5 // 25 MB
)
public class EditBook extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        HttpSession currentSession = request.getSession();
        if (!"admin".equals(currentSession.getAttribute("role"))) {
            response.sendRedirect("/biblioteca"); // Redirección a la página principal si no es admin
            return;
        }

        String uploadDirectory = getServletContext().getRealPath("/uploads");
        File fileSaveDir = new File(uploadDirectory);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        Part filePart = request.getPart("photo");
        String fileName = filePart.getSubmittedFileName();
        String photoPath;

        if (fileName != null && !fileName.isEmpty()) {
            File file = new File(uploadDirectory, fileName);
            try (InputStream fileContent = filePart.getInputStream()) {
                Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
            photoPath = "/uploads/" + fileName;
        } else {
            photoPath = "/images/default-book.jpg"; //
        }

        try {
            int bookId = request.getParameter("bookId") != null ? Integer.parseInt(request.getParameter("bookId")) : 0;
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            String isbn = request.getParameter("isbn");
            String edition = request.getParameter("edition");
            int publicationYear = Integer.parseInt(request.getParameter("publicationYear"));
            String category = request.getParameter("category");
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            if (bookId == 0) {
                String finalPhotoPath = photoPath;
                Database.getInstance().withExtension(BookDao.class, dao -> dao.addBook(title, author, isbn, edition, publicationYear, category, quantity, finalPhotoPath));
            } else {

                if (photoPath.equals("/images/default-book.jpg")) {
                    BookS book = Database.getInstance().withExtension(BookDao.class, dao -> dao.getBook(bookId));
                    photoPath = book.getPhoto();
                }
                String finalPhotoPath1 = photoPath;
                Database.getInstance().withExtension(BookDao.class, dao -> dao.updateBook(bookId, title, author, isbn, edition, publicationYear, category, quantity, finalPhotoPath1));
            }
            response.sendRedirect("/biblioteca?message=Book updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            String errorMessage = URLEncoder.encode("Error processing the request: " + e.getMessage(), StandardCharsets.UTF_8);
            response.sendRedirect("/biblioteca?error=" + errorMessage);
        }
    }
}
