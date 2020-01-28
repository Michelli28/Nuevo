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

                    <div class="card-header" style="background-color:rgba(150,36,13, 0.6);">

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

                                    <h5 style="font-family: arial; margin-top: 15px;"> R.U.C. N° 20111807958 </h5>
                                    <h2 style="font-family: cambria; font-size: 25px;"><strong>Cotización</strong></h2 >
                                    <div class="row" >
                                        <div class="d-flex flex-row" style="margin-left: 15px;">
                                            <div class="p-2">
                                                N° 
                                            </div>
                                            <div class="p-2">
                                                <input type="text"style="background: #F0E9FF; font-family: arial; width: 45px; height: 30px; "  id="idCotizacion" >
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


                    <div class="card-body">

                        <input type="hidden" name="idPedido" id="idPedido" value="${idPedido}">

                        <center>
                            <div class="card-title"><h3 style="font-family: cambria; font-size: 20px;"><strong>Datos del Cliente</strong></h3></div>
                        </center>

                        <c:forEach var="item1" items="${clientes}">
                            <fieldset id="fieldset1" style="border: 1px solid gray">
                                <div class="row" id="fila1">
                                    <div class="col-sm-4">
                                        <label for="">Razón Social:</label>
                                        <input type="text"  style="background: #F0E9FF;" id="razonSocial" value="${item1.razonSocial}"> 
                                    </div>
                                    <div class="col-sm-4">      
                                        <label for="">RUC:</label>
                                        <input type="text" id="ruc" style="background: #F0E9FF;" value="${item1.ruc}"/>
                                    </div>
                                    <div class="col-sm-4">      
                                        <label for="fechaEmision">Fecha de Emision:</label>
                                        <input type="text" style="background: #F0E9FF;" id="txtfechaactual" name="fechaEmision" value="${fecha}"/>
                                    </div>
                                </div>

                                <div class="row" id="fila2">
                                    <div class="col-sm-4">
                                        <label for="direccion">Dirección:</label>
                                        <input type="text" style="background: #F0E9FF;" id="direccion" value="${item1.direccion}"/>
                                    </div>
                                    <div class="col-sm-4">
                                        <label for="">Teléfono:</label>
                                        <input type="text" style="background: #F0E9FF;" id="telefono" value="${item1.telefono}"/>
                                    </div>
                                    <div class="col-sm-4">      
                                        <label for="">Correo:</label>
                                        <input type="text" style="background: #F0E9FF;" id="correo" value="${item1.correo}"/>
                                    </div>
                                </div>

                            </fieldset>
                        </c:forEach>

                        <br>

                        <center><h3 style="font-family: cambria; font-size: 20px;"><strong>Descripción</strong></h3></center>


                        <div class="d-flex flex-row">
                            <div class="p-3" style="font-family: arial;">
                                Fecha Entrega:
                            </div>
                            <div class="p-3">
                                <input type="date" name="fechae" id="fechae" style="">
                            </div>
                        </div>
                        <br>

                        <center>
                            <table class="table" id="table">
                                <thead class="thead-dark">
                                    <tr class="encabezado">
                                        <th style="text-align: center;">IdPedido</th>
                                        <th style="text-align: center;">IdFicha</th>
                                        <th style="text-align: center;">Descripcion</th>
                                        <th style="text-align: center;">Estado</th>
                                        <th style="text-align: center;">Subtotal</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="item" items="${detalle}">
                                        <tr>
                                            <td style="text-align: center;">${item.idDetallePedido}</td>
                                            <td style="text-align: center;">${item.idFicha.idFicha}</td>
                                            <td style="text-align: center;">${item.idFicha.descripcion}</td>
                                            <td style="text-align: center;">
                                                <select name="idEstado" id="idEstado">
                                                    <c:forEach items="${estado}" var="x">
                                                        <c:if test="${item.idFicha.idEstado.idEstado == x.idEstado}">
                                                            <option value="${x.idEstado}" selected="selected">${x.nombre}</option>
                                                        </c:if>
                                                        <c:if test="${item.idFicha.idEstado.idEstado != x.idEstado}">
                                                            <option value="${x.idEstado}">${x.nombre}</option>
                                                        </c:if>
                                                    </c:forEach>
                                                </select>

                                            </td>
                                            <td ><center><input type="text" name="subTotal" style="background: #F0E9FF;width: 80%" id="subtotal" class="monto" onkeyup="sumar()"/></center></td>

                                        </tr>
                                    </c:forEach>

                                </tbody>

                            </table>
                        </center>

                        <div class="row">

                            <div class="col-sm-3" id="col2">
                                <fieldset id="fieldset3" style="border: 1px solid gray; background-color:rgba(150,36,13, 0.3);">

                                    <div class="d-flex flex-row" >
                                        <div class="p-6">

                                            <label for="importe" >Importe(S/):</label>
                                        </div>
                                        <div class="p-6">
                                            <textarea style="background: #F0E9FF;width: 95px; height: 35px;text-align: center; padding: 2%;" id="imp" name="imp" type="text" readonly="readonly"></textarea>

                                        </div>
                                    </div>
                                    <div class="d-flex flex-row" >
                                        <div class="p-6">
                                            <label for="igv" >IGV(%18):</label>
                                        </div>
                                        <div class="p-6">
                                            <textarea style="background: #F0E9FF;width: 95px; height: 35px; text-align: center;padding: 2%;" id="igv" name="igv" type="text" readonly="readonly"></textarea>
                                        </div>	
                                    </div>
                                    <div class="d-flex flex-row" >
                                        <div  class="p-6">
                                            <label for="total" >Total(S/):</label>
                                        </div>
                                        <div  class="p-6">
                                            <textarea style="background: #F0E9FF;width: 95px; height: 35px;text-align: center;padding: 2%;" id="total" name="total" type="text" readonly="readonly"></textarea>

                                        </div>	
                                    </div>
                                </fieldset>

                            </div>
                        </div>
                        <br>

                        <div class="col">
                            <label for="observacion" >Observaciones:</label>
                            <textarea name="observacion" style="background: #F0E9FF; font-family: arial; width: 50%;" id="observacion" ></textarea>

                        </div>


                        <fieldset id="fieldset4" style="border: 1px solid gray" >
                            <legend><p>¿Qué acción desea realizar?</p></legend>
                            <center>
                                <div class="d-flex flex-row" >
                                    <div class="p-6">
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" id="btn" onclick="Enviar()" class="btn btn-success" style="padding: 0px 20px;">Enviar</button>
                                    </div>
                                    <div class="p-6">
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="listapedidostrabajador.htm" type="button" class="btn btn-dark" style="padding: 10px 20px; text-decoration: none; width: 100%; height: 48px;">Cancelar</a>
                                    </div>
                                </div>
                            </center>
                        </fieldset>

                    </div>
                </div>
            </div>

        </div>
        <br>
        <br>

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
                var fechae = formato($("#fechae").val());

                //alert(formato($("#fechaentrega").val()));
                $.ajax({
                    type: 'POST',
                    url: 'generarcotizacion.htm',
                    data: {
                        'idPedido': $("#idPedido").val(),
                        'fechaEmision': $("#txtfechaactual").val(),
                        'fechae': fechae,
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
            function formato(texto) {
                return texto.replace(/^(\d{4})-(\d{2})-(\d{2})$/g, '$3/$2/$1');
            }

        </script>
        
        <script>
            $(function () {
                $("#btn").on("click", function () {
                    $("#formulario").validate({
                        rules:
                                {
                                    subtotal: {required: true, minlength: 5, maxlength: 50, numbersonly: true}
                                    

                                },
                        messages:
                                {
                                    subtotal: {required: 'El campo es requerido', numbersonly: 'Por favor, solo ingrese números'}
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