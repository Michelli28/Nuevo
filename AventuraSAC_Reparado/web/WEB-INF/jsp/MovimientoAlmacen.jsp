<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="webapp/resources/theme1/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/js/bootstrap.min.js" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/main.css" />" rel="stylesheet" />
        <script src="<c:url value="webapp/resources/theme1/js/jquery.min.js" />"></script>
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
                    <div class="card-header" id="cardheader">
                        <br>
                        <center><h2>Movimiento de Almacén</h2></center>
                        <br>
                    </div>

                    <div class="card-body">
                        <div class="d-flex flex-row">
                            <div class="p-4">
                                <div class="d-flex flex-row">
                                    <div class="p-6">
                                        <label for="idOrdenCompra">Orden de Compra:</label> 
                                    </div>
                                    <div class="p-6">
                                        <input name="idOrdenCompra" id="idOrdenCompra" cssClass="form-control" style="height:35px; margin-left: 15px; width: 50px;" placeholder="ID"/>
                                    </div>
                                </div>
                            </div>
                            <div class="p-4">
                                <div class="d-flex flex-row" style="margin-left: 65px;">
                                    <div class="p-6">
                                        <label for="tipoMovimiento">Tipo de Movimiento:</label> 
                                    </div>
                                    <div class="p-6">
                                        <input name="tipoMovimiento" id="tipoMovimiento" cssClass="form-control" style="height:35px; margin-left: 15px;" placeholder="Tipo de Movimiento"/>
                                    </div>
                                </div>
                            </div>
                            <div class="p-4">
                                <div class="d-flex flex-row" style="margin-left: 65px;">
                                    <div class="p-6">
                                        <label for="fecha">Fecha:</label> 
                                    </div>
                                    <div class="p-6">
                                        <input name="fecha" id="fecha" cssClass="form-control" style="height:35px; margin-left: 15px;" value="${fecha}"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="d-flex flex-row">
                            <div class="p-4">
                                <div class="d-flex flex-row">
                                    <div class="p-6">
                                        <label for="idTipoItem">Tipo Item:</label> 
                                    </div>
                                    <div class="p-6">
                                        <select name="idTipoItem" id="idTipoItem" style="height:50px; margin-left: 15px; width: 300px;">
                                            <c:forEach var="x" items="${tipoitem}">
                                                <option value="${x.idTipoItem}">${x.nombre}</option>
                                            </c:forEach>
                                        </select>
                                        </div>
                                    </div>
                                </div>
                            <div class="p-4">
                                <div class="d-flex flex-row">
                                    <div class="p-6">
                                        <label for="descripcion">Descripción:</label> 
                                    </div>
                                    <div class="p-6">
                                        <textarea name="descripcion" id="descripcion" cssClass="form-control" style="height:50px; margin-left: 15px; width: 300px;" placeholder="Ingrese la descripción"></textarea>
                                        </div>
                                    </div>
                                </div>
                                <div class="p-4">
                                    <input type="button" class="btn btn-dark" value="Guardar" onclick="guardar()"/>
                                </div>
                            </div>

                            <br>

                            <table class="table table-bordered" style="width: 100%;" id="table">
                                <thead style="background-color: #D6D9D9;">
                                    <tr>
                                        <th style="text-align: center; width: 20%;">Orden de Compra</th>
                                        <th style="text-align: center; width: 20%;">Tipo movimiento</th>
                                        <th style="text-align: center; width: 20%;">Descripción</th>
                                        <th style="text-align: center;">Fecha</th>
                                        <th style="text-align: center;">Acciones</th>
                                    </tr>
                                </thead>
                                <tbody id="tablita">
                                    
                            </tbody>
                        </table>

                        <hr style="border: solid gray 1px;">
                        <center>
                            <input type="button" class="btn btn-dark" value="Registrar" onclick="Enviar()"/>
                            <a class="btn btn-dark" href="menualmacen.htm" role="button">Regresar al Menú</a> 
                        </center>

                    </div>

            </div>

        </div>

        <br>
        <br>
        <script>
            function guardar() {
                var idOrden = document.getElementById("idOrdenCompra").value;
                var tipoMovimiento = document.getElementById("tipoMovimiento").value;
                
                var descripcion = document.getElementById("descripcion").value;
                var idtipo = document.getElementById("idTipoItem").value;
                var fecha = document.getElementById("fecha").value;
                var i = 1;
                var fila = '<tr id="row" ' + i + '><td style="text-align: center;">'+ idOrden + '</td><td style="text-align: center;">' + tipoMovimiento + '</td><td style="text-align: center;">'+ descripcion + '</td><td style="text-align: center;">'+ idtipo + '</td><td style="text-align: center;">' + fecha + '</td><td style="text-align:center;"><button type="button" id="' + i + '" class="btn btn-danger btn_remove" onclick="remove(this)" >Quitar</button></td></tr>';

                i = i + 1;
                var btn = document.createElement("TR");
                btn.innerHTML = fila;
                document.getElementById("tablita").appendChild(btn);
                document.getElementById("idOrdenCompra").value = "";
                document.getElementById("tipoMovimiento").value = "";
                document.getElementById("descripcion").value = "";
                document.getElementById("idOrdenCompra").focus();
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

               // alert(detalles);
                $.ajax({
                    type: 'POST',
                    url: 'crearmovimiento.htm',
                    data: {
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
                    cadena += $(this).find('td:eq(1)').text() + ",";
                    // DESCRIPCION
                    cadena += $(this).find('td:eq(2)').text() + ",";
                    // FECHA
                    cadena += $(this).find('td:eq(3)').text() + ";";
                    
                    cadena += $(this).find('td:eq(4)').text() + ";";
                });
                return cadena.substring(0, cadena.length - 1);
            }


        </script>
    </body>
</html>
