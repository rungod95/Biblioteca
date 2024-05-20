<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.svalero.biblioteca.domain.BookS" %>
<%@ page import="com.svalero.biblioteca.dao.BookDao" %>
<%@ page import="java.util.List" %>
<%@ page import="com.svalero.biblioteca.dao.Database" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="static com.svalero.biblioteca.dao.Database.getInstance" %>

<%@include file="includes/header.jsp"%>

<main>
    <section class="py-5 text-center container">
        <div class="row py-lg-5">
            <div class="col-lg-6 col-md-8 mx-auto">
                <h1 class="fw-light">Biblioteca Online</h1>
                <p class="lead text-muted">Explora nuestra colección de libros</p>
                <% if ("admin".equals(role)) { %>
                <a href="edit-book.jsp" class="btn btn-primary my-2">Registrar nuevo libro</a>
                <% } %>
            </div>
        </div>
    </section>

    <div class="album py-5 bg-light">
        <div class="container">
            <div class="row justify-content-center mb-4">
                <div class="col-md-6">
                    <form action="<%=request.getContextPath()%>/searchBooks" method="GET" class="input-group">
                        <input type="text" class="form-control" placeholder="Buscar libros..." name="searchQuery">
                        <button class="btn btn-outline-secondary" type="submit">Buscar</button>
                    </form>
                </div>
            </div>

            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                    <% List<BookS> books = null;
                    try {
                        books = getInstance().withExtension(BookDao.class, BookDao::getAllBooks);
                        for (BookS book : books) {
                %>
                <div class="col">
                    <div class="card shadow-sm">
                        <img src="<%= request.getContextPath() + book.getPhoto() %>" class="card-img-top" alt="Cover Image">
                        <div class="card-body">
                            <p class="card-text"><strong><%= book.getTitle() %></strong> por <%= book.getAuthor() %></p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <a href="view-Book.jsp?bookId=<%= book.getId() %>" class="btn btn-sm btn-outline-primary">Ver</a>
                                    <% if ("admin".equals(role)) { %>
                                    <a href="edit-book.jsp?bookId=<%= book.getId() %>" class="btn btn-sm btn-outline-primary">Editar</a>
                                    <form action="<%=request.getContextPath()%>/listBooks" method="POST">
                                        <input type="hidden" name="action" value="changeVisibility">
                                        <input type="hidden" name="bookId" value="<%= book.getId() %>">
                                        <input type="hidden" name="shouldBeActive" value="<%= !book.isActive() %>">
                                        <button type="submit" class="btn btn-sm <%= book.isActive() ? "btn-outline-warning" : "btn-outline-success" %>">
                                            <%= book.isActive() ? "Ocultar" : "Mostrar" %>
                                        </button>
                                    </form>
                                    <% } else if (book.getQuantity() > 0 && book.isActive()) { %>
                                    <form action="reserveBook" method="POST" style="display: inline;">
                                        <input type="hidden" name="bookId" value="<%= book.getId() %>">
                                        <button type="submit" class="btn btn-sm btn-outline-secondary">Reservar</button>
                                    </form>
                                    <% } else if (!book.isActive()) { %>
                                    <small class="text-muted">No disponible</small>
                                    <% } %>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                    <% } %>
                <% } catch (Exception e) { throw new RuntimeException("Error al obtener la lista de libros", e); } %>
            </div>
        </div>
    </div>
</main>

<%@include file="includes/footer.jsp"%>