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

                                <h3> R.U.C. N° 20111807958 </h3>
                                <h3><strong>Factura</strong></h3>
                                <div class="row" id="ngui">
                                    N° &nbsp;&nbsp;&nbsp;<input type="text"style="background: #F0E9FF;"  id="idCotizacion">&nbsp;-&nbsp;0000041
                                </div>

                            </fieldset>
                        </div>
                    </div>

                </div>

                <br>

                <div class="card-body">

                    <form method="post" modelAttribute="cliente" id="formulario">

                        <input type="hidden" name="idPedido" id="idPedido" value="${idPedido}">

                        <center>
                            <div class="card-title"><h2><strong>Datos del Cliente</strong></h2></div>
                        </center>

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
                                    <input type="text" style="background: #F0E9FF;" id="txtfechaactual" name="fechaEmision"/>
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

                        <br>

                        <center><h3><strong>Descripción</strong></h3></center>

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
                                            <td ><input type="text" name="subTotal" style="background: #F0E9FF;" id="subtotal" class="monto" style="text-align: center;" onkeyup="sumar()"/></td>

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
                                            <textarea style="background: #F0E9FF;" id="imp" name="imp"></textarea> 

                                        </div>
                                    </div>
                                    <div class="d-flex flex-row" >
                                        <div class="p-6">
                                            <label for="igv" >IGV(%18):</label>
                                        </div>
                                        <div class="p-6">
                                            <textarea style="background: #F0E9FF;" id="igv" name="igv"></textarea>
                                        </div>	
                                    </div>
                                    <div class="d-flex flex-row" >
                                        <div  class="p-6">
                                            <label for="total" >Total(S/):</label>
                                        </div>
                                        <div  class="p-6">
                                            <textarea style="background: #F0E9FF;" id="total" name="total"></textarea>

                                        </div>	
                                    </div>
                                </fieldset>

                            </div>
                        </div>

                        <div class="col">
                            <label for="observacion" >Observaciones:</label>
                            <textarea name="observacion" style="background: #F0E9FF;" id="observacion" ></textarea>

                        </div>

                        <fieldset id="fieldset4" style="border: 1px solid gray">
                            <legend><p>¿Qué acción desea realizar?</p></legend>
                            <div class="row" id="fila8">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" id="btn" onclick="Enviar()" class="btn btn-success" >Enviar</button>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-outline-secondary">Cancelar</button>
                            </div>
                        </fieldset>

                    </form>
                </div>

            </div>


        </div>

        <script>
            $(function () {
                $("#btn").on("click", function () {
                    $("#formulario").validate({
                        rules:
                                {
                                    razon: {required: true, minlength: 3, maxlength: 20, lettersonly: true},
                                    txtfechaactual :{required: true, minlength: 10, maxlength: 10},
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
    </body>
</html>
