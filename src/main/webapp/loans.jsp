<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="includes/header.jsp"%>
<main class="container mt-5">
    <h1 class="text-center mb-3">Préstamos Activos</h1>
    <table class="table table-hover table-striped">
        <thead class="table-dark">
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
                <td><fmt:formatDate value="${loan.loanDate}" pattern="dd/MM/yyyy"/></td>
                <td><fmt:formatDate value="${loan.expectedReturnDate}" pattern="dd/MM/yyyy"/></td>
                <td>
                    <c:if test="${loan.actualReturnDate == null}">
                        <form method="POST" action="returnBook">
                            <input type="hidden" name="loanId" value="${loan.loanId}"/>
                            <button type="submit" class="btn btn-primary btn-sm" onclick="return confirm('¿Quieres devolver este libro?');">Devolver</button>
                        </form>
                    </c:if>
                    <c:if test="${loan.actualReturnDate != null}">
                        <span class="badge bg-success">Devuelto</span>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</main>
<%@include file="includes/footer.jsp"%>
