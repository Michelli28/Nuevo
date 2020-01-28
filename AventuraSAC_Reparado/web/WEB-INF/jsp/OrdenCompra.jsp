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
        <script src="<c:url value="webapp/resources/theme1/js/jquery.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="webapp/resources/theme1/js/jquery.validate.min.js"/>"></script>
    </head>

    <body id="body" style="background-color:rgba(100,0,0, 0.2);">

        <header id="header" >
            <a class="logo" href="menu.htm">Aventura S.A.C.</a>
            <nav>
                <a href="login.htm">Salir</a>
            </nav>
        </header>

        <div class="container md-8">

            <div style="background-color:rgba(100,0,0, 0.4);">
                <div class="card" id="carta" style="margin-left:10px;">

                    <input type="hidden" name="idPedido" id="idPedido" value="${idPedido}">
                    <div class="card-header" id="cardheader" style="background-color:rgba(100,0,0, 0.4);">


                        <div class="d-flex flex-row">

                            <div  class="p-4" id="img">

                                <img src="webapp/resources/theme1/images/logo.PNG">

                            </div>

                            <div class="p-4"  id="escritos">
                                <p style="font-family: arial;">Cal. Mercaderes Nro. 164 Dpto. 203 </p>
                                <p style="font-family: arial;">Urb. Las Gardenias (Alt. Cuadra 50 de la Avenida Benavides)</p>

                            </div>

                            <div class="p-4" id="fieldset">

                                <fieldset style="border:1px solid black" id="fld" >

                                    <h5 style="font-family: arial;"><strong> R.U.C. N° 20111807958 </strong></h5>
                                    <h2 style="font-family: cambria; font-size: 23px;"><strong>Orden de Compra</strong></h2>
                                    <div class="d-flex flex-row" id="ngui">
                                        <div class="p-3" style="font-family: arial;">
                                            N° 
                                        </div>
                                        <div class="p-3">
                                            <input name="idCotizacion">
                                            <input type="hidden" name="idOrdenCompra"  cssClass="form-control"/>
                                        </div>
                                        <div class="p-3" style="font-family: arial;">
                                            -&nbsp;0000041
                                        </div>
                                    </div>

                                </fieldset>
                            </div>
                        </div>
                    </div>
                    <form id="formulario">
                        <div class="card-body">
                            <input type="hidden" name="idEmpleado" id="idEmpleado" value="${usuario.idEmpleado}"/>
                            <div class="row">
                                <div class="col-sm-4" style="left:10%;">
                                    <label for="idProveedor"><strong>Proveedor:</strong></label>
                                    <select name="idProveedor" id="idProveedor" style="width: 60%; ">
                                        <c:forEach items="${proveedor}" var="x">
                                            <option value="${x.idProveedor}" >${x.razonSocial}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-sm-4" style="left: 30%;" >
                                    <label for="fechaEmision"><strong>Fecha:</strong></label>
                                    <input type="text" name="fechaEmision" id="txtfechaactual" value="${fecha}" readonly="readonly" style="width: 60%; "/>
                                </div>
                            </div>
                           


                            <hr style="border: solid gray 1px;">

                            <div class="row" >
                                <div class="col-sm-4" style="left: 10%;">
                                    <label for="descripcion"><strong>Descripción:</strong></label>
                                    <input type="text" name="descripcion"  id="descripcion" style="width: 70%; "/>
                                </div>
                                <div class="col-sm-4" style="left: 5%;" >

                                    <label for="cantidad"><strong>Cantidad:</strong></label>
                                    <input type="text" name="cantidad" id="cantidad" style="width: 50%; ">

                                </div>
                                <div class="col-sm-4" style="left: 0%;" >
                                    <br>
                                    <button id="btn" class="btn btn-success" type="button" style="margin: 15px 0px; width: 200px" onclick="guardar()">Agregar</button>
                                </div>

                            </div>
                            <br>
                            <center>
                                <table class="table" style="width: 80%;" id="table">
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
                            </center>
                            <hr style="border: solid gray 1px;">

                            <center>
                                <button type="button" class="btn btn-light"  onclick="Enviar()" style="width: 200px; height: 50px; ">Guardar</button>
                                <a class="btn btn-dark" href="menu.htm" role="button" style="text-decoration: none; width: 200px; height: 50px; padding: 13px; margin: 0px 10px;">Regresar al Menú</a> 
                            </center>

                        </div>
                    </form>


                </div>
            </div>
        </div>

        <br>
        <br>

        <script>

            function guardar() {

                var _nom = document.getElementById("descripcion").value;
                var _ape = document.getElementById("cantidad").value;
                var i = 1;
                var fila = '<tr id="row" ' + i + '><td style="text-align: center;">' + _nom + '</td><td style="text-align: center;">' + _ape + '</td><td style="text-align:center;"><button type="button" id="' + i + '" class="btn btn-danger btn_remove" onclick="remove(this)" >Quitar</button></td></tr>';



                i = i + 1;
                var btn = document.createElement("TR");
                btn.innerHTML = fila;
                document.getElementById("tablita").appendChild(btn);
                document.getElementById("cantidad").value = "";
                document.getElementById("descripcion").value = "";
                document.getElementById("descripcion").focus();
            }


            function remove(t) {

                var td = t.parentNode;
                var tr = td.parentNode;
                var table = tr.parentNode;
                table.removeChild(tr);
            }




        </script>
        <script>
            function Enviar() {
                var detalles = obtenerDetalles();

                // alert($("#idProveedor").val());

                $.ajax({
                    type: 'POST',
                    url: 'generarorden.htm',
                    data: {
                        'idPedido': $("#idPedido").val(),
                        'idEmpleado': $("#idEmpleado").val(),
                        'idProveedor': $("#idProveedor").val(),
                        'fechaEmision': $("#txtfechaactual").val(),
                        'detalles': detalles

                    },
                    success: function (data) {
                        window.location.href = 'listaordenes.htm';
                    }
                });
            }

            function obtenerDetalles() {
                var cadena = '';
                // Recorrer las filas TR de la tabla
                $("#table tbody tr").each(function (i) {
                    // ID FICHA
                    cadena += $(this).find('td:eq(0)').text() + ",";
                    // ESTADO
                    cadena += $(this).find('td:eq(1)').text() + ";";
                    // SUBTOTAL
                    /* if ($(this).find('td:eq(3)').find("option:selected").val() === '2') {
                     cadena += '0;';
                     } else {
                     cadena += $(this).find("input").val() + ";";
                     }*/
                });
                return cadena.substring(0, cadena.length - 1);
            }
        </script>

        <script>
            $(function () {
                $("#btn").on("click", function () {
                    $("#formulario").validate({
                        rules:
                                {
                                    descripcion: {required: true, minlength: 5, maxlength: 50, alfanumOespacio: true},
                                    cantidad: {required: true, numbersonly: true}

                                },
                        messages:
                                {
                                    descripcion: {required: 'El campo es requerido', minlength: 'El mínimo permitido son 5 caracteres',
                                        maxlength: 'El máximo permitido son 50 caracteres', alfanumOespacio: 'Ingrese un nombre válido.'},
                                    cantidad: {required: 'El campo es requerido', numbersonly: 'Por favor, solo ingrese números'}
                                }
                    });
                });
            });
        </script>

        <script>
            $(document).ready(function () {      //DOM manipulation code  
                jQuery.validator.addMethod("lettersonly", function (value, element) {
                    return this.optional(element) || /^[a-z]+$/i.test(value);
                }, "Por favor, solo letras");
                jQuery.validator.addMethod("numbersonly", function (value, element) {
                    return this.optional(element) || /^[0-9]+$/i.test(value);
                }, "Por favor, solo números");
                jQuery.validator.addMethod("alfanumOespacio", function (value, element) {
                    return this.optional(element) || /^[ a-z0-9áéíóúüñ]*$/i.test(value);
                }, "Ingrese sólo letras, números o espacios.");
                jQuery.validator.addMethod("minimo8", function (value, element) {
                    return this.optional(element) || /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/i.test(value);
                }, "Minimo 8 caracteres, con al menos una letra y un número.");
                jQuery.validator.addMethod("cualquiercaracter", function (value, element) {
                    return this.optional(element) || /^(?=.*[A-Za-z])(?=.*\d).{8,}$/i.test(value);
                }, "Cualquier caracter.");
                jQuery.validator.addMethod("minimo50", function (value, element) {
                    return this.optional(element) || /^(?=.*[A-Za-z])(?=.*\d).{8,}$/i.test(value);
                }, "minimo50.");
            });
        </script>


    </body>
</html>


