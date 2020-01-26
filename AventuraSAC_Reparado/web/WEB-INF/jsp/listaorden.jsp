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
        <link href="<c:url value="webapp/resources/theme1/css/EstilosOrden.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/main.css" />" rel="stylesheet" />
        <script src="<c:url value="webapp/resources/theme1/js/jquery.min.js" />"></script>
        <link href="<c:url value="webapp/resources/theme1/fonts/font.awesome.css" />" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body id="bodys">
        <header id="header">
            <a class="logo" href="menu.htm">Aventura S.A.C.</a>
            <nav>
                <a href="login.htm">Salir</a>
            </nav>
        </header>
        
         <div class="container md-8">

            <div class="card" id="carta">
                <div class="card-header">
                    <h2 style="font-family: Segou IU; text-align: center;" >Listado de Ordenes de Compra</h2>
                </div>
                <div class="card-body">
                    <a class="btn btn-primary" href="nuevocliente.htm" role="button" id="nuevo">Nuevo</a>
                    <table class="table">
                        <thead>
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
                                     

                                        <a class="btn btn-info" role="button" href="verorden.htm?id=${item.idOrdenCompra}" ><i class="fas fa-edit"></i></a> 
                                        
                                        <button  class="btn btn-warning" type="button" onclick="eliminar('${item.idOrdenCompra}')" ><i class="fas fa-trash-alt"></i></button>
                                    </td>

                                    

                                </tr>
                            </c:forEach>
                                
                        
                        
                        </tbody>
                    </table>
                    
                    <center>
                        <button><a href="menulogistica.htm" id="regre">Regresar al Menú</a></button>
                    </center>

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
