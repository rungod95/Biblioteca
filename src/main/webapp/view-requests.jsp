<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Mis Solicitudes de Libros</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<%@include file="includes/header.jsp"%>
<div class="container mt-5">
    <h1>Mis Solicitudes de Libros</h1>
    <c:if test="${not empty bookRequests}">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Título</th>
                <th>Autor</th>
                <th>ISBN</th>
                <th>Nivel de Urgencia</th>
                <th>Fecha de Solicitud</th>
                <c:if test="${role == 'admin'}">
                    <th>Usuario</th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="request" items="${bookRequests}">
                <tr class="<c:choose>
                               <c:when test='${request.urgencyLevel == "Alto"}'>table-danger</c:when>
                               <c:when test='${request.urgencyLevel == "Medio"}'>table-warning</c:when>
                               <c:otherwise>table-success</c:otherwise>
                           </c:choose>">
                    <td>${request.bookTitle}</td>
                    <td>${request.author}</td>
                    <td>${request.isbn}</td>
                    <td>${request.urgencyLevel}</td>
                    <td>${request.requestDate}</td>
                    <c:if test="${role == 'admin'}">
                        <td>${request.firstName} ${request.lastName}</td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty bookRequests}">
        <p>No tienes solicitudes de libros.</p>
    </c:if>
    <a href="request-book.jsp" class="btn btn-primary">Solicitar un nuevo libro</a>
</div>
</body>
</html>
<%@include file="includes/footer.jsp" %>