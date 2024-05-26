<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="carrito" scope="session" type="minitienda.Carrito"/>

<!DOCTYPE HTML>

<html lang="es">

    <!-- Cabecera -->
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/caja.css">
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
                <td>${carrito.importeTotal}</td>
            </tr>
        </table>

        <!-- Formulario de pago -->
        <form name="loginForm" method="post" action="controlador">
            <table>
                <!-- Fila de nombre, correo electronico y contrasenha -->
                <tr>
                    <td>
                        <label for="nombre">Nombre</label>
                        <input type="text" name="nombre" id="nombre" required>
                    </td>
                    <td>
                        <label for="correo">Correo electronico</label>
                        <input type="email" name="correo" id="correo" required>
                    </td>
                    <td>
                        <label for="contrasenha">Contrasenha</label>
                        <input type="password" name="contrasenha" id="contrasenha" required>
                    </td>
                </tr>

                <!-- Fila de tipo de tarjeta y numero de tarjeta -->
                <tr>
                    <td>
                        <label for="tipo">Tipo de tarjeta</label>
                        <select name="tipo" id="tipo" required>
                            <option value="mastercard">Mastercard</option>
                            <option value="visa">Visa</option>
                        </select>
                    </td>
                    <td colspan="2">
                        <label for="numero">Numero de tarjeta</label>
                        <input type="text" name="numero" id="numero" required>
                    </td>
                </tr>
            </table>

            <!-- Boton de confirmar pago -->
            <input type="submit" name="confirmarPago" id="confirmarPago" value="Confirmar pago">
        </form>

        <!-- Boton de comprar mas -->
        <img src="images/cd.png" alt="CD" id="CD">
        <form name="caja" method="post" action="controlador">
            <input type="submit" name="inicio" value="Comprar mas">
        </form>
    </body>

</html>
