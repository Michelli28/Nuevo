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
                            <p style="font-family: arial;">Cal. Mercaderes Nro. 164 Dpto. 203 </p>
                            <p style="font-family: arial;">Urb. Las Gardenias (Alt. Cuadra 50 de la Avenida Benavides)</p>

                        </div>

                        <div class="p-4" id="fieldset">

                            <fieldset style="border:1px solid black" id="fld" >

                                <h5> R.U.C. N° 20111807958 </h5>
                                <h2 style="font-family: cambria;"><strong>Factura</strong></h2>
                                <div class="row" id="ngui">
                                    N° &nbsp;&nbsp;&nbsp;<input type="text"style="background: #F0E9FF;"  id="idCotizacion">&nbsp;-&nbsp;0000041
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
                                            <td  id="idDetalleCotizacion" name="idDetalleCotizacion">${item.idDetalleCotizacion}</td>
                                            <td style="text-align: center;">${item.idDetallePedido.idFicha.descripcion}</td>
                                            <td style="text-align: center;width: 30%;">${item.idDetallePedido.idFicha.cantidad}</td>
                                            <td style="width: 30%;"><center><input type="text" name="subTotal"  value="${item.subTotal}" style="background: #F0E9FF; width: 60%; text-align: center;" id="subtotal" class="monto" style="text-align: center;" onkeyup="sumar()" readonly="readonly"/></center></td>

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
                                            <input style="background: #F0E9FF; width: 115%;" id="imp" name="imp" type="text" value="${coti.importe}">

                                        </div>
                                    </div>
                                    <div class="d-flex flex-row" >
                                        <div class="p-6">
                                            <label for="igv" >IGV(%18):</label>
                                        </div>
                                        <div class="p-6">
                                            <input style="background: #F0E9FF;width: 115%;" id="igv" name="igv" type="text" value="${coti.igv}">
                                        </div>	
                                    </div>
                                    <div class="d-flex flex-row" >
                                        <div  class="p-6">
                                            <label for="total" >Total(S/):</label>
                                        </div>
                                        <div  class="p-6">
                                            <input style="background: #F0E9FF;width: 115%;" id="total" name="total" type="text" value="${coti.total}">

                                        </div>	
                                    </div>
                                </fieldset>

                            </div>
                        </div>

                        <fieldset id="fieldset4" style="border: 1px solid gray">
                            <legend><p>¿Qué acción desea realizar?</p></legend>
                            <center>
                            <div class="d-flex flex-row" >
                                <div class="p-6">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" id="btn" onclick="Enviar()" class="btn btn-success" style="padding: 0px 20px;">Enviar</button>
                                </div>
                                <div class="p-6">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-outline-secondary" style="padding: 0px 20px;">Cancelar</button>
                                </div>
                            </div>
                                </center>
                        </fieldset>

                    </form>
                </div>

            </div>


        </div>
                                            <br>

        <script>
            $(function () {
                $("#btn").on("click", function () {
                    $("#formulario").validate({
                        rules:
                                {
                                    razon: {required: true, minlength: 3, maxlength: 20, lettersonly: true},
                                    txtfechaactual: {required: true, minlength: 10, maxlength: 10},
                                    direccion: {required: true, maxlength: 30},
                                    telefono: {required: true, minlength: 7, maxlength: 9, numbersonly: true},
                                    correo: {required: true, email: true, minlength: 15, maxlength: 30},

                                },
                        messages:
                                {
                                    razon: {required: 'El campo es requerido', minlength: 'El mínimo permitido son 3 caracteres',
                                        maxlength: 'El máximo permitido son 20 caracteres'},
                                    txtfechaactual: {required: 'El campo es requerido', minlength: 'El mínimo pérmitido son 10 caracteres', maxlength: 'El máximo permitido son 10 caracteres'},
                                    direccion: {required: 'El campo es requerido', maxlength: 'El máximo permitido son 30 caracteres'},
                                    telefono: {required: 'El campo es requerido', minlength: 'El mínimo permitido son 7 caracteres para teléfono',
                                        maxlength: 'El máximo permitido son 9 caracteres para celular',
                                        numbersonly: 'Por favor, solo ingrese números'},

                                    correo: {required: 'El campo es requerido', email: 'El caracter "@" es requerido', minlength: 'El mínimo permitido son 15 caracteres', maxlength: 'El máximo permitido son 30 caracteres'},

                                }
                    });
                });
            });
        </script>
        <script>
            function Enviar() {

                var detalles = obtenerDetalles();
                //var fechae = formato($("#fechae").val());

    //alert(formato($("#fechaentrega").val()));
                $.ajax({
                    type: 'POST',
                    url: 'generarfactura.htm',
                    data: {
                        'idPedido': $("#idPedido").val(),
                        'fechaEmision': $("#txtfechaactual").val(), 
                        'imp': $("#imp").val(),
                        'igv': $("#igv").val(),
                        'total': $("#total").val(),
                        'detalles': detalles
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
                    //cadena += $(this).find('td:eq(2)').text() + ";";
                    cadena += $(this).find("input").val() + ";";
                    // ESTADO
                   // cadena += $(this).find('td:eq(1)').text() + ";";
                    // SUBTOTAL
               
                });
                return cadena.substring(0, cadena.length - 1);
            }


        </script>
    </body>
</html>
