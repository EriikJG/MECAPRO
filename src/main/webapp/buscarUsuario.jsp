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
<h1>Ingrese la cedula del usuario que quiere agregar el vehiculo</h1>
<form class="user" action="SvEncontrarUsuario" method="POST">

            <input type="text" class="form-control form-control-user" id="cedula" name="cedula" required
                   placeholder="cedula">
       <button class="btn btn-primary btn-user btn-block" type="submit">
         Buscar Usuario
    </button>

</form>
</body>
<a href="inicio.jsp" class="agregarUsuario">
    Cancelar
</a>




