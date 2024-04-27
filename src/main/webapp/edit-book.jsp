<%@ page import="com.svalero.biblioteca.domain.BookS" %>
<%@ page import="com.svalero.biblioteca.dao.BookDao" %>
<%@ page import="com.svalero.biblioteca.dao.Database" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="includes/header.jsp"%>

<script>
    $(document).ready(function () {
        // Asegúrate de que el script AJAX está bien configurado si decides usarlo para la carga.
    });
</script>

<main>
    <section class="py-5 container">
            <%
            role = (String) request.getSession().getAttribute("role");
            if (!"admin".equals(role)) {
                response.sendRedirect("/biblioteca");
                return;
            }

            int bookId  ;  // Inicialización predeterminada
            BookS book = null;
            String bookIdParam = request.getParameter("bookId");
            if (bookIdParam != null && !bookIdParam.isEmpty()) {
                bookId = Integer.parseInt(bookIdParam);
                try {
book = Database.getInstance().withExtension(BookDao.class, dao -> dao.getBook(bookId));} catch (ClassNotFoundException e) {
    throw new RuntimeException(e);
}
            }else {bookId=0;}
        %>

            <% if (bookId == 0) { %>
        <h1>Registrar nuevo Libro</h1>
            <% } else { %>
        <h1>Modificar Libro</h1>
            <% } %>

        <form class="row g-3 needs-validation" novalidate method="post" action="<%=request.getContextPath()%>/edit-book" enctype="multipart/form-data" id="edit-form">
            <div class="mb-3">
                <label for="title" class="form-label">Título</label>
                <input type="text" name="title" class="form-control" id="title" value="<%= book != null ? book.getTitle() : "" %>" required>
            </div>

            <div class="mb-3">
                <label for="author" class="form-label">Autor</label>
                <input type="text" name="author" class="form-control" id="author" value="<%= book != null ? book.getAuthor() : "" %>" required>
            </div>

            <div class="mb-3">
                <label for="isbn" class="form-label">ISBN</label>
                <input type="text" name="isbn" class="form-control" id="isbn" value="<%= book != null ? book.getIsbn() : "" %>" required>
            </div>

            <div class="mb-3">
                <label for="edition" class="form-label">Edición</label>
                <input type="text" name="edition" class="form-control" id="edition" value="<%= book != null ? book.getEdition() : "" %>" required>
            </div>

            <div class="mb-3">
                <label for="publicationYear" class="form-label">Año de Publicación</label>
                <input type="number" name="publicationYear" class="form-control" id="publicationYear" value="<%= book != null ? book.getPublicationYear() : "" %>" required>
            </div>

            <div class="mb-3">
                <label for="category" class="form-label">Categoría</label>
                <input type="text" name="category" class="form-control" id="category" value="<%= book != null ? book.getCategory() : "" %>" required>
            </div>

            <div class="mb-3">
                <label for="quantity" class="form-label">Cantidad</label>
                <input type="number" name="quantity" class="form-control" id="quantity" value="<%= book != null ? book.getQuantity() : 0 %>" required>
            </div>

            <div class="mb-3">
                <label for="photo" class="form-label">Foto del Libro</label>
                <input type="file" class="form-control" id="photo" name="photo" accept="image/*">
            </div>

            <div class="col-12">
                <button type="submit" class="btn btn-primary" id="edit-button">Guardar</button>
            </div>
            <input type="hidden" name="bookId" value="<%= bookId %>"/>
        </form>

        <br/>
        <div id="result"></div>
    </section>
</main>

<%@include file="includes/footer.jsp"%>