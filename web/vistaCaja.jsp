<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="carrito" scope="session" type="minitienda.Carrito"/>

<!DOCTYPE HTML>

<html lang="es">

    <!-- Cabecera -->
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/caja.css">
        <script src="js/caja.js"></script>
        <title>Musica para DAW</title>
    </head>

    <!-- Cuerpo -->
    <body>
        <!-- Titulo -->
        <div class="titulo">
            <img src="images/pagar.png" alt="Pagar">
            <h1>Caja</h1>
            <img src="images/pagar.png" alt="Pagar">
        </div>

        <!-- Importe total -->
        <table>
            <tr>
                <th>IMPORTE TOTAL</th>
            </tr>
            <tr>
                <td><fmt:formatNumber value="${carrito.importeTotal}" type="number" minFractionDigits="2" maxFractionDigits="2"/></td>
            </tr>
        </table>

        <!-- Botones de inicio de sesion y registro -->
        <div class="botones">
            <button id="btnInicioSesion" type="button" onclick="mostrarCamposInicioSesion()">Iniciar sesion</button>
            <button id="btnRegistro" type="button" onclick="mostrarCamposRegistro()">Registrarse</button>
        </div>

        <!-- Formulario de pago -->
        <form name="caja" method="post" action="controlador">
            <input type="hidden" name="accion" id="accion" value="">
            <table>
                <!-- Filas de nombre, contrasenha y correo electronico -->
                <tr>
                    <td>
                        <label for="nombre">Nombre</label>
                        <input type="text" name="nombre" id="nombre" required>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="correo">Correo electronico</label>
                        <input type="email" name="correo" id="correo" required>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="contrasenha">Contrasenha</label>
                        <input type="password" name="contrasenha" id="contrasenha" required>
                    </td>
                </tr>

                <!-- Fila de tipo de tarjeta y numero de tarjeta -->
                <tr id="form-tarjeta" style="display: none">
                    <td>
                        <label for="tipo">Tipo de tarjeta</label>
                        <select name="tipo" id="tipo">
                            <option value="americanExpress">American Express</option>
                            <option value="mastercard">Mastercard</option>
                            <option value="visa">Visa</option>
                        </select>
                    </td>
                    <td>
                        <label for="numero">Numero de tarjeta</label>
                        <input type="number" name="numero" id="numero">
                    </td>
                </tr>
            </table>

            <!-- Boton de confirmar pago -->
            <input type="submit" name="confirmarPago" id="confirmarPago" value="Confirmar pago">
        </form>

        <!-- Boton de comprar mas -->
        <img src="images/cd.png" alt="CD" id="CD">
        <form name="comprarMas" method="post" action="controlador">
            <input type="submit" name="inicio" value="Comprar mas">
        </form>
    </body>

</html>
