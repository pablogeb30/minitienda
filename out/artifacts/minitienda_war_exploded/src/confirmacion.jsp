<%@ page contentType="text/html;charset=ISO-8859-1" language="java" %>
<%@ page import="minitienda.Carrito" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
    <head>
        <title>Confirmación de Pago</title>
    </head>
    <body bgcolor="#FDF5E6">
        <h2>Total a pagar: <fmt:formatNumber value="${total}" type="currency" /></h2>
        <p>Gracias por su compra. <a href="index.html">Volver a la tienda</a></p>
    </body>
</html>
