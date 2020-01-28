<%-- 
    Document   : Ficha1
    Created on : 15/01/2020, 10:29:20 PM
    Author     : CHELLI BONITA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="<c:url value="webapp/resources/theme1/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/EstilosFicha.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/main.css" />" rel="stylesheet" />
        <script src="<c:url value="webapp/resources/theme1/js/jquery.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="webapp/resources/theme1/js/jquery.minicolors.js"/>"></script>
        <link type="text/css" rel="stylesheet" href="<c:url value="webapp/resources/theme1/css/jquery.minicolors.css"/>" />
        <script type="text/javascript" src="<c:url value="webapp/resources/theme1/js/jquery.modal.js"/>"></script>
        <script type="text/javascript" src="<c:url value="webapp/resources/theme1/js/jquery.validate.min.js"/>"></script>


    </head>
    <body id="bodys" style="background-color:rgba(100,0,0, 0.2);">
        <header id="header">
            <a class="logo" href="index.html">Aventura S.A.C.</a>
            <nav>
                <a href="#menu">Menu</a>
            </nav>
        </header>
        
        <div class="container m-8" >
            <div style="background-color:rgba(100,0,0, 0.4);">
                
            <div class="card"  id="carta" style="margin-left:10px;">
                
                <form:form method="post" modelAttribute="fichatecnica" id="formulario" enctype="multipart/form-data">
                    
                    <div class="card-header" id="cardheader" style="background-color:rgba(100,0,0, 0.4);">
                        <br>
                        <div class="d-flex flex-row">

                            <div  class="p-4" id="img">

                                <img src="webapp/resources/theme1/images/logo.PNG">

                            </div>

                            <div class="p-4"  id="escritos">
                                <p>Cal. Mercaderes Nro. 164 Dpto. 203 </p>
                                <p>Urb. Las Gardenias (Alt. Cuadra 50 de la Avenida Benavides)</p>

                            </div>

                            <div class="p-4" id="fieldset">

                                <fieldset style="border:1px solid black" id="fld" >

                                    <h5 style="font-family: arial;"><strong> R.U.C. N° 20111807958 </strong></h5>
                                    <h2 style="font-family: cambria; font-size: 23px;"><strong>Ficha Técnica</strong></h2>
                                    <div class="d-flex flex-row" id="ngui">
                                        <div class="p-3" style="font-family: arial;">
                                            N° 
                                        </div>
                                        <div class="p-3">
                                            <input type="text" style="background: #F0E9FF;" name="idFicha">
                                        </div>
                                        <div class="p-3" style="font-family: arial;">
                                            -&nbsp;0000041
                                        </div>
                                    </div>

                                </fieldset>
                            </div>

                        </div>

                    </div>
                    <div class="card-body">

                        <center><h3 style="font-family: cambria; font-size: 25px;"><strong>Ingresar datos de la Prenda</strong></h3></center>


                        <form:input path="idEstado.idEstado" id="fentregaI" type="hidden" value="1"/>


                        <div class="row" style="text-align: left;">
                            <div class="col-sm-4">
                                <label for="descripcion">Descripción:</label>
                                <form:input path="descripcion" style="background: #F0E9FF;font-family: arial;" cssClass="form-control" placeholder="Ingresar descripcion" />
                            </div>
                            <div class="col-sm-4">
                                <label for="idTipoModelo" >Modelo: </label>
                                <form:select path="idTipoModelo.idTipoModelo" id="idTipoModelo">
                                    <c:forEach items="${listamodelo}" var="x">
                                        <option value="${x.idTipoModelo}">${x.nombre}</option>
                                    </c:forEach>
                                </form:select>
                            </div>
                            <div class="col-sm-4">	
                                <label for="idTipo">Tela:</label>
                                <form:select path="idTipo.idTipo" id="idTipo">
                                    <c:forEach items="${tipotelas}" var="x">
                                        <option value="${x.idTipo}">${x.nombre}</option>
                                    </c:forEach>
                                </form:select>
                            </div>
                        </div>
                        <br>
                        <div class="row" style="text-align: left;">  
                            <div class="col-sm-4"> 
                                <label for="etiqueta"><strong>Etiqueta:</strong></label>
                                <form:input path="etiqueta" style="background: #F0E9FF;font-family: arial;" cssClass="form-control" placeholder="Ingresar nombres de etiqueta"/>
                            </div>
                            <div class="col-sm-4">
                                <label for="idTalla"><strong>Talla:</strong></label>
                                <form:select path="idTalla.idTalla" id="idTalla">
                                    <c:forEach items="${listatallas}" var="x">
                                        <option value="${x.idTalla}">${x.nombre}</option>
                                    </c:forEach>
                                </form:select>
                            </div>
                            <div class="col-sm-4">
                                <label for="cantidad" >Cantidad:</label>
                                <form:input path="cantidad"  style="background: #F0E9FF;font-family: arial;" cssClass="form-control" placeholder="Ingresar cantidad"/>
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
                                            <label for="color1" style="font-family: arial;">Color 1:</label>
                                            <form:input path="color1" id="c1" style="height: 35px; font-family: arial;" cssClass="form-control" value="ffff00"/>
                                            <script>
                                                $(function () {

                                                    $('#c1').minicolors();

                                                });

                                            </script>

                                        </div>

                                        <div  class="p-4">

                                            <label for="color2" style="font-family: arial;">Color 2:</label>
                                            <form:input path="color2" id="c2" style="height: 35px; font-family: arial;" value="00ffff" cssClass="form-control"/>

                                            <script>
                                                $(function () {

                                                    $('#c2').minicolors();

                                                });
                                            </script>

                                        </div>

                                        <div  class="p-4">

                                            <label for="color3" style="font-family: arial;">Color 3:</label>
                                            <form:input path="color3" id="c3" style="height: 35px; font-family: arial;" value="ff00ff" cssClass="form-control"/>

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
