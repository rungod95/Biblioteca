<%@ page import="com.svalero.biblioteca.dao.Database" %>
<%@ page import="com.svalero.biblioteca.dao.BookDao" %>
<%@ page import="com.svalero.biblioteca.domain.BookS" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="includes/header.jsp"%>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/sign-in.css'/>">
    <title></title>
</head>

<main>
    <section class="py-5 text-center container">
        <h1>Ver Libro</h1>
    </section>
    <%
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        BookS book = null;
        try {
            book = Database.getInstance().withExtension(BookDao.class, dao -> dao.getBook(bookId));
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener detalles del libro", e);
        }
    %>
    <div class="container">
        <div class="card">
            <img src="<%= request.getContextPath() + book.getPhoto() %>" class="card-img-top" alt="Portada del libro">
            <div class="card-body">
                <h5 class="card-title"><%= book.getTitle() %></h5>
                <p class="card-text">Autor: <%= book.getAuthor() %></p>
                <p class="card-text">ISBN: <%= book.getIsbn() %></p>
                <p class="card-text">Edición: <%= book.getEdition() %></p>
                <p class="card-text">Año de Publicación: <%= book.getPublicationYear() %></p>
                <p class="card-text">Categoría: <%= book.getCategory() %></p>
                <p class="card-text">Cantidad Disponible: <%= book.getQuantity() %></p>
            </div>
        </div>
    </div>
</main>
<%@include file="includes/footer.jsp" %>