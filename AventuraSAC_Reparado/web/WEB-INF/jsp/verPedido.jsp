<%-- 
    Document   : nuevopedido
    Created on : 12/01/2020, 08:40:39 AM
    Author     : CHELLI BONITA
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link href="<c:url value="webapp/resources/theme1/css/estilospedido.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/main.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/fonts/font.awesome.css" />" rel="stylesheet">
        <script src="<c:url value="webapp/resources/theme1/js/jquery.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="webapp/resources/theme1/js/jquery.modal.js"/>"></script>
        <link type="text/css" rel="stylesheet" href="<c:url value="webapp/resources/theme1/css/jquery.modal.css"/>" />

    </head>
    <body id="body"  style="background-color:rgba(100,0,0, 0.2);">

        <header id="header">
            <a class="logo" href="menu.htm">Aventura S.A.C.</a>
            <nav>
                <a href="login.htm">Salir</a>
            </nav>
        </header>

        <div class="container md-8">
            <div style="background-color:rgba(100,0,0, 0.4);">
            <div class="card" id="carta" style="margin-left:10px;">

                <div class="card-header" id="cardheader" style="background-color:rgba(100,0,0, 0.4);">
                    <div class="d-flex flex-row">
                        <div class="p-4">
                            <div class="d-flex flex-row" >
                                <div class="p-6" style="width: 120px;">
                                    <h4><strong>N° de Pedido:</strong></h4>
                                </div>
                                <div class="p-6">
                                    <input style="width: 50px; height: 25px;" type="text" value="${pedido.idPedido}" readonly="readonly">
                                </div>
                            </div>
                        </div>
                        <div class="p-4">
                            <div class="d-flex flex-row" class="form-group" style="margin-left: 130px ">
                                <div class="p-6" class="form-group" style="width: 120px;">
                                    <label for="fechaRegistro" >Fecha Emisión: </label>
                                </div>
                                <div class="p-6" class="form-group">
                                    <label name="fechaRegistro" >${pedido.fechaRegistro}</label>
                                </div>
                            </div>
                        </div>
                        <div class="p-4">
                            <div class="d-flex flex-row" style="margin-left: 130px ">
                                <div class="p-6" class="form-group" style="width: 110px;">
                                    <label for="fechaRegistro">Fecha Entrega:</label>
                                </div>
                                <div class="p-6" class="form-group">
                                    <label name="fechaEntrega" >${pedido.fechaEntrega}</label>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>

                <div class="card-body" >
                   
                    <c:forEach var="item1" items="${cliente}">
                        <legend><h3 style="font-family: Segeo UI; font-size: 20px; text-align: center;"><strong>Datos del Cliente</strong></h3></legend>
                        <fieldset id="fieldset1" style="border: 1px solid gray" >

                            <div class="row" id="fila1" style="margin-top: 15px;">
                                <div class="col-sm-4" >
                                    <div class="d-flex flex-row" style="margin: 0px 25px;">
                                        <div class="p-6">
                                            <label for="">Razón Social:</label>
                                        </div>&nbsp;
                                        <div class="p-6">
                                            <label style="font-size: 14px; text-align: center;">${item1.razonSocial}</label> 
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-4">      
                                    <div class="d-flex flex-row">
                                        <div class="p-6">
                                            <label for="">R.U.C.:</label>
                                        </div>&nbsp;
                                        <div class="p-6">
                                            <label style="font-size: 14px;text-align: center;">${item1.ruc}</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="d-flex flex-row">
                                        <div class="p-6">
                                            <label for="">Dirección:</label>
                                        </div>&nbsp;
                                        <div class="p-6">
                                            <label style="font-size: 14px;text-align: center;">${item1.direccion}</label>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row" id="fila2">

                                <div class="col-sm-4">
                                    <div class="d-flex flex-row" style="margin: 0px 25px;">
                                        <div class="p-6">
                                            <label for="">Teléfono:</label>
                                        </div>&nbsp;
                                        <div class="p-6">
                                            <label style="font-size: 14px;text-align: center;">${item1.telefono}</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-4">      
                                    <div class="d-flex flex-row">
                                        <div class="p-6">
                                            <label for="">Correo:</label>
                                        </div>&nbsp;
                                        <div class="p-6">
                                            <label style="font-size: 14px;text-align: center;">${item1.correo}</label>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </fieldset>
                    </c:forEach>
                    <br>
                    <table class="table">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col" style="text-align: center;">IdFicha</th>
                                <th scope="col" style="text-align: center;">Descripcion</th>
                                <th scope="col" style="text-align: center;">Cantidad</th>
                                <th scope="col" style="text-align: center;">Estado</th>
                                <th scope="col" style="text-align: center;">Acciones</th>
                            </tr>
                        </thead>
                        <tbody style="background: #F0E9FF;">
                            <c:forEach var="item" items="${ficha}">
                                <tr>
                                    <th scope="row" style="text-align: center;">${item.idDetallePedido}</th>
                                    <th style="text-align: center;">${item.idFicha.descripcion}</th>
                                    <th style="text-align: center;">${item.idFicha.cantidad}</th>
                                    <th style="text-align: center;">${item.idFicha.idEstado.nombre}</th>
                                    <td scope="col-2" style="text-align: center;">
                                        <a class="btn btn-warning" role="button" style="height: 50%; text-decoration: none;" href="verficha.htm?id=${item.idFicha.idFicha}" >Ver Ficha <i class="fas fa-edit"></i></a> 
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>


                    <input name="idEstado.idEstado" id="fentregaI" type="hidden" value="1"/>
                    <br>
                    <center>
                      
                        <a class="btn btn-dark" style="text-decoration: none; width: 200px; height: 50px; padding: 13px; margin: 0px 10px" href="menulogistica.htm" role="button">Regresar al Menú</a> 
                        <a class="btn btn-info" style="text-decoration: none; width: 200px; height: 50px; padding: 13px; margin: 0px 10px" href="OrdenCompra.htm?idPedido=${pedido.idPedido}" role="button">Generar Orden Compra</a> 
                    </center>
                </div>

            </div>  
</div>
        </div>
                    <br>
    <script type="text/javascript">
        function eliminar(id) {
            if (confirm("¿Desea eliminar la ficha técnica?")) {
                window.location.href = "eliminarficha.htm?id=" + id;
                return true;
            }
            return false;
        }
    </script>
</body>
</html>