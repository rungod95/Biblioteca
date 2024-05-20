<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="includes/header.jsp" %>
<main class="container mt-5">
    <h1 class="text-center mb-3">Mis Reservas</h1>
    <div class="table-responsive">
        <table class="table table-hover table-bordered">
            <thead class="table-dark">
            <tr>
                <th>Título del Libro</th>
                <th>Fecha de Reserva</th>
                <th>Estado</th>
                <th>Acciones</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${empty reservations}">
                <tr>
                    <td colspan="4" class="text-center">No hay reservas disponibles.</td>
                </tr>
            </c:if>
            <c:forEach items="${reservations}" var="reservation">
                <tr>
                    <td>${reservation.bookTitle}</td>
                    <td><fmt:formatDate value="${reservation.reservationDate}" pattern="dd/MM/yyyy"/></td>
                    <td>${reservation.status}</td>
                    <td>
                        <c:if test="${reservation.status == 'Reserved'}">
                            <!-- Formulario para coger el libro prestado -->
                            <form method="POST" action="processLoan" style="display: inline;">
                                <input type="hidden" name="reservationId" value="${reservation.reservationId}">
                                <button type="submit" class="btn btn-success">Coger Libro Prestado</button>
                            </form>
                            <!-- Enlace para cancelar la reserva -->
                            <a href="cancelReservation?reservationId=${reservation.reservationId}" class="btn btn-danger" onclick="return confirm('¿Estás seguro de que quieres cancelar esta reserva?');">Cancelar</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</main>
<%@include file="includes/footer.jsp" %>
