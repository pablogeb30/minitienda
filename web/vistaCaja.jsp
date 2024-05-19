<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="carrito" scope="session" type="minitienda.Carrito"/>

<!DOCTYPE HTML>

<HTML>
<HEAD><TITLE>Caja</TITLE>
    <meta charset="UTF-8"></HEAD>
<BODY BGCOLOR="#FDF5E6">
<table align="center" border="0">
    <tr>
        <th><IMG SRC="images/cd.png" ALIGN="CENTER"></th>
        <th><H1>Caja</H1></th>
        <th><IMG SRC="images/cd.png" ALIGN="CENTER"></th>
    </tr>
</table>

<p></p>
<hr>

<table align="center" border="1" cellpadding="1" width="20%" bgcolor="#FFFFFF">
    <tr>
        <td><b>TOTAL A PAGAR</b></td>
    </tr>
    <tr>
        <td>$${carrito.importeTotal}</td>
    </tr>
</table>

<p></p>
<hr>

<p></p>

<form name="loginForm" method="post" action="Controlador">
    <table align="center" border="0">
        <tr>
            <td>
                <label>Nombre</label>
                <label><input type="text" class="input" name="nombre" required></label>
            </td>
            <td>
                <label>Contrasenha</label>
                <label><input type="password" class="input" name="contrasenha" required></label>
            </td>
            <td>
                <label>Correo electronico</label>
                <label><input type="email" class="input" name="correo" required></label>
            </td>
        </tr>
        <tr>
            <td>
                <label>Tipo de tarjeta</label>
                <label>
                    <select name="tipo" required>
                        <option value="visa">Visa</option>
                        <option value="mastercard">Mastercard</option>
                        <option value="american_express">American Express</option>
                    </select>
                </label>
            </td>
            <td>
                <label>Numero de tarjeta</label>
                <label><input type="text" class="input" name="tarjeta" required></label>
            </td>
        </tr>
    </table>

    <p></p>

    <table align="center" border="0">
        <tr>
            <th><img src="images/pagar.png" width="100" height="100" alt="imagen pagar"></th>
        </tr>
        <tr>
            <td><input type="submit" value="Realizar pedido" name="realizar_pedido"></td>
        </tr>
    </table>

</form>

<p></p>
<hr>

<table align="center" border="0">
    <tr>
        <th><img src="images/carrito.png" width="100" height="100" alt="imagen carrito"></th>
    </tr>
    <form name="caja" method="post" action="Controlador">
        <tr>
            <td><input type="submit" value="Volver al inicio" name="go_index"></td>
        </tr>
    </form>
</table>

<p></p>
<hr>

</BODY></HTML>
