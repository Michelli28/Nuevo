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
    <body id="bodys">

        <header id="header">
            <a class="logo" href="index.html">Aventura S.A.C.</a>
            <nav>
                <a href="#menu">Menu</a>
            </nav>
        </header>

        <div class="container md-8">

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
                                        N° &nbsp;&nbsp;&nbsp;<input type="text" id="idCotizacion">&nbsp;-&nbsp;0000041
                                    </div>

                                </fieldset>
                            </div>
                        </div>

                    </div>

                    <br>

                    <div class="card-body">

                        <input type="hidden" name="idPedido" id="idPedido" value="${cotizacion.idPedido}">

                        <center>
                            <div class="card-title"><h2><strong>Datos de la Cotización</strong></h2></div>
                        </center>

                        <c:forEach var="item1" items="${cliente}">
                            <fieldset id="fieldset1" style="border: 1px solid gray">
                                <div class="row" id="fila1">
                                    <div class="col-sm-4">
                                        <label for="">Razón Social:</label>
                                        <input type="text"  id="razonSocial" value="${item1.razonSocial}"> 
                                    </div>
                                    <div class="col-sm-4">      
                                        <label for="">RUC:</label>
                                        <input type="text" id="ruc" value="${item1.ruc}"/>
                                    </div>
                                    <div class="col-sm-4">      
                                        <label for="fechaEmision">Fecha de Emision:</label>
                                        <input type="text" id="fechaEmision" id="txtfechaactual" value="${cotizacion.fechaEmision}"/>
                                    </div>
                                </div>

                                <div class="row" id="fila2">
                                    <div class="col-sm-4">
                                        <label for="direccion">Dirección:</label>
                                        <input type="text" id="direccion" value="${item1.direccion}"/>
                                    </div>
                                    <div class="col-sm-4">
                                        <label for="">Teléfono:</label>
                                        <input type="text" id="telefono" value="${item1.telefono}"/>
                                    </div>
                                    <div class="col-sm-4">      
                                        <label for="">Correo:</label>
                                        <input type="text" id="correo" value="${item1.correo}"/>
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
                                        <th style="text-align: center;">Estado</th>
                                        <th style="text-align: center;">Subtotal</th>
                                    </tr>
                                </thead>
                                <tbody>
                                     <c:forEach var="item" items="${detalle}">
                                        <tr>
                                            <td style="text-align: center;">${item.idDetallePedido.idDetallePedido}</td>
                                            <td style="text-align: center;">${item.idDetallePedido.idFicha.descripcion}</td>
                                            <td style="text-align: center;">${item.idDetallePedido.idFicha.cantidad}</td>
                                            <td style="text-align: center;">${item.idDetallePedido.idFicha.idEstado.nombre}</td>
                                            <td ><input type="text" name="subTotal"  id="subtotal" class="monto" style="text-align: center;" onkeyup="sumar()" value="${item.subTotal}"/></td>

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
                                            <label id="imp" name="imp" >${cotizacion.importe}</label> 
                                        </div>
                                    </div>
                                    <div class="d-flex flex-row" >
                                        <div class="p-6">
                                            <label for="igv" >IGV(%18):</label>
                                        </div>
                                        <div class="p-6">
                                            <label id="igv" name="igv">${cotizacion.igv}</label>
                                        </div>	
                                    </div>
                                    <div class="d-flex flex-row" >
                                        <div  class="p-6">
                                            <label for="total" >Total(S/):</label>
                                        </div>
                                        <div  class="p-6">
                                            <label id="total" name="total">${cotizacion.total}</label>
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

                        <fieldset id="fieldset4" style="border: 1px solid gray">
                            <legend><p>¿Qué acción desea realizar?</p></legend>
                            <div class="row" id="fila8">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a type="button" class="btn btn-success"  href="RegistrarPago.htm?idPedido=${cotizacion.idPedido.idPedido}">Ir a pago por adelantado</a>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a type="button" class="btn btn-outline-secondary" href="listapedidos.htm">Cancelar</a>
                            </div>
                        </fieldset>
                            
                            <p>N° de Cuenta: 000909999099</p>
                            <p>Se deberá de pagar por adelantado el 50% del total del pedido.</p>

                    </div>

                </div>
           
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

            function Enviar() {
                var fichas = obtenerFichas();
                /*var parametros = "idpedido=" + $("#idPedido").val() +
                        "&fechaEmision=" + $("#txtfechaactual").val() +
                        "&fichas=" + fichas +
                        "&imp=" + $("#imp").val() +
                        "&igv=" + $("#igv").val() +
                        "&total=" + $("#total").val() +
                        "&observacion=" + $("#observacion").val();

                window.location.href = 'generarcotizacion.htm?' + parametros;
                */
                $.ajax({
                    type: 'POST',
                    url: 'generarcotizacion.htm',
                    data: {
                        'idPedido': $("#idPedido").val(),
                        'fechaEmision': $("#txtfechaactual").val(),
                        'fichas': fichas,
                        'imp': $("#imp").val(),
                        'igv': $("#igv").val(),
                        'total': $("#total").val(),
                        'observacion': $("#observacion").val()
                    },
                    success: function (data) {
                        window.location.href = 'listapedidostrabajador.htm';
                    }
                });
            }

            function obtenerFichas() {
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
        
        <script>
            $(function () {
                $("#btn").on("click", function () {
                    $("#formulario").validate({
                        rules:
                                {
                                    idCotizacion: {required: true, numbersonly: true },
                                    subtotal: { required: true, numbersonly: true},
                                    observacion: {required: true, minlength: 15, maxlength: 50}
                                }, 
                        messages:
                                {
                                    idCotizacion: {required: true, numbersonly: true },
                                    subtotal: {required: 'El campo es requerido', numbersonly: 'Porfavor, solo números'},
                                    observacion:  {required: 'El campo es requerido', minlength: 'El mínimo permitido son 15 caracteres' ,maxlength: 'El máximo permitido son 50 caracteres'}
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
            });
        </script>
    </body>
</html>