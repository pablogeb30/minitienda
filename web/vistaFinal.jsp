<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="nombre" scope="session" type="java.lang.String"/>
<jsp:useBean id="correo" scope="session" type="java.lang.String"/>
<jsp:useBean id="importeTotal" scope="session" type="java.lang.Float"/>

<!DOCTYPE HTML>

<html lang="es">

    <!-- Cabecera -->
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/final.css">
        <title>Compra finalizada</title>
    </head>

    <!-- Cuerpo -->
    <body>
        <!-- Titulo -->
        <div class="titulo">
            <img src="images/check.png" alt="Check">
            <h1>Compra finalizada</h1>
            <img src="images/check.png" alt="Check">
        </div>

        <!-- Informacion -->
        <div class="info">
            <p>Usuario: <b>${nombre}</b></p>
            <p>Correo electronico: <b>${correo}</b></p>
            <p>Importe total: <b>${importeTotal}</b></p>
        </div>

        <!-- Boton de comprar mas -->
        <img src="images/cd.png" alt="CD" id="CD">
        <form name="comprarMas" method="post" action="controlador">
            <input type="submit" name="inicio" value="Comprar mas">
        </form>
    </body>

</html>
