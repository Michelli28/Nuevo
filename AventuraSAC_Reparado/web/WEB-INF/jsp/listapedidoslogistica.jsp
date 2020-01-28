
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
        <link href="webapp/resources/theme1/css/estilolistapedido.css" rel="stylesheet" type="text/css"/>
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
            <div style="background-color:#138496 ;">
            <div class="card" id="carta" style="margin-left:10px;">

                <div class="card-header" style="background-color: rgba(75, 156, 227, 0.5) ;"> 
                    <br>

                    <h2 style="font-family: cambria; font-size: 35px;"><center>Lista de Pedidos</center></h2>

                </div>

                <div class="card-body">

                    <table class="table" style="width: 100%; height: 100%;">

                        <thead style="background-color: #D6D9D9 !important;">
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

                                    <c:if test="${item.cotizacionList.size() != 0 && item.idEstado.idEstado == 2}">
                                        <td style="text-align: center;"><a class="btn btn-info" role="button" href="vercotizacion.htm?cotizacionList.get(0)=${item.cotizacionList.get(0).idCotizacion}" style="text-decoration: none;">Ver Cotización <i class="fas fa-edit"></i></a></td>
                                            </c:if>
                                            <c:if test="${item.cotizacionList.size() == 0}">
                                        <td></td>
                                    </c:if>
                                    <c:if test="${item.cotizacionList.size() != 0 && item.idEstado.idEstado == 3}">
                                        <td style="text-align: center;"><a class="btn btn-info" role="button" href="RegistrarPago.htm?idPedido=${item.idPedido}" style="text-decoration: none;">Ir a pago<i class="fas fa-edit"></i></a></td>
                                            </c:if>
                                            <c:if test="${item.cotizacionList.size() != 0 && item.idEstado.idEstado == 1}">
                                        <td style="text-align: center;"><a class="btn btn-info" role="button" href="vercotizacion.htm?cotizacionList.get(0)=${item.cotizacionList.get(0).idCotizacion}" style="text-decoration: none;">Ver Cotización <i class="fas fa-edit"></i></a></td>
                                            </c:if>
                                            <c:if test="${item.idEstado.idEstado == 4}">
                                        <td style="text-align: center;"><a class="btn btn-info" role="button" href="verPedido.htm?idPedido=${item.idPedido}" style="text-decoration: none;">Ver Pedido<i class="fas fa-edit"></i></a></td>
                                    </c:if>
                                        <c:if test="${item.idEstado.idEstado == 7}">
                                        <td ></td>
                                    </c:if>
                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>

                    <center>
                        <a class="btn btn-dark" href="menulogistica.htm" role="button" style="text-decoration: none; width: 200px; height: 50px; padding: 13px; margin: 0px 10px">Regresar al Menú</a> 

                    </center>

                </div>

            </div>
            </div>
        </div>
        <br>
    </body>

</html>
