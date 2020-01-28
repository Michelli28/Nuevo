<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="webapp/resources/theme1/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/EstilosFicha.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/js/bootstrap.min.js" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/main.css" />" rel="stylesheet" />
        <script src="<c:url value="webapp/resources/theme1/js/jquery.min.js" />"></script>
        <title>JSP Page</title>
    </head>
    <body id="body" style="background-color:rgba(100,0,0, 0.3);">

        <header id="header">
            <a class="logo" href="menu.htm">Aventura S.A.C.</a>
            <nav>
                <a href="#menu">Menu</a>
            </nav>
        </header>

        <nav id="menu">
            <ul class="links">
                <li><a href="menuventas.htm">Home</a></li>
                <li><a href="quienessomos.htm">Quienes Somos</a></li>
                <li><a href="login.htm">Salir</a>
            </ul>
        </nav>

        <div class="container md-8">
            <br>
            <div style="background-color:rgba(150,36,13, 0.9);">

                <div class="card" id="carta" style="margin-left: 10px; margin-top: 15px;">
                    <div class="card-header" id="cardheader" style="background-color:rgba(150,36,13, 0.6);">
                        <br>
                        <center><h2 style="font-family: cambria; margin-bottom: 0px;">Movimiento de Almacén</h2></center>
                        <br>
                    </div>

                    <div class="card-body">
                        <div class="d-flex flex-row">
                            <div class="p-3">
                                <div class="d-flex flex-row" style="font-family: arial;">
                                    <div class="p-6">
                                        <label for="idOrdenCompra">Orden de Compra:</label> 
                                    </div>
                                    <div class="p-6">
                                        <input name="idOrdenCompra" id="idOrdenCompra" cssClass="form-control" type="text" style="height:35px;font-family: arial; margin-left: 15px; width: 50px;text-align: center;" placeholder="ID"/>
                                    </div>
                                </div>
                            </div>
                            <div class="p-3">
                                <div class="d-flex flex-row" style="margin-left: 18px;font-family: arial;">
                                    <div class="p-6">
                                        <label for="tipoMovimiento">Tipo de Movimiento:</label> 
                                    </div>
                                    <div class="p-6">
                                        <input name="tipoMovimiento" id="tipoMovimiento" type="text" cssClass="form-control" style="height:35px;font-family: arial; margin-left: 15px; width: 150px; text-align: center;" placeholder="Tipo de Movimiento"/>
                                    </div>
                                </div>
                            </div>
                            <div class="p-3">
                                <div class="d-flex flex-row" style="margin-left:18px;font-family: arial;">
                                    <div class="p-6">
                                        <label for="fecha">Fecha:</label> 
                                    </div>
                                    <div class="p-6">
                                        <input name="fecha" id="fecha" cssClass="form-control" type="text" style="height:35px; margin-left: 15px;font-family: arial; width: 100px; text-align: center;" />
                                    </div>
                                </div>
                            </div>
                            <div class="p-3">
                                <div class="d-flex flex-row" style="margin-left:18px;font-family: arial;">
                                    <div class="p-6">
                                        <label for="idTipoItem">Tipo Item:</label> 
                                    </div>
                                    <div class="p-6">
                                        <select name="idTipoItem" id="idTipoItem" style="height:35px; margin-left: 15px; width: 100px; font-family: arial;">
                                            <c:forEach var="x" items="${tipoitem}">
                                                <option value="${x.idTipoItem}">${x.nombre}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="d-flex flex-row">

                            <div class="p-4">
                                <div class="d-flex flex-row" style="margin-left:-5px;font-family: arial;">
                                    <div class="p-6">
                                        <label for="descripcion">Descripción:</label> 
                                    </div>
                                    <div class="p-6">
                                        <input name="descripcion" id="descripcion" type="text" cssClass="form-control" style="height:35px; margin-left: 15px; font-family: arial;width: 150px; text-align: center;" placeholder="Ingrese..">
                                    </div>
                                </div>
                            </div>
                            <div class="p-4">
                                <div class="d-flex flex-row" style="margin-left: 15px;font-family: arial;">
                                    <div class="p-6">
                                        <label for="cantidad">Cantidad:</label> 
                                    </div>
                                    <div class="p-6">
                                        <input name="cantidad" id="cantidad" cssClass="form-control" type="text" style="height:35px; margin-left: 30px;font-family: arial; width: 100px; text-align: center;" placeholder="Ingrese..">
                                    </div>
                                </div>
                            </div>
                            <div class="p-4">
                                <input type="button" class="btn btn-dark" value="Guardar" onclick="guardar()" style="margin-left: 30px; height: 45px; width: 100%; margin-top: -2px; ">
                            </div>
                        </div>
                        <hr style="border: solid gray 1px;">

                        <center>
                            <table class="table table-bordered" style="width: 80%; text-align: center;" id="table">
                                <thead style="background-color: #D6D9D9;">
                                    <tr>
                                        <th style="text-align: center; width: 20%">Cantidad</th>
                                        <th style="text-align: center; ">Descripción</th>
                                        <th style="text-align: center;">Acciones</th>
                                    </tr>
                                </thead>
                                <tbody id="tablita">

                                </tbody>
                            </table>
                        </center>
                        <hr style="border: solid gray 1px;">
                        <center>
                            <input type="button" class="btn btn-dark" value="Registrar" onclick="Enviar()" style="width: 16%; margin-right: 15px;"/>
                            <a class="btn btn-dark" href="menualmacen.htm" role="button" style="text-decoration: none; text-align: center; width: 20%; height: 47px !important; padding: 1.1%;">Regresar al Menú</a> 
                        </center>

                    </div>

                </div>
            </div>
        </div>
    </div>

    <br>
    <br>
    <script>
        function guardar() {

            var descripcion = document.getElementById("descripcion").value;
            var cantidad = document.getElementById("cantidad").value;
            var i = 1;
            var fila = '<tr id="row" ' + i + '><td style="text-align: center;">' + cantidad + '</td><td style="text-align: center;">' + descripcion + '</td><td style="text-align:center;"><button type="button" id="' + i + '" class="btn btn-danger btn_remove" onclick="remove(this)" >Quitar</button></td></tr>';

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

            //  alert(detalles).val());
            $.ajax({
                type: 'POST',
                url: 'crearmovimiento.htm',
                data: {
                    'idOrdenCompra': $("#idOrdenCompra").val(),
                    'tipoMovimiento': $("#tipoMovimiento").val(),
                    'fecha': $("#fecha").val(),
                    'idTipoItem': $("#idTipoItem").val(),
                    'detalles': detalles
                },
                success: function (data) {
                    window.location.href = 'listamovimiento.htm';
                }
            });
        }


        function obtenerDetalles() {
            var cadena = '';
            // Recorrer las filas TR de la tabla
            $("#table tbody tr").each(function (i) {
                // ID ORDEN
                cadena += $(this).find('td:eq(0)').text() + ",";
                // TIPO MOVIMIENTO
                cadena += $(this).find('td:eq(1)').text() + ";";
                // DESCRIPCION

            });
            return cadena.substring(0, cadena.length - 1);
        }


    </script>
</body>
</html>
