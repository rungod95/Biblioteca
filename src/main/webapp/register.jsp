<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="includes/header.jsp"%>

<main class="form-signin w-100 m-auto">
    <form action="register" method="post">
        <div class="form-group">
            <label for="firstName">Nombre</label>
            <input type="text" class="form-control" id="firstName" name="firstName" required>
        </div>
        <div class="form-group">
            <label for="lastName">Apellidos</label>
            <input type="text" class="form-control" id="lastName" name="lastName" required>
        </div>
        <div class="form-group">
            <label for="email">Correo Electrónico</label>
            <input type="email" class="form-control" id="email" name="email" required>
        </div>
        <div class="form-group">
            <label for="phoneNumber">Número de Teléfono</label>
            <input type="text" class="form-control" id="phoneNumber" name="phoneNumber">
        </div>
        <div class="form-group">
            <label for="address">Dirección</label>
            <input type="text" class="form-control" id="address" name="address">
        </div>
        <div class="form-group">
            <label for="password">Contraseña</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>
        <!-- Más campos según se necesite -->
        <button class="w-100 btn btn-lg btn-primary" type="submit">Registrarse</button>
    </form>
</main>

<%@include file="includes/footer.jsp"%>
