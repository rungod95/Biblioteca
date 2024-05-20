<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Solicitar Libro</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<%@include file="includes/header.jsp"%>
<div class="container mt-5">
    <h1>Solicitar Libro</h1>
    <form action="${pageContext.request.contextPath}/RequestBookServlet" method="post">
        <div class="mb-3">
            <label for="bookTitle" class="form-label">Título del Libro</label>
            <input type="text" class="form-control" id="bookTitle" name="bookTitle" required>
        </div>
        <div class="mb-3">
            <label for="author" class="form-label">Autor</label>
            <input type="text" class="form-control" id="author" name="author" required>
        </div>
        <div class="mb-3">
            <label for="isbn" class="form-label">ISBN</label>
            <input type="text" class="form-control" id="isbn" name="isbn" required>
        </div>
        <div class="mb-3">
            <label for="urgencyLevel" class="form-label">Nivel de Urgencia</label>
            <select class="form-control" id="urgencyLevel" name="urgencyLevel">
                <option value="Bajo">Bajo</option>
                <option value="Medio">Medio</option>
                <option value="Alto">Alto</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Enviar Solicitud</button>
    </form>
</div>
</body>
</html>
<%@include file="includes/footer.jsp"%>