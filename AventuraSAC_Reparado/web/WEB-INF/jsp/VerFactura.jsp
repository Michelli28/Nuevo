<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="webapp/resources/theme1/css/EstilosFactura.css" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="webapp/resources/theme1/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/main.css" />" rel="stylesheet" />
        <script src="<c:url value="webapp/resources/theme1/js/jquery.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="webapp/resources/theme1/js/jquery.validate.min.js"/>"></script>

        <title>Factura</title>
    </head>
    <body id="body">

        <header id="header">
            <a class="logo" href="index.html">Aventura S.A.C.</a>
            <nav>
                <a href="#menu">Menu</a>
            </nav>
        </header>

        <div class="container md-8">

            <div class="card" id="carta">

                <div class="card-header" id="cardheader">

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

                                <h3 style="font-family: arial; font-size: 16px; margin: 10px;"> R.U.C. N° 20111807958 </h3>
                                <h3 style="font-family: arial;"><strong>Factura</strong></h3>
                                <div class="row" id="ngui">
                                    <div class="d-flex flex-row">
                                        <div class="p-1">
                                            N° 
                                        </div>
                                        <div class="p-1">
                                            <input type="text"style="background: #F0E9FF; font-family: arial;"  id="idCotizacion">
                                        </div>
                                        <div class="p-1">
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

                    <form id="formulario">

                        <input type="hidden" name="idPedido" id="idPedido" value="${idPedido}">
                        <input type="hidden" id="idCotizacion" name="idCotizacion" value="${coti.idCotizacion}">

                        <center>
                            <div class="card-title"><h3 ><strong>Datos del Cliente</strong></h3></div>
                        </center>
                        <c:forEach items="${cliente}" var="item1">
                            <fieldset id="fieldset1" style="border: 1px solid gray">
                                <div class="row" id="fila1">
                                    <div class="col-sm-4">
                                        <label for="">Razón Social:</label>
                                        <input type="text"  style="background: #F0E9FF;" id="razonSocial" value="${item1.razonSocial}" readonly="readonly"> 
                                    </div>
                                    <div class="col-sm-4">      
                                        <label for="">RUC:</label>
                                        <input type="text" id="ruc" style="background: #F0E9FF;" value="${item1.ruc}" readonly="readonly"/>
                                    </div>
                                    <div class="col-sm-4">      
                                        <label for="fechaEmision">Fecha de Emision:</label>
                                        <input type="text" style="background: #F0E9FF;" id="txtfechaactual" value="${fecha}" name="fechaEmision" readonly="readonly"/>
                                    </div>
                                </div>

                                <div class="row" id="fila2">
                                    <div class="col-sm-4">
                                        <label for="direccion">Dirección:</label>
                                        <input type="text" style="background: #F0E9FF;" id="direccion" value="${item1.direccion}" readonly="readonly"/>
                                    </div>
                                    <div class="col-sm-4">
                                        <label for="">Teléfono:</label>
                                        <input type="text" style="background: #F0E9FF;" id="telefono" value="${item1.telefono}" readonly="readonly"/>
                                    </div>
                                    <div class="col-sm-4">      
                                        <label for="">Correo:</label>
                                        <input type="text" style="background: #F0E9FF;" id="correo" value="${item1.correo}" readonly="readonly"/>
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
                                        <th style="text-align: center;">IdFicha</th>
                                        <th style="text-align: center;">Descripcion</th>
                                        <th style="text-align: center;">Cantidad</th>
                                        <th style="text-align: center;">Subtotal</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="item" items="${detalle}">
                                        <tr>
                                            <td style="text-align: center;">${item.idDetalleCotizacion.idDetallePedido.idFicha.idFicha}</td>
                                            <td style="text-align: center;">${item.idDetalleCotizacion.idDetallePedido.idFicha.descripcion}</td>
                                            <td style="text-align: center;width: 30%;">${item.idDetalleCotizacion.idDetallePedido.idFicha.cantidad}</td>
                                            <td style="width: 30%;"><center><input type="text" name="subTotal"  value="${item.subtotal}" style="background: #F0E9FF; width: 60%; text-align: center;" id="subtotal" class="monto" style="text-align: center;" onkeyup="sumar()" readonly="readonly"/></center></td>

                                    </tr>
                                </c:forEach>

                                </tbody>

                            </table>
                        </center>

                        <div class="row">

                            <div class="col-sm-3" id="col2">
                                <fieldset id="fieldset3" style="border: 1px solid gray; background: white;">

                                    <div class="d-flex flex-row" >
                                        <div class="p-6">

                                            <label for="importe" >Importe(S/):</label>
                                        </div>
                                        <div class="p-6">
                                            <input style="background: #F0E9FF; width: 115%;" id="imp" name="imp" type="text" value="${factura.importe}"readonly="readonly">

                                        </div>
                                    </div>
                                    <div class="d-flex flex-row" >
                                        <div class="p-6">
                                            <label for="igv" >IGV(%18):</label>
                                        </div>
                                        <div class="p-6">
                                            <input style="background: #F0E9FF;width: 115%;" id="igv" name="igv" type="text" value="${factura.igv}" readonly="readonly">
                                        </div>	
                                    </div>
                                    <div class="d-flex flex-row" >
                                        <div  class="p-6">
                                            <label for="total" >Total(S/):</label>
                                        </div>
                                        <div  class="p-6">
                                            <input style="background: #F0E9FF;width: 115%;" id="total" name="total" type="text" value="${factura.total}" readonly="readonly">

                                        </div>	
                                    </div>
                                </fieldset>

                            </div>
                        </div>

                        <fieldset id="fieldset4" style="border: 1px solid gray">
                            <legend><p>¿Qué acción desea realizar?</p></legend>
                            <center>

                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a type="button" id="btn" class="btn btn-success" href="listapedidos.htm" style="text-decoration: none; margin-right: 20px; width: 100px;">Retornar</a>


                            </center>
                        </fieldset>

                    </form>
                </div>

            </div>


        </div>
        <br>
    </script>
</body>
</html>
