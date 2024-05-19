<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="carrito" scope="session" class="minitienda.Carrito" />

<!DOCTYPE html>

<HTML>
<HEAD><TITLE>Carrito de la compra</TITLE>
    <meta charset="UTF-8"></HEAD>
<BODY BGCOLOR="#FDF5E6">
<table align="center" border="0">
    <tr>
        <th><IMG SRC="images/cd.png" ALIGN="CENTER"></th>
        <th><H1>Carrito de la compra</H1></th>
        <th><IMG SRC="images/cd.png" ALIGN="CENTER"></th>
    </tr>
</table>

<p></p>

<form name="fEliminar" method="post" action="Controlador">
    <table align="center" border="1" cellpadding="1" width="60%" bgcolor="#FFFFFF">
        <tr>
            <td><b>TITULO DEL CD</b></td>
            <td><b>Cantidad</b></td>
            <td><b>Importe</b></td>
            <td><b>Eliminar disco</b></td>
        </tr>

        <c:set var="discos" value="${carrito.discos}"/>
        <c:forEach var="disco" items="${discos}" varStatus="loop">
            <tr>
                <td>${disco.info}</td>
                <td>${disco.cantidad}</td>
                <td>$<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${disco.precio * disco.cantidad}"/></td>
                <td>
                    <label><input type="radio" name="posEliminar" value="${loop.index}" required></label>
                </td>
            </tr>
        </c:forEach>

        <tr>
            <td></td>
            <td><b>IMPORTE TOTAL</b></td>
            <td>$${carrito.importeTotal}</td>
            <td><input type="submit" value="Eliminar"></td>
        </tr>
    </table>
</form>

<p></p>
<hr>

<table align="center" border="0">
    <tr>
        <th><img src="images/carrito.png" width="100" height="100" alt="imagen carrito"></th>
        <th><img src="images/pagar.png" width="100" height="100" alt="imagen pagar"></th>
    </tr>
    <form name="carrito" method="post" action="Controlador">
        <tr>
            <td><input type="submit" value="Sigo comprando" name="go_index"></td>
            <td><input type="submit" value="Me largo a pagar" name="go_caja"></td>
        </tr>
    </form>
</table>

<p></p>
<hr>

</BODY></HTML>