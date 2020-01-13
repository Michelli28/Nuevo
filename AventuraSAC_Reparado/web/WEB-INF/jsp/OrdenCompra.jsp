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
                        <hr style="border: solid gray 1px;">

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
                                <br>
                                <button id="adicionar" class="btn btn-success" type="button" style="margin: 15px 0px; width: 200px" onclick="guardar()">Agregar</button>
                            </div>

                        </div>

                        <p>Elementos en la Tabla:
                        <div id="adicionados"></div>
                        </p>


                        <table class="table" style="width: 100%;" id="mytable">
                            <thead class="thead-dark">
                                <tr>
                                    <th style="text-align: center;">Descripción</th>
                                    <th style="text-align: center;">Cantidad</th>
                                    <th style="text-align: center;">Accion</th>

                                </tr>
                            </thead>
                            <tbody id="tablita">

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



        <br>
        <br>

        <script>

            function guardar() {

                var _nom = document.getElementById("descripcion").value;
                var _ape = document.getElementById("cantidad").value;
                var i = 1;

                var fila = "<tr><td style='text-align: center;'>" + _nom + "</td><td style='text-align: center;'>" + _ape + "</td><td><button type='button' name='remove' id='" + i + "' class='btn btn-danger btn_remove' onclick='remove()' >Quitar</button></td></tr>";
                i++;
                 $("#adicionados").text(""); //esta instruccion limpia el div adicioandos para que no se vayan acumulando
                var btn = document.createElement("mytable TR");
                btn.innerHTML = fila;
                 $("#adicionados").append(btn - 1);
                document.getElementById("tablita").appendChild(btn);
                document.getElementById("cantidad").value = "";
                document.getElementById("descripcion").value = "";
                document.getElementById("descripcion").focus();

                function remove() {
                    var button_id = $(this).attr("id");
                    //cuando da click obtenemos el id del boton
                    $('#row' + button_id + '').remove(); //borra la fila
                    //limpia el para que vuelva a contar las filas de la tabla
                    $("#adicionados").text("");
                    var btn = document.createElement("TR");
                    $("#adicionados").append(btn - 1);
                }

            }


        </script>

    </body>
</html>


