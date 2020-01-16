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

                <form method="post" action="generarorden.htm">

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
                                            <input type="hidden" name="idOrdenCompra" cssClass="form-control"/>
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
                        <input type="hidden" name="idEmpleado" value="${usuario.idEmpleado}"/>
                        <div class="row">
                            <div class="col-sm-4" >
                                <label for="fechaEmision"><strong>Fecha:</strong></label>
                                <input type="text" name="fechaEmision" id="txtfechaactual" />
                            </div>
                            <div class="col-sm-4" >
                                <label for="idProveedor"><strong>Proveedor:</strong></label>
                                <select path="idProveedor.idProveedor" id="idProveedor">
                                    <c:forEach items="${proveedor}" var="x">
                                        <option value="${x.idProveedor}">${x.razonSocial}</option>
                                    </c:forEach>
                                </select>
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


                        <table class="table" style="width: 100%;" id="table">
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
                            <input type="button" class="btn btn-dark"value="Guardar" onclick="Enviar()"/>
                            <a class="btn btn-dark" href="menu.htm" role="button">Regresar al Menú</a> 
                        </center>

                    </div>
                </form>


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
                var detalles = obtenerDetalle();

                $.ajax({
                    type: 'POST',
                    url: 'generarorden.htm',
                    data: {
                        'fechaEmision': $("#txtfechaactual").val(),
                        'detalles': detalles,
                                'imp': $("#imp").val(),
                                'igv': $("#igv").val(),
                                'tota'imp': $("#imp").val(),
                                'igv': $("#igv")l': $("#total").val(),
                                'observacion': $("#observacion").val()
                    },
                    success: function (data) {
                        window.location.href = 'listapedidostrabajador.htm';
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
                    cadena += $(this).find('td:eq(3)').find("option:selected").val() + ",";
                    // SUBTOTAL
                    if ($(this).find('td:eq(3)').find("option:selected").val() === '2') {
                        cadena += '0;';
                    } else {
                        cadena += $(this).find("input").val() + ";";
                    }
                });
                return cadena.substring(0, cadena.length - 1);
            }
        </script>

    </body>
</html>


