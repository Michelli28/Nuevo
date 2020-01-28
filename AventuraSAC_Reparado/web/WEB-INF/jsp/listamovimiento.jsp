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
        <link href="webapp/resources/theme1/css/estilolistapedido.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body id="body" style="background-color:rgba(20,100,20, 0.2);">

        <header id="header">
            <a class="logo" href="menu.htm">Aventura S.A.C.</a>
            <nav>
                <a href="login.htm">Salir</a>
            </nav>
        </header>

        <div class="container md-8">
            <br>
            <div style="background-color:rgba(20,100,20, 0.7);">
            <div class="card" id="carta" style="margin-left:10px;">
                <form:form method="post" modelAttribute="movimiento" >
                    <div class="card-header" id="cardheader" style="background-color:rgba(20,100,30, 0.4);">
                        <br>
                        <center><h2 style="font-family: cambria;">Lista de Movimientos de Almacén</h2></center>
                        <br>
                    </div>

                    <div class="card-body">


                        <table class="table table-bordered" style="width: 100%;">
                            <thead style="background-color: #D6D9D9 !important;">
                                <tr>
                                    <th style="text-align: center; width: 20%;">IdMovimiento</th>
                                    <th style="text-align: center; width: 20%;">Orden de Compra</th>
                                    <th style="text-align: center; width: 20%;">Tipo movimiento</th>
                                    <th style="text-align: center; width: 20%;">Tipo Item</th>
                                    <th style="text-align: center;">Fecha</th>
                                </tr>
                            </thead>
                            <tbody>

                                <c:forEach var="item" items="${movi}">
                                    <tr>
                                        <th scope="col" style="text-align: center;">${item.idMovimiento}</th>
                                        <td style="text-align: center;">${item.idOrdenCompra.idOrdenCompra}</td>
                                        <td style="text-align: center;">${item.tipoMovimiento}</td>
                                        <td style="text-align: center;">${item.idTipoItem.nombre}</td>
                                        <td style="text-align: center;">${item.fecha}</td>
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>

                        <hr style="border: solid gray 1px;">
                        <center>

                            <a class="btn btn-dark" href="menualmacen.htm" role="button" style="text-decoration: none; width: 20%; height: 45px; padding: 1%;">Regresar al Menú</a> 
                        </center>

                    </div>
                </form:form>


            </div>
            </div>
        </div>

        <br>
        <br>

    </body>
</html>
