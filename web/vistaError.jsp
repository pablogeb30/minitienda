<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="nombre" scope="session" type="java.lang.String"/>
<jsp:useBean id="correo" scope="session" type="java.lang.String"/>

<!DOCTYPE HTML>

<html lang="es">

    <!-- Cabecera -->
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/final.css">
        <title>Error al iniciar sesion</title>
    </head>

    <!-- Cuerpo -->
    <body>
        <!-- Titulo -->
        <div class="titulo">
            <img src="images/cross.png" alt="Cross">
            <h1>Error al iniciar sesion</h1>
            <img src="images/cross.png" alt="Cross">
        </div>

        <!-- Informacion -->
        <div class="info">
            <p>Usuario: <b>${nombre}</b></p>
            <p>Correo electronico: <b>${correo}</b></p>
            <p>Error: <b>Inicio de sesión o registro fallido.</b></p>
        </div>

        <!-- Ir a la caja -->
        <img src="images/pagar.png" alt="Pagar" id="pagar">
        <form name="irCaja" method="post" action="controlador">
            <input type="submit" name="irCaja" value="Ir a la caja">
        </form>
    </body>

</html>
