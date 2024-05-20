<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 19/5/2024
  Time: 1:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form class="user" action="SvReparacion" method="POST">


            <input type="text" class="form-control form-control-user" id="descripcion" name="descripcion"
                   placeholder="descripcion">


            <input type="text" class="form-control form-control-user" id="costo" name ="costo"
                   placeholder="costo">



    <button class="btn btn-primary btn-user btn-block" type="submit">
        Crear Registro
    </button>

</form>
<a href="inicio.jsp" class="agregarUsuario">
    Cancelar
</a>

</body>
</html>
