<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
    <head>
        <title>Orden de Compra</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <link href="<c:url value="webapp/resources/theme1/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/EstilosOrden.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/main.css" />" rel="stylesheet" />
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

                <form:form method="post" modelAttribute="ordencompra">

                    <div class="card-header" id="cardheader">
                        <br>
                        <center><h2><strong>Orden de Compra</strong></h2></center>

                        <div class="d-flex flex-row">

                            <div  class="p-4" id="img">

                                <img src="webapp/resources/theme1/images/logo2.png">

                            </div>

                            <div class="p-4"  id="escritos">
                                <p>Cal. Mercaderes Nro. 164 Dpto. 203 </p>
                                <p>Urb. Las Gardenias (Alt. Cuadra 50 de la Avenida Benavides)</p>

                            </div>

                            <div class="p-4" id="fieldset">

                                <fieldset style="border:1px solid black" id="fld" >

                                    <h5><strong> R.U.C. N° 20111807958 </strong></h5>
                                    <div class="d-flex flex-row" id="ngui">
                                        <div class="p-3">
                                            N° 
                                        </div>
                                        <div class="p-3">
                                            <input name="idCotizacion">
                                            <form:hidden path="idOrdenCompra" cssClass="form-control"/>
                                        </div>
                                        <div class="p-3">
                                            -&nbsp;0000041
                                        </div>
                                    </div>

                                </fieldset>
                            </div>
                        </div>
                    </div>

                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-4" >
                                <label for="fechaEmision"><strong>Fecha:</strong></label>
                                <form:input path="fechaEmision" cssClass="form-control" />
                            </div>
                            <div class="col-sm-4" >
                                <label for="idProveedor"><strong>Proveedor:</strong></label>
                                <form:select path="idProveedor.idProveedor" id="idProveedor">
                                    <c:forEach items="${proveedor}" var="x">
                                        <option value="${x.idProveedor}">${x.razonSocial}</option>
                                    </c:forEach>
                                </form:select>
                            </div>
                            <div class="col-sm-4" >

                                <label><strong>R.U.C:</strong></label>
                                <input type="text" name="ruc" value="${x.ruc}">

                            </div>

                        </div>
                        <br>

                        <div class="row">
                            <div class="col-sm-4" >
                                <label for="descripcion"><strong>Descripción:</strong></label>
                                <input type="text" name="descripcion"  id="descripcion"/>
                            </div>
                            <div class="col-sm-4" >

                                <label for="cantidad"><strong>Cantidad:</strong></label>
                                <input type="text" name="cantidad" id="cantidad">

                            </div>
                            <div class="col-sm-4" >

                                <button id="adicionar" class="btn btn-success" type="button">Agregar</button>
                            </div>

                        </div>

                        <p>Elementos en la Tabla:
                        <div id="adicionados"></div>
                        </p>

                        <hr style="border: solid gray 1px;">
                        <table class="table" style="width: 100%;" id="mytable">
                            <thead class="thead-dark">
                                <tr>
                                    <th style="text-align: center;">Cantidad</th>
                                    <th style="text-align: center;">Descripción</th>
                                </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>

                        <hr style="border: solid gray 1px;">

                        <center>
                            <input type="submit" class="btn btn-dark"value="Guardar"/>
                            <a class="btn btn-dark" href="menu.htm" role="button">Regresar al Menú</a> 
                        </center>

                    </div>
                </form:form>


            </div>

        </div>

        <script>
            $(document).ready(function () {
                //obtenemos el valor de los input

                $('#adicionar').click(function () {
                    var nombre = document.getElementById("descripcion").value;
                    var apellido = document.getElementById("cantidad").value;
                    var i = 1; //contador para asignar id al boton que borrara la fila
                    var fila = '<tr id="row' + i + '"><td>' + nombre + '</td><td>' + apellido + '</td><td>' + cedula + '</td><td><button type="button" name="remove" id="' + i + '" class="btn btn-danger btn_remove">Quitar</button></td></tr>'; //esto seria lo que contendria la fila

                    i++;

                    $('#mytable tr:first').after(fila);
                    $("#adicionados").text(""); //esta instruccion limpia el div adicioandos para que no se vayan acumulando
                    var nFilas = $("#mytable tr").length;
                    $("#adicionados").append(nFilas - 1);
                    //le resto 1 para no contar la fila del header
                    document.getElementById("apellido").value = "";
                    document.getElementById("cedula").value = "";
                    document.getElementById("nombre").value = "";
                    document.getElementById("nombre").focus();
                });
                $(document).on('click', '.btn_remove', function () {
                    var button_id = $(this).attr("id");
                    //cuando da click obtenemos el id del boton
                    $('#row' + button_id + '').remove(); //borra la fila
                    //limpia el para que vuelva a contar las filas de la tabla
                    $("#adicionados").text("");
                    var nFilas = $("#mytable tr").length;
                    $("#adicionados").append(nFilas - 1);
                });
            });
        </script>

        <br>
        <br>

    </body>
</html>


