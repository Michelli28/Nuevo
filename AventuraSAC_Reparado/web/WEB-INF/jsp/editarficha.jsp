
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
    <head>
        <title>Registrar Pedido</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <link href="<c:url value="webapp/resources/theme1/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/EstilosFicha.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/main.css" />" rel="stylesheet" />
        <script src="<c:url value="webapp/resources/theme1/js/jquery.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="webapp/resources/theme1/js/jquery.minicolors.js"/>"></script>
        <link type="text/css" rel="stylesheet" href="<c:url value="webapp/resources/theme1/css/jquery.minicolors.css"/>" />
        <script type="text/javascript" src="<c:url value="webapp/resources/theme1/js/jquery.modal.js"/>"></script>
        <link type="text/css" rel="stylesheet" href="<c:url value="webapp/resources/theme1/css/jquery.modal.css"/>" />


    </head>
    <body>
             <header id="header">
            <a class="logo" href="index.html">Aventura S.A.C.</a>
            <nav>
                <a href="#menu">Menu</a>
            </nav>
        </header>
        <div class="container m-8" >

            <div class="card"  id="carta" style="margin: 6% auto;">
                <form:form method="post" modelAttribute="fichatecnica" id="formulario" >

                    <div class="card-header" id="cardheader">
                        <br>
                        <center><h2><strong>Ficha Técnica</strong></h2></center>
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
                                            <form:hidden path="idFicha" value="${fichatecnica.idFicha}" />
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

                        <center><h3 style="font-family: Segou IU; font-size: 20px;"><strong>Ingresar datos de la Prenda</strong></h3></center>


                        <form:hidden path="idEstado" id="fentregaI" value="${fichatecnica.idEstado}"/>


                        <div class="row" style="text-align: left;">
                            <div class="col-sm-4">
                                <label for="descripcion">Descripción:</label>
                                <form:input path="descripcion" style="background: #F0E9FF;" cssClass="form-control"  value="${fichatecnica.descripcion}"/>
                            </div>
                            <div class="col-sm-4">
                                <label for="idTipoModelo" >Modelo: </label>
                                <form:select path="idTipoModelo.idTipoModelo" id="idTipoModelo">
                                    <c:forEach items="${listamodelo}" var="x">
                                        <c:if test="${x.idTipoModelo == fichatecnica.idTipoModelo.idTipoModelo}">
                                            <option value="${x.idTipoModelo}" selected="selected">${x.nombre}</option>
                                        </c:if>
                                        <c:if test="${x.idTipoModelo != fichatecnica.idTipoModelo.idTipoModelo}">
                                            <option value="${x.idTipoModelo}">${x.nombre}</option>
                                        </c:if>
                                    </c:forEach>
                                </form:select>
                            </div>
                            <div class="col-sm-4">	
                                <label for="idTipo">Tela:</label>
                                <form:select path="idTipo.idTipo" id="idTipo">
                                    <c:forEach items="${tipotelas}" var="x">
                                        <c:if test="${x.idTipo == fichatecnica.idTipo.idTipo}">
                                            <option value="${x.idTipo}" selected="selected">${x.nombre}</option>
                                        </c:if>
                                        <c:if test="${x.idTipo != fichatecnica.idTipo.idTipo}">
                                            <option value="${x.idTipo}">${x.nombre}</option>
                                        </c:if>
                                    </c:forEach>
                                </form:select>
                            </div>
                        </div>
                        <br>
                        <div class="row" style="text-align: left;">  
                            <div class="col-sm-4"> 
                                <label for="etiqueta"><strong>Etiqueta:</strong></label>
                                <form:input path="etiqueta" style="background: #F0E9FF;" cssClass="form-control" value="${fichatecnica.etiqueta}"/>
                            </div>
                            <div class="col-sm-4">
                                <label for="idTalla"><strong>Talla:</strong></label>
                                <form:select path="idTalla.idTalla" id="idTalla">
                                    <c:forEach items="${listatallas}" var="x">
                                        <c:if test="${x.idTalla == fichatecnica.idTalla.idTalla}">
                                            <option value="${x.idTalla}" selected="selected">${x.nombre}</option>
                                        </c:if>
                                        <c:if test="${x.idTalla != fichatecnica.idTalla.idTalla}">
                                            <option value="${x.idTalla}">${x.nombre}</option>
                                        </c:if>
                                    </c:forEach>
                                </form:select>
                            </div>
                            <div class="col-sm-4">
                                <label for="cantidad" >Cantidad:</label>
                                <form:input path="cantidad"  style="background: #F0E9FF;" cssClass="form-control" value="${fichatecnica.cantidad}"/>
                            </div>
                        </div>
                        <br>
                        <div class="row" style="text-align: left;">  
                            <div class="col-sm-4"> 
                                <label for="filename"><strong>Imagen:</strong></label>
                                <input type="file" name="filename"/>
                            </div>
                        </div>
                        <hr style="border: 1px solid gray">

                        <center>
                            <div class="card" id="carta2" >
                                <div class="card-body" id="body2">
                                    <h5 class="card-title">Combinación de colores:</h5>
                                    <h6 class="card-subtitle mb-2 text-muted">Seleccionar 1 color como mínimo..</h6>
                                    <div class="d-flex flex-row">
                                        <div  class="p-4">
                                            <label for="color1" >Color 1:</label>
                                            <input type="text" name="color1" id="c1" style="height: 35px;"  value="${fichatecnica.color1}" data-wheelcolorpicker/>
                                            <script>
                                                $(function () {

                                                    $('#c1').minicolors();

                                                });

                                            </script>

                                        </div>

                                        <div  class="p-4">

                                            <label for="color2" >Color 2:</label>
                                            <input name="color2" type="text" id="c2" style="height: 35px;" value="${fichatecnica.color2}" data-wheelcolorpicker/>

                                            <script>
                                                $(function () {

                                                    $('#c2').minicolors();

                                                });
                                            </script>

                                        </div>

                                        <div  class="p-4">

                                            <label for="color3" >Color 3:</label>
                                            <input name="color3" type="text" id="c3" style="height: 35px;" value="${fichatecnica.color3}" data-wheelcolorpicker/>

                                            <script>
                                                $(function () {

                                                    $('#c3').minicolors();

                                                });
                                            </script>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </center>
                        <hr style="border: 1px solid gray">
                        <center>
                            <input type="submit" class="btn btn-dark"  value="Registrar" id="btn" style="width: 200px; height: 50px; ">
                            <a class="btn btn-dark" style="text-decoration: none; width: 200px; height: 50px; padding: 13px; margin: 0px 10px" href="nuevopedido.htm">Regresar</a>
                        </center>


                    </div>
                </form:form>
            </div>

        </div>
        <script>
            $(function () {
                $("#btn").on("click", function () {
                    $("#formulario").validate({
                        rules:
                                {
                                    descripcion: {required: true, minlength: 5, maxlength: 50, lettersonly: true},
                                    cantidad: {required: true, numbersonly: true},
                                    etiqueta: {required: true, minlength: 3, maxlength: 10}

                                },
                        messages:
                                {
                                    descripcion: {required: 'El campo es requerido', minlength: 'El mínimo permitido son 5 caracteres',
                                        maxlength: 'El máximo permitido son 50 caracteres', lettersonly: 'Porfavor, solo letras'},
                                    cantidad: {required: 'El campo es requerido', numbersonly: 'Por favor, solo ingrese números'},
                                    etiqueta: {required: 'El campo es requerido', minlength: 'El mínimo permitido son 3 caracteres',
                                        maxlength: 'El máximo permitido son 10 caracteres', lettersonly: 'Porfavor, solo letras'}
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



