<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
    <head>
        <title>Cotización</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <link href="<c:url value="webapp/resources/theme1/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/EstilosCotizacion.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/main.css" />" rel="stylesheet" />
        <script src="<c:url value="webapp/resources/theme1/js/jquery.min.js" />"></script>

    </head>
    <body id="bodys">

        <header id="header">
            <a class="logo" href="index.html">Aventura S.A.C.</a>
            <nav>
                <a href="#menu">Menu</a>
            </nav>
        </header>

        <div class="container md-8">

            <form method="post" action="generarcotizacion.htm">

                <div class="card">

                    <div class="card-header">

                        <div class="d-flex flex-row">

                            <div  class="p-4" id="img">

                                <img src="webapp/resources/theme1/images/logo.PNG" alt="Card image cap">

                            </div>

                            <div class="p-4"  id="escritos">
                                <p>Cal. Mercaderes Nro. 164 Dpto. 203 </p>
                                <p>Urb. Las Gardenias (Alt. Cuadra 50 de la Avenida Benavides)</p>

                            </div>

                            <div class="p-4" id="fieldset">

                                <fieldset style="border:1px solid black" id="fld" >

                                    <h3> R.U.C. N° 20111807958 </h3>
                                    <h3><strong>Cotización</strong></h3>
                                    <div class="row" id="ngui">
                                        N° &nbsp;&nbsp;&nbsp;<input name="idCotizacion">&nbsp;-&nbsp;0000041
                                    </div>

                                </fieldset>
                            </div>
                        </div>

                    </div>

                    <br>

                    <div class="card-body">

                        <input type="hidden" name="idPedido" value="${idPedido}">

                        <center>
                            <div class="card-title"><h2><strong>Datos de la Cotización</strong></h2></div>
                        </center>

                        <c:forEach var="item1" items="${clientes}">
                            <fieldset id="fieldset1" style="border: 1px solid gray">
                                <div class="row" id="fila1">
                                    <div class="col-sm-4">
                                        <label for="">Razón Social:</label>
                                        <input type="text"  value="${item1.razonSocial}"> 
                                    </div>
                                    <div class="col-sm-4">      
                                        <label for="">RUC:</label>
                                        <input type="text" value="${item1.ruc}"/>
                                    </div>
                                    <div class="col-sm-4">      
                                        <label for="fechaEmision">Fecha de Emision:</label>
                                        <input type="text" name="fechaEmision" id="txtfechaactual" />
                                    </div>
                                </div>

                                <div class="row" id="fila2">
                                    <div class="col-sm-4">
                                        <label for="direccion">Dirección:</label>
                                        <input type="text" value="${item1.direccion}"/>
                                    </div>
                                    <div class="col-sm-4">
                                        <label for="">Teléfono:</label>
                                        <input type="text" value="${item1.telefono}"/>
                                    </div>
                                    <div class="col-sm-4">      
                                        <label for="">Correo:</label>
                                        <input type="text"  value="${item1.correo}"/>
                                    </div>
                                </div>

                            </fieldset>
                        </c:forEach>

                        <br>

                        <center><h3><strong>Descripción</strong></h3></center>

                        <center>
                            <table class="table" id="table">
                                <thead class="thead-dark">
                                    <tr class="encabezado">
                                        <th>IdPedido</th>
                                        <th>IdFicha</th>
                                        <th>Descripcion</th>
                                        <th>Cantidad</th>
                                        <th>Estado</th>
                                        <th>Subtotal</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="item" items="${detalle}">
                                        <tr>
                                            <td scope="row" >${item.idPedido.idPedido}</td>
                                            <td >${item.idDetallePedido}</td>
                                            <td >${item.idFicha.descripcion}</td>
                                            <td >${item.idFicha.cantidad}</td>
                                            <td ></td>
                                            <td ><input type="text" name="subTotal" class="monto" style="text-align: center;" onkeyup="sumar()"/></td>

                                        </tr>
                                    </c:forEach>

                                </tbody>

                            </table>
                        </center>

                        <div class="row">

                            <div class="col-sm-3" id="col2">
                                <fieldset id="fieldset3" style="border: 1px solid gray">

                                    <div class="d-flex flex-row" >
                                        <div class="p-6">

                                            <label for="importe" >Importe(S/):</label>
                                        </div>
                                        <div class="p-6">
                                            <textarea id="imp" name="imp"></textarea> 

                                        </div>
                                    </div>
                                    <div class="d-flex flex-row" >
                                        <div class="p-6">
                                            <label for="igv" >IGV(%18):</label>
                                        </div>
                                        <div class="p-6">
                                            <textarea id="igv" name="igv"></textarea>
                                        </div>	
                                    </div>
                                    <div class="d-flex flex-row" >
                                        <div  class="p-6">
                                            <label for="total" >Total(S/):</label>
                                        </div>
                                        <div  class="p-6">
                                            <textarea id="total" name="total"></textarea>

                                        </div>	
                                    </div>
                                </fieldset>

                            </div>
                        </div>

                        <div class="col">
                            <label for="observacion" >Observaciones:</label>
                            <textarea name="observacion" id="observacion" ></textarea>

                        </div>

                        <fieldset id="fieldset4" style="border: 1px solid gray">
                            <legend><p>¿Qué acción desea realizar?</p></legend>
                            <div class="row" id="fila8">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" class="btn btn-success" value="Enviar" >
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-outline-secondary">Cancelar</button>
                            </div>
                        </fieldset>

                    </div>

                </div>
            </form>
        </div>

        <script>

            function sumar() {

                var total = 0;
                var igv = 0.0;
                var total1 = 0.0;

                $(".monto").each(function () {

                    if (isNaN(parseFloat($(this).val()))) {

                        total += 0;


                    } else {

                        total += parseFloat($(this).val());
                        igv = Number((total * 0.18).toFixed(2));
                        total1 = Number((total + igv).toFixed(2));
                    }

                });

                //alert(total);
                document.getElementById('imp').innerHTML = total;
                document.getElementById('igv').innerHTML = igv;
                document.getElementById('total').innerHTML = total1;

            }

        </script>
    </body>
</html>