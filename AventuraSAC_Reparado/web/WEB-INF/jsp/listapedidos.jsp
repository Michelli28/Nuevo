
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>


<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <link href="<c:url value="webapp/resources/theme1/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/main.css"/>" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/fonts/font.awesome.css" />" rel="stylesheet">
        <link href="../../webapp/resources/theme1/css/estilolistapedido.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body id="body" style="background: #adb5bd;">

        <header id="header">
            <a class="logo" href="menu.htm">Aventura S.A.C.</a>
            <nav>
                <a href="login.htm">Salir</a>
            </nav>
        </header>

        <div class="container md-8">
            <br>
            <div class="card" id="carta">

                <div class="card-header" style="background: #C1FCEC ;"> 
                    <br>

                    <h2><center>Lista de Pedidos</center></h2>

                </div>
                <br>
                <div class="card-body">

                    <table class="table" style="width: 100%; height: 100%;">

                        <thead class="thead-dark">
                            <tr>
                                <th style="text-align: center;">IdPedido</th>
                                <th style="text-align: center;">Fecha Registro</th>
                                <th style="text-align: center;">Fecha Entrega</th>
                                <th style="text-align: center;">Estado</th>
                                <th style="text-align: center;">Acciones</th>

                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach var="item" items="${pedidos}">
                                <tr> 
                                    <th scope="row" style="text-align: center;">${item.idPedido}</th>
                                    <td style="text-align: center;">${item.fechaRegistro}</td>
                                    <td style="text-align: center;">${item.fechaEntrega}</td>
                                    <td style="text-align: center;">${item.idEstado.nombre}</td>

                                    <c:if test="${item.cotizacionList.size() != 0}">
                                        <td style="text-align: center;"><a class="btn btn-info" role="button" href="vercotizacion.htm?cotizacionList.get(0)=${item.cotizacionList.get(0).idCotizacion}" style="text-decoration: none;">Ver Cotización <i class="fas fa-edit"></i></a></td>
                                            </c:if>
                                        <c:if test="${item.cotizacionList.size() == 0}">
                                            <td></td>
                                            </c:if>

                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>

                    <center>
                        <a class="btn btn-dark" href="menucliente.htm" role="button">Regresar al Menú</a> 

                    </center>

                </div>

            </div>
        </div>
    </body>

</html>
