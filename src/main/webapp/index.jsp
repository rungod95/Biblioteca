<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="includes/header.jsp"%>

<main>
  <section class="py-5 text-center container">
    <div class="row py-lg-5">
      <div class="col-lg-6 col-md-8 mx-auto">
        <h1 class="fw-light">Bienvenido a tu Biblioteca Online</h1>
        <p class="lead text-muted">Explora, descubre y reserva tus libros favoritos.</p>
        <a href="<%=request.getContextPath()%>/listBooks" class="btn btn-primary my-2">Ver Catálogo de Libros</a>
        <% if ("admin".equals(role)) { %>
        <a href="edit-book.jsp" class="btn btn-secondary my-2">Registrar nuevo libro</a>
        <% } %>
      </div>
    </div>
  </section>
</main>

<%@include file="includes/footer.jsp"%>
