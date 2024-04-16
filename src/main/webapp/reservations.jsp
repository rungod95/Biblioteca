<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="includes/header.jsp" %>

<h2>Mis Reservas</h2>
<table>
  <thead>
  <tr>
    <th>Título del Libro</th>
    <th>Fecha de Reserva</th>
    <th>Estado</th>
    <th>Acciones</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${reservations}" var="reservation">
    <tr>
      <td>${reservation.bookTitle}</td>
      <td><fmt:formatDate value="${reservation.reservationDate}" pattern="dd/MM/yyyy"/></td>
      <td>${reservation.status}</td>
      <td><a href="cancelReservation?reservationId=${reservation.reservationId}" class="btn btn-danger">Cancelar</a></td>
    </tr>
  </c:forEach>

  </tbody>
</table>


<%@include file="includes/footer.jsp" %>
