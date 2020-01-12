<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="<c:url value="webapp/resources/theme1/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/crud.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/main.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/fonts/font.awesome.css" />" rel="stylesheet">

    </head>
    <body id="bodys">
        <header id="header">
            <a class="logo" href="MenuPrincipalTrabajador.html">Aventura S.A.C.</a>
            <nav>
                <a href="login.htm">Salir</a>
            </nav>
        </header>
        <div class="container md-8">
            <div class="card" id="carta">
                <div class="card-header">
                    <h3>Listado de Empleados</h3>
                </div>
                <div class="card-body">
                    <a class="btn btn-primary" href="nuevoempleado.htm" role="button" id="nuevo">Nuevo</a>
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">DNI</th>
                                <th scope="col">Nombre</th>
                                <th scope="col">Apellido Paterno</th>
                                <th scope="col">Apellido Materno</th>
                                <th scope="col">Cargo</th>
                                <th scope="col">Área</th>
                                <th scope="col">Teléfono</th>
                                <th scope="col">Acciones</th>

                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${empleados}">
                                <tr>
                                    <th scope="row">${item.idEmpleado}</th>
                                    <td>${item.dni}</td>
                                    <td>${item.nombre}</td>
                                    <td>${item.apellidoPaterno}</td>
                                    <td>${item.apellidoMaterno}</td>
                                    <td>${item.idArea.detalle}</td>
                                    <td>${item.idCargo.detalle}</td>
                                    <td>${item.telefono}</td>
                                    <td><a href="editarempleado.htm?id=${item.idEmpleado}" class="btn btn-info" role="button"><i class="fas fa-edit"></i></a> 
                                        <button type="button" onclick="eliminar('${item.idEmpleado}')" class="btn btn-warning"><i class="fas fa-trash-alt"></i></button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <center>
                        <button><a href="menu.htm" id="regre">Regresar al Menú</a></button>
                    </center>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            function eliminar(id) {
                if (confirm("¿Desea eliminar al empleado?")) {
                    window.location.href = "eliminarempleado.htm?id=" + id;
                    return true;
                }
                return false;
            }
        </script>
    </body>
</html>

