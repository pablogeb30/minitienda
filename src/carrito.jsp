<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="minitienda.Carrito" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="carrito" scope="session" class="minitienda.Carrito" />
<html>
    <head>
        <title>Carrito de Compras</title>
    </head>
    <body bgcolor="#FDF5E6">
        <h2>Carrito de la compra</h2>
        <c:choose>
            <c:when test="${not empty carrito.items}">
                <ul>
                    <c:forEach var="item" items="${carrito.items}">
                        <li>${item.key} - Cantidad: ${item.value}
                            <form action="eliminarDeCarrito" method="post" style="display:inline;">
                                <input type="hidden" name="cd" value="${item.key}">
                                <input type="submit" value="Eliminar">
                            </form>
                        </li>
                    </c:forEach>
                </ul>
                <p>Total: <fmt:formatNumber value="${carrito.calculateTotal()}" type="currency" /></p>
                <form action="confirmacionServlet" method="get">
                    <input type="submit" value="Proceder al Pago">
                </form>
            </c:when>
            <c:otherwise>
                <p>El carrito está vacío</p>
            </c:otherwise>
        </c:choose>
    </body>
</html>
