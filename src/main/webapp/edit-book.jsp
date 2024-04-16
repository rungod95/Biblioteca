<%@ page import="com.svalero.biblioteca.domain.BookS" %>
<%@ page import="com.svalero.biblioteca.dao.BookDao" %>
<%@ page import="com.svalero.biblioteca.dao.Database" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="includes/header.jsp"%>

<script>
    $(document).ready(function () {
        $("#edit-button").click(function (event) {
            event.preventDefault();
            const form = $("#edit-form")[0];
            const data = new FormData(form);

            $("#edit-button").prop("disabled", true);

            $.ajax({
                type: "POST",
                enctype: "multipart/form-data",
                url: "edit-book",  // Asegúrate de que la URL corresponda a tu servlet de edición de libros
                data: data,
                processData: false,
                contentType: false,
                cache: false,
                timeout: 600000,
                success: function (data) {
                    $("#result").html(data);
                    $("#edit-button").prop("disabled", false);
                },
                error: function (error) {
                    $("#result").html(error.responseText);
                    $("#edit-button").prop("disabled", false);
                }
            });
        });
    });
</script>

<%
        if (!"admin".equals(role)) {
        response.sendRedirect("/library");
        return;
    }

    int bookId;
    BookS book = null;
    if (request.getParameter("bookId") == null) {
        bookId = 0;
    } else {
        bookId = Integer.parseInt(request.getParameter("bookId"));
        try {
            book = Database.getInstance().withExtension(BookDao.class, dao -> dao.getBook(bookId));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
%>

<main>
    <section class="py-5 container">
        <% if (bookId == 0) { %>
        <h1>Registrar nuevo Libro</h1>
        <% } else { %>
        <h1>Modificar Libro</h1>
        <% } %>
        <form class="row g-3 needs-validation" novalidate method="post" enctype="multipart/form-data" id="edit-form">
            <!-- Campos específicos para libros (título, autor, etc.) -->
            <div class="mb-3">
                <label for="title" class="form-label">Título</label>
                <input type="text" name="title" class="form-control" id="title"
                    <% if (book != null) { %> value="<%= book.getTitle() %>" <% } %> required>
            </div>

            <!-- Añade más campos según sea necesario -->

            <div class="col-12">
                <input type="submit" value="Guardar" id="edit-button"/>
            </div>
            <input type="hidden" name="bookId" value="<%= bookId %>"/>
        </form>
        <br/>
        <div id="result"></div>
    </section>
</main>

<%@include file="includes/footer.jsp"%>
