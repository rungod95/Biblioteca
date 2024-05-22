<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="includes/header.jsp" %>

<main class="container mt-5">
    <h1 class="text-center mb-3">Solicitudes de Libros</h1>
    <div class="row justify-content-center mb-4">
        <div class="col-md-6">
            <form action="<%=request.getContextPath()%>/view-requests" method="GET" class="input-group">
                <label>
                    <input type="text" class="form-control" placeholder="Buscar solicitudes..." name="searchQuery">
                </label>
                <button class="btn btn-outline-secondary" type="submit">Buscar</button>
            </form>
        </div>
    </div>
    <div class="table-responsive">
        <table class="table table-hover table-bordered">
            <thead class="table-dark">
            <tr>
                <th data-field="bookTitle" data-align="center" data-halign="center" data-sortable="true">Título del Libro</th>
                <th data-field="requestDate" data-align="center" data-halign="center" data-sortable="true">Fecha de Solicitud</th>
                <th data-field="urgencyLevel" data-align="center" data-halign="center" data-sortable="true">Nivel de Urgencia</th>
                <c:if test="${role == 'admin'}">
                    <th data-field="requestedBy" data-align="center" data-halign="center" data-sortable="true">Solicitado Por</th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:if test="${empty bookRequests}">
                <tr>
                    <td colspan="4" class="text-center">No hay solicitudes de libros.</td>
                </tr>
            </c:if>
            <a href="request-book.jsp" class="btn btn-primary">Solicitar un nuevo libro</a>
            <c:forEach items="${bookRequests}" var="request">
                <tr class="<c:choose>
                           <c:when test='${request.urgencyLevel == "High"}'>table-danger</c:when>
                           <c:when test='${request.urgencyLevel == "Medium"}'>table-warning</c:when>
                           <c:otherwise>table-success</c:otherwise>
                       </c:choose>">
                    <td>${request.bookTitle}</td>
                    <td><fmt:formatDate value="${request.requestDate}" pattern="dd/MM/yyyy"/></td>
                    <td>${request.urgencyLevel}</td>
                    <c:if test="${role == 'admin'}">
                        <td>${request.firstName} ${request.lastName}</td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>
</main>
<%@include file="includes/footer.jsp" %>
