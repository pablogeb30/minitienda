<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="carrito" scope="session" class="minitienda.Carrito" />

<!DOCTYPE html>

<html lang="es">

    <!-- Cabecera -->
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/carrito.css">
        <title>Carrito</title>
    </head>

    <!-- Cuerpo -->
    <body>
        <!-- Titulo -->
        <div class="titulo">
            <img src="images/carrito.png" alt="Carrito">
            <h1>Carrito</h1>
            <img src="images/carrito.png" alt="Carrito">
        </div>

        <!-- Formulario -->
        <form name="eliminar" method="post" action="controlador">
            <table>
                <!-- Encabezado de la tabla -->
                <tr>
                    <th>Titulo</th>
                    <th>Cantidad</th>
                    <th>Importe</th>
                    <th>Eliminar</th>
                </tr>

                <!-- Cuerpo de la tabla -->
                <c:set var="discos" value="${carrito.discos}"/>
                <c:forEach var="disco" items="${discos}" varStatus="loop">
                    <tr>
                        <td>${disco.info}</td>
                        <td>${disco.cantidad}</td>
                        <td>
                            <fmt:formatNumber value="${disco.precio * disco.cantidad}" type="number" minFractionDigits="2" maxFractionDigits="2"/></td>
                        <td>
                            <label for="posDiscoMarcado"></label>
                            <input type="radio" name="posDiscoMarcado" id="posDiscoMarcado" value="${loop.index}" required>
                        </td>
                    </tr>
                </c:forEach>

                <!-- Pie de la tabla -->
                <tr>
                    <td></td>
                    <td><b>IMPORTE TOTAL</b></td>
                    <td><fmt:formatNumber value="${carrito.importeTotal}" type="number" minFractionDigits="2" maxFractionDigits="2"/></td>
                    <td>
                        <c:choose>
                            <c:when test="${empty carrito.discos}">
                                <input type="submit" value="Eliminar" disabled>
                            </c:when>
                            <c:otherwise>
                                <input type="submit" value="Eliminar">
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </table>
        </form>

        <!-- Botones -->
        <div class="button-container">
            <!-- Comprar mas -->
            <img src="images/cd.png" alt="CD">
            <form name="comprarMas" method="post" action="controlador">
                <input type="submit" name="inicio" value="Comprar mas">
            </form>

            <!-- Ir a la caja -->
            <img src="images/pagar.png" alt="Pagar">
            <form name="irCaja" method="post" action="controlador">
                <c:choose>
                    <c:when test="${carrito.importeTotal == 0}">
                        <input type="submit" name="irCaja" value="Ir a la caja" disabled>
                    </c:when>
                    <c:otherwise>
                        <input type="submit" name="irCaja" value="Ir a la caja">
                    </c:otherwise>
                </c:choose>
            </form>
        </div>
    </body>

</html>
