
<%--<%@ page import="static com.svalero.biblioteca.dao.Database.getInstance" %>--%>
<%--<%@ page import="java.util.List" %>--%>
<%--<%@ page import="com.svalero.biblioteca.dao.BookDao" %>--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>

<%--<%@include file="includes/header.jsp" %>--%>
<%--<main>--%>
<%--    <div class="album py-5 bg-light">--%>
<%--        <div class="container">--%>
<%--            <h1>Resultados de búsqueda</h1>--%>
<%--            <c:if test="${not empty books}">--%>
<%--                <div class="row">--%>
<%--                    <c:forEach items="${books}" var="book">--%>
<%--                        <div class="col-md-4">--%>
<%--                            <div class="card mb-4 shadow-sm">--%>
<%--                                <img src="${pageContext.request.contextPath}/${book.photo}" class="card-img-top" alt="Cover Image">--%>
<%--                                <div class="card-body">--%>
<%--                                    <p class="card-text">${book.title} - ${book.author}</p>--%>
<%--                                    <div class="d-flex justify-content-between align-items-center">--%>
<%--                                        <div class="btn-group">--%>
<%--                                            <a href="view-Book.jsp?bookId=${book.id}" class="btn btn-sm btn-outline-secondary">View</a>--%>
<%--                                            <a href="listReservations?bookId=${book.id}" class="btn btn-sm btn-outline-primary">Reserve</a>--%>
<%--                                        </div>--%>
<%--                                        <small class="text-muted">${book.category}</small>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </c:forEach>--%>
<%--                </div>--%>
<%--            </c:if>--%>
<%--            <c:if test="${empty books}">--%>
<%--                <div class="alert alert-warning" role="alert">--%>
<%--                    No se encontraron libros que coincidan con su búsqueda.--%>
<%--                </div>--%>
<%--            </c:if>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</main>--%>
<%--<%@include file="includes/footer.jsp" %>--%>
