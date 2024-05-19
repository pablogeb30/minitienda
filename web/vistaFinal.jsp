<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="usuario" scope="session" type="minitienda.Usuario"/>
<jsp:useBean id="pedido" scope="session" type="minitienda.Pedido"/>

<!DOCTYPE HTML>

<HTML>
<HEAD><TITLE>Compra finalizada</TITLE>
    <meta charset="UTF-8"></HEAD>
<BODY BGCOLOR="#FDF5E6">
<table align="center" border="0">
    <tr>
        <th><IMG SRC="images/cd.png" ALIGN="CENTER"></th>
        <th><H1>Compra finalizada</H1></th>
        <th><IMG SRC="images/cd.png" ALIGN="CENTER"></th>
    </tr>
</table>

<p></p>
<hr>

<p align="center"> El usuario: <strong>${usuario.nombre}</strong></p>
<p align="center"> con el correo: <strong>${usuario.correo}</strong></p>
<p align="center"> ha gastado un total de: <strong>${pedido.importeTotal}</strong></p>
<p align="center"> en el pedido: <strong>${pedido.id}</strong></p>

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
