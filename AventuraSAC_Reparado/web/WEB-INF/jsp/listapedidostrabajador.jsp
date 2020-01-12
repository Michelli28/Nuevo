<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="webapp/resources/theme1/css/main.css"/>" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/fonts/font.awesome.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="../../webapp/resources/theme1/css/estilolistapedido.css" rel="stylesheet" type="text/css"/>
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

                <div class="card-header" style="background: #C1FCEC ;">


                    <div class="p-4" style="font-family: Segoe UI">
                        <h2><center>Lista de Pedidos</center></h2>
                    </div>


                </div>
                <br>
                <div class="card-body">


                    <table class="table">

                        <thead class="thead-dark" > 
                            <tr>
                                <th scope="col" style="text-align: center">IdPedido</th>
                                <th scope="col" style="text-align: center">IdCliente</th>
                                <th scope="col" style="text-align: center">Fecha Registro</th>
                                <th scope="col" style="text-align: center">Fecha Entrega</th>
                                <th scope="col" style="text-align: center">Acciones</th>

                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach var="item" items="${pedidos}">
                                <tr> 
                                    <th scope="row" style="text-align: center">${item.idPedido}</th>
                                    <td style="text-align: center">${item.idCliente.idCliente}</td>
                                    <td style="text-align: center">${item.fechaRegistro}</td>
                                    <td style="text-align: center">${item.fechaEntrega}</td>
                                    <td style="text-align: center">
                                        <a class="btn btn-info" role="button" href="Cotizacion.htm?idPedido=${item.idPedido}" >Generar Cotización <i class="fas fa-edit"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>

                    <button><a href="menu.htm" id="regre">Regresar al Menú</a></button>


                </div>

            </div>
        </div>
    </body>

</html>
