
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

            <div class="card" id="carta">     

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
                                            <input type="text" style="background: #F0E9FF;" name="idFicha">
                                        </div>
                                        <div class="p-3">
                                            -&nbsp;0000041
                                        </div>
                                    </div>

                                </fieldset>
                            </div>
                        </div>
                    </div>

                    <div class="card-body" style="top: 5%;">

                        <center><h2><strong>Ingresar datos de la Prenda</strong></h2></center>
                        <br>

                        <div class="row" style="text-align: left;">
                            <div class="col-sm-4">
                                <label for="descripcion">Descripción:</label>
                                <form:input path="descripcion" style="background: #F0E9FF;" cssClass="form-control" placeholder="Ingresar descripcion" />
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
                                <form:input path="etiqueta" style="background: #F0E9FF;" cssClass="form-control" placeholder="Ingresar nombres de etiqueta"/>
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
                                <form:input path="cantidad"  style="background: #F0E9FF;" cssClass="form-control" placeholder="Ingresar cantidad"/>
                            </div>
                        </div>
                        <div>
                            <label for="cantidad" >Muestra:</label>
                            <button type="button" >Adjuntar</button>

                            <input type="file" name="file" value="Subir"/>
                        </div>

                        <hr style="border: 1px solid gray">

                        <div class="row">
                            <div class="col-sm-5" style="right: 15%;">
                                <div class="d-flex flex-row">
                                    <div  class="p-6">
                                        <div class="card" id="carta2" >
                                            <div class="card-body" id="body2">
                                                <h5 class="card-title">Combinación de colores:</h5>
                                                <h6 class="card-subtitle mb-2 text-muted">Seleccionar..</h6>
                                                <div class="d-flex flex-row">
                                                    <div  class="p-3">
                                                        <label for="color1" >Color 1:</label>
                                                        <form:input path="color1" id="c1" style="height: 35px;" cssClass="form-control" value="ffff00"/>
                                                        <script>
                                                            $(function () {

                                                                $('#c1').minicolors();

                                                            });

                                                        </script>

                                                    </div>

                                                    <div  class="p-3">

                                                        <label for="color2" >Color 2:</label>
                                                        <form:input path="color2" id="c2" style="height: 35px;" value="00ffff" cssClass="form-control"/>

                                                        <script>
                                                            $(function () {

                                                                $('#c2').minicolors();

                                                            });
                                                        </script>

                                                    </div>

                                                    <div  class="p-3">

                                                        <label for="color3" >Color 3:</label>
                                                        <form:input path="color3" id="c3" style="height: 35px;" value="ff00ff" cssClass="form-control"/>

                                                        <script>
                                                            $(function () {

                                                                $('#c3').minicolors();

                                                            });
                                                        </script>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div>
                             
                                    <form:select path="hidden" id="fentregaI" >
                                        <c:forEach items="${estado}" var="x">
                                            <option value="${x.idEstado}">${x.nombre}</option>
                                        </c:forEach>
                                    </form:select>
                           
                            </div>
                        </div>
                        <hr style="border: 1px solid gray">
                        <center>
                            <input type="submit" class="btn btn-dark"  value="Registrar" id="btn">
                            <a class="btn btn-dark" style="text-decoration: none;" href="pedidos.htm">Regresar</a>
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


