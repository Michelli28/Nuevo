<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="webapp/resources/theme1/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/js/bootstrap.min.js" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/main.css" />" rel="stylesheet" />
        <title>JSP Page</title>
    </head>
    <body id="body">

        <header id="header">
            <a class="logo" href="menu.htm">Aventura S.A.C.</a>
            <nav>
                <a href="login.htm">Salir</a>
            </nav>
        </header>

        <div class="container md-8">
            <br>
            <div class="card" id="carta">
                <form:form method="post" modelAttribute="movimiento" >
                    <div class="card-header" id="cardheader">
                        <br>
                        <center><h2>Lista de Movimientos de Almacén</h2></center>
                        <br>
                    </div>

                    <div class="card-body">

                        <br>

                        <table class="table table-bordered" style="width: 100%;">
                            <thead style="background-color: #D6D9D9;">
                                <tr>
                                    <th style="text-align: center; width: 20%;">IdMovimiento</th>
                                    <th style="text-align: center; width: 20%;">Orden de Compra</th>
                                    <th style="text-align: center; width: 20%;">Tipo movimiento</th>
                                    <th style="text-align: center;">Descripción</th>
                                    <th style="text-align: center;">Fecha</th>
                                </tr>
                            </thead>
                            <tbody>

                                <c:forEach var="item" items="${movi}">
                                    <tr>
                                        <th scope="col" style="text-align: center;">${item.idMovimiento}</th>
                                        <td style="text-align: center;">${item.idOrdenCompra.idOrdenCompra}</td>
                                        <td style="text-align: center;">${item.tipoMovimiento}</td>
                                        <td style="text-align: center;">${item.descripcion}</td>
                                        <td style="text-align: center;">${item.fecha}</td>
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>

                        <hr style="border: solid gray 1px;">
                        <center>

                            <a class="btn btn-dark" href="menualmacen.htm" role="button">Regresar al Menú</a> 
                        </center>

                    </div>
                </form:form>


            </div>

        </div>

        <br>
        <br>

    </body>
</html>
