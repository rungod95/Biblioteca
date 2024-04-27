<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="includes/header.jsp"%>
<html>
<head>
    <title>Gestión de Préstamos</title>
</head>
<body>
<h1>Préstamos Activos</h1>
<table border="1">
    <thead>
    <tr>
        <th>Título del Libro</th>
        <th>Fecha de Préstamo</th>
        <th>Fecha Esperada de Devolución</th>
        <th>Acciones</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${loans}" var="loan">
        <tr>
            <td>${loan.bookTitle}</td>
            <td><fmt:formatDate value="${loan.loanDate}" pattern="dd/MM/yyyy" /></td>
            <td><fmt:formatDate value="${loan.expectedReturnDate}" pattern="dd/MM/yyyy" /></td>
            <td>
                <c:if test="${loan.actualReturnDate == null}">
                    <!-- Formulario para devolver el libro, solo si no ha sido devuelto -->
                    <form method="POST" action="returnBook" onsubmit="return confirmReturn();">
                        <input type="hidden" name="loanId" value="${loan.loanId}" />
                        <button type="submit">Devolver</button>
                    </form>
                </c:if>
                <c:if test="${loan.actualReturnDate != null}">
                    &#10004; <!-- Checkmark indicando que el libro ha sido devuelto -->
                </c:if>
            </td>
        </tr>
    </c:forEach>

    </tbody>
</table>
</body>
</html>
<script>
    function confirmReturn(loanId) {
        if (confirm('¿Quieres devolver este libro?')) {
            // Redireccionar al servlet que maneja la devolución
            window.location.href = 'returnBook?loanId=' + loanId;
        }
    }
</script>

<%@include file="includes/footer.jsp" %>
