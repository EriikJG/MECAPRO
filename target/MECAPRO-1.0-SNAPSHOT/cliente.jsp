<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="style.css">

</head>
<body>
<h1>Agregar Usuario</h1>
<p>Este es el apartado para añadir el Propietario del Automovil</p>

<form class="user" action="SvUsuario" method="POST">

    <input type="text" class="form-control form-control-user" id="cedula" name="cedula"
           placeholder="cedula">

    <input type="text" class="form-control form-control-user" id="nombre" name="nombre"
           placeholder="nombre">

    <input type="text" class="form-control form-control-user" id="apellido" name="apellido"
           placeholder="apellido">

    <input type="email" class="form-control form-control-user" id="correo" name="correo"
           placeholder="correo">

    <input type="tel" id="telefono" name="telefono" pattern="09[0-9]{8}" required placeholder="telefono">

    <input type="text" class="form-control form-control-user" id="direccion" name="direccion"
           placeholder="Direccion">

    <button class="agregarUsuario" type="submit">
        Agregar Usuario
    </button>

</form>
</body>
<a href="inicio.jsp" class="agregarUsuario">
    Cancelar
</a>
