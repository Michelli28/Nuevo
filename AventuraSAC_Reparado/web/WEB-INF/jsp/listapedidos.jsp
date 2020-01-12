
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="webapp/resources/theme1/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/main.css"/>" rel="stylesheet">
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
                                    <c:forEach var="" items="">
                                    <th style="text-align: center;">Acciones</th>
                                    </c:forEach>
                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach var="item" items="${pedidos}">
                                <tr> 
                                    <th scope="row" style="text-align: center;">${item.idPedido}</th>

                                    <td style="text-align: center;">${item.fechaRegistro}</td>
                                    <td style="text-align: center;">${item.fechaEntrega}</td>
                                    <td style="text-align: center;">${item.estado}</td>
                                    <c:forEach var="x" items="${cotiz}">
                                        <c:if test="${x.idPedido == pedidos.idPedido.idPedido}">
                                            <td style="text-align: center;"><a class="btn btn-info" role="button" href="Cotizacion.htm?idPedido=${item.idPedido}" >Ver Cotización <i class="fas fa-edit"></i></a></td>
                                        </c:if>
                                    </c:forEach>
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
