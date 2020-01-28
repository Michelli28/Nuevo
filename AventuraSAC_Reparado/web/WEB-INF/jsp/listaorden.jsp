<%-- 
    Document   : listaorden
    Created on : 24/01/2020, 10:23:49 PM
    Author     : CHELLI BONITA
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="webapp/resources/theme1/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/estilolistapedido.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/main.css" />" rel="stylesheet" />
        <script src="<c:url value="webapp/resources/theme1/js/jquery.min.js" />"></script>
        <link href="<c:url value="webapp/resources/theme1/fonts/font.awesome.css" />" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body id="bodys" style="background: #adb5bd;">
        <header id="header">
            <a class="logo" href="menu.htm">Aventura S.A.C.</a>
            <nav>
                <a href="login.htm">Salir</a>
            </nav>
        </header>

        <div class="container md-8">

            <div style="background-color:rgba(20,100,20, 0.7);">

                <div class="card" id="carta" style="margin-left:10px;">

                    <div class="card-header" style="background-color:rgba(20,100,30, 0.4);"> 

                        <h2 style="font-family: cambria; text-align: center; margin-top: 2%;" >Listado de Ordenes de Compra</h2>
                    </div>
                    <div class="card-body">
                        <table class="table">
                            <thead  style="background-color: #D6D9D9 !important;">
                                <tr>
                                    <th scope="col">IdOrden</th>
                                    <th scope="col">Empleado</th>
                                    <th scope="col">Proveedor</th>
                                    <th scope="col">Fecha Emitida</th>

                                    <th scope="col">Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="item" items="${orden}">
                                    <tr>
                                        <th scope="row" >${item.idOrdenCompra}</th>
                                        <td >${item.idEmpleado.nombre}</td>
                                        <td >${item.idProveedor.razonSocial}</td>
                                        <td >${item.fechaEmision}</td>

                                        <td scope="col-2">


                                            <button  class="btn btn-warning" type="button" style="height: 35px;" onclick="eliminar('${item.idOrdenCompra}')" ><i class="fas fa-trash-alt"></i></button>
                                        </td>



                                    </tr>
                                </c:forEach>



                            </tbody>
                        </table>

                        <center>
                            <a type="button" href="menulogistica.htm" id="regre" class="btn btn-secondary" style="text-decoration: none; width: 20%;">Regresar al Menú</a>
                        </center>

                    </div>


                </div>
            </div>
        </div>

        <script type="text/javascript">
            function eliminar(id) {
                if (confirm("¿Desea eliminar la orden de compra?")) {
                    window.location.href = "eliminarorden.htm?id=" + id;
                    return true;
                }
                return false;
            }
        </script>
    </body>
</html>
