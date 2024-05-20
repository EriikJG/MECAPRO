<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 18/5/2024
  Time: 22:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Agregar Automovil</h1>
<p>Este es el apartado para añadir el Automovil al sistema</p>

<form class="user" action="SvAutomovil" method="POST">

            <input type="text" class="form-control form-control-user" id="placa" name="placa"
                   placeholder="Placa">


            <input type="text" class="form-control form-control-user" id="marca" name ="marca"
                   placeholder="Marca">


            <input type="text" class="form-control form-control-user" id="anioFab" name="anioFab"
                   placeholder="Año de Fabricacion">

    <button class="btn btn-primary btn-user btn-block" type="submit">
        Agregar Automovil
    </button>


</form>
<a href="inicio.jsp" class="agregarUsuario">
    Cancelar
</a>
</body>
</html>
