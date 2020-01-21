<%-- 
    Document   : RegistrarPago
    Created on : 15/01/2020, 10:54:41 AM
    Author     : Administrador
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Orden de Compra</title>
        <link href="<c:url value="webapp/resources/theme1/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/EstilosRegistrarPago.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/main.css" />" rel="stylesheet" />
        <script src="<c:url value="webapp/resources/theme1/js/jquery.min.js" />"></script>

    </head>
    <body id="body">

        <header id="header">
            <a class="logo" href="menu.htm">Aventura S.A.C.</a>
            <nav>
                <a href="login.htm">Salir</a>
            </nav>
        </header>

        <div class="container md-8">

 
                <div class="card" id="carta">

                    <div class="card-header" id="cardheader">
                        <br>
                        <center><h1>Registrar Voucher </h1></center>
                        <br>
                    </div>

                    <div class="card-body">

                        <p style="text-align: left;">Ingresar los datos del comprobante de su deposito</p>    

                        <fieldset style="border: 1px solid gray; width:40%; height: 50%; margin: 0% 30%; padding: 3% 4% 2% 0%;"  >
                            <div class="form-group row">
                                <div class="col-sm-5" style="left:15%;">
                                    <label for="numeroOperacion">N° de Operación:</label>
                                </div>
                                <div class="col-sm-6" style="left:7%; width: 100%;" >
                                    <input type="text" name="numeroOperacion" id="numeroOperacion" cssClass="form-control"  placeholder="Ingrese numero"/>
                                </div>
                            </div>
                             <input type="hidden" name="idPedido" id="idPedido" value="${idPedido}">

                            <div class="form-group row">
                                <div class="col-sm-5" style="left:15%;">
                                    <label>Fecha:</label>
                                </div>
                                <div class="col-sm-6" style="left:7%;">
                                    <input type="text" name="fecha" id="fecha" cssClass="form-control" placeholder="Ingrese fecha">
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-5" style="left:15%;">
                                    <label>Monto:</label>
                                </div>
                                <div class="col-sm-6" style="left:7%;">
                                    <input type="text" name="monto" id="monto" cssClass="form-control" placeholder="Ingrese monto">
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-5" style="left:15%;">
                                    <label>Entidad Bancaria:</label>
                                </div>
                                <div class="col-sm-6" style="left:7%;">
                                    <input type="text" name="banco" id="banco" cssClass="form-control" placeholder="Ingrese banco">
                                </div>
                            </div>

                        </fieldset>

                        <hr style="border: solid gray 1px;">

                        <center>
                            <input type="button" class="btn btn-dark" value="Enviar" onclick="Enviar()"/>
                            <a class="btn btn-dark" id="a" href="menucliente.htm" style="text-decoration: none;" role="button">Regresar al Menú</a> 
                        </center>

                        <input type="hidden" name="idEstado" id="idEstado" value="4">
                    </div>
                </div>
 

        </div>

        <br>
        <br>

        <script>

            function Enviar() {


                //alert(fichas);
                $.ajax({
                    type: 'POST',
                    url: 'Registrar.htm',
                    data: {

                        'numeroOperacion': $("#numeroOperacion").val(),
                        'idPedido': $("#idPedido").val(),
                        'fecha': $("#fecha").val(),
                        'monto': $("#monto").val(),
                        'banco': $("#banco").val(),
                        'idEstado': $("#idEstado").val()

                    },
                    success: function (data) {
                        window.location.href = 'listapedidos.htm';
                    }
                });
            }





        </script>
    </body>
</html>
