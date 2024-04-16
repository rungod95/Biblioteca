<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="includes/header.jsp"%>

<main class="container mt-4">
    <h2>Lista de Libros Disponibles</h2>
    <div class="row">
        <c:forEach var="book" items="${books}">
            <div class="col-md-4">
                <div class="card mb-4 shadow-sm">
                    <img src="${book.photo}" alt="Portada del libro" class="bd-placeholder-img card-img-top"/>
                    <div class="card-body">
                        <p class="card-text"><strong>${book.title}</strong> por ${book.author}</p>
                        <!-- Más detalles del libro -->
                        <c:choose>
                            <c:when test="${not empty sessionScope.userId && book.quantity > 0}">
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="btn-group">
                                        <a href="reserveBook?bookId=${book.bookId}" class="btn btn-sm btn-outline-secondary">Reservar</a>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <small class="text-muted">No disponible para reserva</small>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</main>

<%@include file="includes/footer.jsp"%>
