<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.svalero.biblioteca.domain.BookS" %>
<%@ page import="com.svalero.biblioteca.dao.BookDao" %>
<%@ page import="java.util.List" %>
<%@ page import="com.svalero.biblioteca.dao.Database" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="static com.svalero.biblioteca.dao.Database.getInstance" %>

<%@include file="includes/header.jsp"%>

<%
    role = (String) request.getSession().getAttribute("role");
  boolean isAdmin = "admin".equals(role);
%>

<main>
  <section class="py-5 text-center container">
    <div class="row py-lg-5">
      <div class="col-lg-6 col-md-8 mx-auto">
        <h1 class="fw-light">Biblioteca Online</h1>
        <p class="lead text-body-secondary">Explora nuestra colección de libros</p>
        <% if (isAdmin) { %>
        <a href="edit-book.jsp" class="btn btn-primary my-2">Registrar nuevo libro</a>
        <% } %>
      </div>
    </div>
  </section>

  <div class="album py-5 bg-body-tertiary">
    <div class="container">
      <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
        <%
          List<BookS> books = null;
          try {
            books = getInstance().withExtension(BookDao.class, BookDao::getAllBooks);
            for (BookS book : books) {
        %>
        <div class="col">
          <div class="card shadow-sm">
            <div class="card-body">
              <p class="card-text"><strong><%= book.getTitle() %></strong> por <%= book.getAuthor() %></p>
              <div class="d-flex justify-content-between align-items-center">
                <div class="btn-group">
                  <a href="view-Book.jsp?bookId=<%= book.getId() %>" type="button" class="btn btn-sm btn-outline-primary">Ver</a>
                  <% if (isAdmin) { %>
                  <a href="edit-book.jsp?bookId=<%= book.getId() %>" type="button" class="btn btn-sm btn-outline-primary">Editar</a>
                  <a href="remove-book?bookId=<%= book.getId() %>" type="button" class="btn btn-sm btn-outline-danger">Eliminar</a>
                  <% } else if (book.getQuantity() > 0) { %>
                  <form action="reserveBook" method="POST" style="display: inline;">
                    <input type="hidden" name="bookId" value="<%= book.getId() %>" />
                    <button type="submit" class="btn btn-sm btn-outline-secondary">Reservar</button>
                  </form>
                  <% } else { %>
                  <small class="text-muted">No disponible</small>
                  <% } %>
                </div>
              </div>
            </div>
          </div>
        </div>
        <%
            }
          } catch (Exception e) {
            throw new RuntimeException("Error al obtener la lista de libros", e);
          }
        %>

      </div>
    </div>
  </div>
</main>

<%@include file="includes/footer.jsp"%>
