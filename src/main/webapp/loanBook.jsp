<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="includes/header.jsp"%>

<main class="container mt-4">
    <h2>Préstamo de Libro</h2>
    <form action="loanBook" method="post">
        <div class="mb-3">
            <label for="bookId" class="form-label">ID del Libro</label>
            <input type="number" class="form-control" id="bookId" name="bookId" required>
        </div>
        <div class="mb-3">
            <label for="expectedReturnDate" class="form-label">Fecha Esperada de Devolución</label>
            <input type="date" class="form-control" id="expectedReturnDate" name="expectedReturnDate" required>
        </div>
        <button type="submit" class="btn btn-primary">Solicitar Préstamo</button>
    </form>
</main>

<%@include file="includes/footer.jsp"%>
