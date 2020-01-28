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

                <div class="card-header" style="background-color:rgba(20,100,30, 0.4);">


                    <div class="p-4" style="font-family: Segoe UI; text-align: center">
                        <h2 style="font-family: cambria; font-size: 35px;">Lista de Pedidos</h2>
                    </div>


                </div>
                
                <div class="card-body">


                    <table class="table">

                        <thead class="thead-dark" > 
                            <tr>
                                <th scope="col" style="text-align: center">IdPedido</th>
                                <th scope="col" style="text-align: center">Razón Social</th>
                                <th scope="col" style="text-align: center">Fecha Registro</th>
                                <th scope="col" style="text-align: center">Fecha Entrega</th>
                                <th scope="col" style="text-align: center">Acciones</th>

                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach var="item" items="${pedidos}">
                                <tr> 
                                    <th scope="row" style="text-align: center">${item.idPedido}</th>
                                    <td style="text-align: center">${item.idCliente.razonSocial}</td>
                                    <td style="text-align: center">${item.fechaRegistro}</td>
                                    <td style="text-align: center">${item.fechaEntrega}</td>
                                 
                                        <c:if test="${item.cotizacionList.size() == 0}">
                                        <td style="text-align: center;"><a class="btn btn-info" role="button" href="Cotizacion.htm?idPedido=${item.idPedido}" >Generar Cotización <i class="fas fa-edit"></i></a></td>
                                        </c:if>
                                            <c:if test="${item.cotizacionList.size() != 0 && item.idEstado.idEstado == 1 || 
                                                          item.idEstado.idEstado == 2 || 
                                                          item.idEstado.idEstado == 3 ||
                                                          item.idEstado.idEstado == 4 || 
                                                          item.idEstado.idEstado == 5 ||
                                                          item.idEstado.idEstado == 7
                                                  }">
                                            <td></td>
                                        </c:if>
                                             <c:if test="${item.cotizacionList.size() != 0 && item.idEstado.idEstado == 6}">
                                            <td style="text-align: center;"><a class="btn btn-info" role="button" href="Factura.htm?idPedido=${item.idPedido}" >Facturar<i class="fas fa-edit"></i></a></td>
                                        </c:if>
                                             
                                  
                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>
                    <div style="text-align: center">
                    <a href="menuventas.htm" id="regre" class="btn btn-secondary" style="height: 50px; width: 250px; padding: 10px; font-size: 17px">Regresar al Menú</a>
                    </div>


                </div>

            </div>
            </div>
            <br>
            <br>
        </div>
    </body>

</html>
