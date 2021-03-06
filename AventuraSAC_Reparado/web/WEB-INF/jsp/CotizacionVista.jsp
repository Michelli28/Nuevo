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
        <script type="text/javascript" src="<c:url value="webapp/resources/theme1/js/jquery.validate.min.js"/>"></script>

    </head>
    <body id="bodys" style="background-color:rgba(100,0,0, 0.3);">

        <header id="header">
            <a class="logo" href="index.html">Aventura S.A.C.</a>
            <nav>
                <a href="#menu">Menu</a>
            </nav>
        </header>

        <div class="container md-8">

            <div style="background-color:rgba(150,36,13, 0.9);">
                <div class="card" id="carta" style="margin-left: 10px;">

                    <div class="card-header"  style="background-color:rgba(150,36,13, 0.6);">

                        <div class="d-flex flex-row">

                            <div  class="p-4" id="img">

                                <img src="webapp/resources/theme1/images/logo.PNG" alt="Card image cap">

                            </div>

                            <div class="p-4"  id="escritos">
                                <p style="font-family: arial;">Cal. Mercaderes Nro. 164 Dpto. 203 </p>
                                <p style="font-family: arial;">Urb. Las Gardenias (Alt. Cuadra 50 de la Avenida Benavides)</p>

                            </div>

                            <div class="p-4" id="fieldset">

                                <fieldset style="border:1px solid black" id="fld" >

                                    <h5 style="font-family: arial; margin-top: 10px;"> R.U.C. N° 20111807958 </h5>
                                    <h2 style="font-family: cambria; font-size: 25px;"><strong>Cotización</strong></h2>
                                    <div class="row" >
                                        <div class="d-flex flex-row" style="margin-left: 10px;">
                                            <div class="p-2">
                                                N° 
                                            </div>
                                            <div class="p-2">
                                                <input type="text"style="background: #F0E9FF; font-family: arial; width: 45px; height: 30px;" readonly="readonly" id="idCotizacion" value="${cotizacion.idCotizacion}">
                                            </div>
                                            <div class="p-2">
                                                - 0000041
                                            </div>
                                        </div>
                                    </div>

                                </fieldset>
                            </div>
                        </div>

                    </div>

                    <br>

                    <div class="card-body">

                        <input type="hidden" name="idPedido" id="idPedido" value="${cotizacion.idPedido}">

                    
                        <legend style="text-align: left; margin-left: 15px;"><p><strong>Información:</strong></p></legend>
                                    <p style=" margin: 1% 2% auto;">* N° de Cuenta: 000909999099</p>
                                    <p style=" margin: 1% 2% auto;">* Se deberá de pagar por adelantado el 50% del total del pedido.</p>
                                    <br>
                        <c:forEach var="item1" items="${cliente}">
                            <fieldset id="fieldset1" style="border: 1px solid gray">
                                <legend><h3 style="font-family: cambria; font-size: 20px; text-align: center;"><strong>Datos del Cliente</strong></h3></legend>
                                <div class="row" id="fila1">
                                    <div class="col-sm-4">
                                        <div class="d-flex flex-row">
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
                                                <label for="">Fecha Emisión:</label>
                                            </div>&nbsp;
                                            <div class="p-6">
                                                <label style="font-size: 14px;text-align: center;">${cotizacion.fechaEmision}</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row" id="fila2">
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
                                    <div class="col-sm-4">
                                        <div class="d-flex flex-row">
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

                        <center><h3 style="font-family: cambria; font-size: 20px; text-align: center;"><strong>Detalle</strong></h3></center>

                        <center>
                            <table class="table" id="table">
                                <thead class="thead-dark">
                                    <tr class="encabezado">
                                        <th style="text-align: center;">IdFicha</th>
                                        <th style="text-align: center;">Descripcion</th>
                                        <th style="text-align: center;">Cantidad</th>
                                        <th style="text-align: center;">Estado</th>
                                        <th style="text-align: center;">Subtotal</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="item" items="${detalle}">
                                        <tr>
                                            <td style="text-align: center;font-family: Arial;">${item.idDetallePedido.idDetallePedido}</td>
                                            <td style="text-align: center;font-family: Arial;">${item.idDetallePedido.idFicha.descripcion}</td>
                                            <td style="text-align: center;font-family: Arial;">${item.idDetallePedido.idFicha.cantidad}</td>
                                            <td style="text-align: center;font-family: Arial;">${item.idDetallePedido.idFicha.idEstado.nombre}</td>
                                            <td style="text-align: center; font-family: Arial;"><label id="sub">${item.subTotal}</label>

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
                                            <input id="imp" name="imp" style="width: 80px; height: 35px" type="text" readonly="readonly" value="${cotizacion.importe}"> 
                                        </div>
                                    </div>
                                    <div class="d-flex flex-row" >
                                        <div class="p-6">
                                            <label for="igv" >IGV(%18):</label>
                                        </div>
                                        <div class="p-6">
                                            <input id="igv" name="igv" style="width: 80px; height: 35px" type="text" readonly="readonly" value="${cotizacion.igv}">
                                        </div>	
                                    </div>
                                    <div class="d-flex flex-row" >
                                        <div  class="p-6">
                                            <label for="total" >Total(S/):</label>
                                        </div>
                                        <div  class="p-6">
                                            <input id="total" name="total" style="width: 80px; height: 35px" type="text" readonly="readonly" value="${cotizacion.total}">
                                        </div>	
                                    </div>
                                </fieldset>

                            </div>
                        </div>
                        <br>

                        <div class="col">
                            <label for="observacion" >Observaciones:</label>
                            <textarea name="observacion" id="observacion" style="width: 40%" >${cotizacion.observacion}</textarea>

                        </div>
                                
                       
                                <fieldset id="fieldset4" style="border: 1px solid gray; width: 50%; margin: 3% 25%; ">
                                    <legend><p>¿Qué acción desea realizar?</p></legend>
                                    <center>
                                        <div class="d-flex flex-row" >
                                            <div class="p-4">
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a type="button" class="btn btn-success" style="text-decoration: none; height: 50px; width: 100%; padding-top: 15px;;" href="aceptar.htm?idPedido=${cotizacion.idPedido.idPedido}" >Aceptar</a>
                                            </div>
                                            <div class="p-4">
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a type="button" class="btn btn-success" style="text-decoration: none; height: 50px; width: 100%; padding-top: 15px;" href="rechazar.htm?idPedido=${cotizacion.idPedido.idPedido}">Rechazar</a>
                                            </div>
                                            <div class="p-4">
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a type="button" class="btn btn-outline-secondary" style="text-decoration: none; height: 50px; width: 100%; padding-top: 15px;" href="listapedidos.htm">Retornar</a>

                                            </div>
                                        </div>
                                    </center>
                                </fieldset>
                        



                    </div>

                    <div>
                        <input type="hidden" name="idEstado" value="${cotizacion.idPedido.idEstado.idEstado}">
                    </div>

                </div>

            </div>
        </div>
        <br>

    </body>
</html>