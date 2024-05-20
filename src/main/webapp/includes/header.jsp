<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Biblioteca La Melange</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/sign-in.css'/>">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

</head>
<body>
<header data-bs-theme="dark">
    <div class="navbar navbar-dark bg-dark shadow-sm">
        <div class="container">
            <a href="${pageContext.request.contextPath}" class="navbar-brand d-flex align-items-center">
                <!-- Icono y título -->
                <strong>Biblioteca La Melange</strong>
            </a>
            <%
                String role = (String) request.getSession().getAttribute("role");
                String userName = (String) request.getSession().getAttribute("userName");
                if ("anonymous".equals(role) || role == null) {
            %>
            <a href="login.jsp" title="Iniciar sesión"><img src="icons/user.png" height="50" width="50"/></a>
            <% } else { %>
            <div class="dropdown">
                <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                    Bienvenido, <%= userName %>
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                    <li><a class="dropdown-item" href="<c:url value='/listReservations'/>">Mis reservas</a></li>
                    <li><a class="dropdown-item" href="<c:url value='/listLoans'/>">Libros Prestados</a></li>
                    <li><a class="dropdown-item" href="profile.jsp">Mi perfil</a></li>
                    <li><a class="dropdown-item" href="<c:url value='/view-requests'/>">Ver Mis Solicitudes</a></li>
                    <li><hr class="dropdown-divider"></li>
                    <li><a class="dropdown-item" href="logout">Cerrar sesión</a></li>
                </ul>
            </div>
            <% } %>
        </div>
    </div>
</header>
</body>
</html>
