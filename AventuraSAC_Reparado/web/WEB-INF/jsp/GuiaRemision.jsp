<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>
<html>
    <head>
        <title>Guía de Remisión</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <link href="<c:url value="webapp/resources/theme1/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/estilosguia.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/main.css" />" rel="stylesheet" />
        <script src="<c:url value="webapp/resources/theme1/js/jquery.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="webapp/resources/theme1/js/jquery.validate.min.js"/>"></script>


    </head>
    <body id="body" style="background-color:rgba(100,0,0, 0.2);">

        <header id="header">
            <a class="logo" href="menu.htm">Aventura S.A.C.</a>
            <nav>
                <a href="login.htm">Salir</a>
            </nav>
        </header>

        <div class="container md-8">
            <div style="background-color:rgba(100,0,0, 0.4);">

                <div class="card"  id="carta" style="margin-left:10px;">


                    <form:form method="post" modelAttribute="guiaremision" id="formulario">

                        <div class="card-header" id="cardheader" style="background-color:rgba(100,0,0, 0.4);">
                            

                            <div class="d-flex flex-row">

                                <div  class="p-4" id="img">

                                    <img src="webapp/resources/theme1/images/logo.PNG">

                                </div>

                                <div class="p-4"  id="escritos">
                                    <p tyle="font-family: arial;">Cal. Mercaderes Nro. 164 Dpto. 203 </p>
                                    <p tyle="font-family: arial;">Urb. Las Gardenias (Alt. Cuadra 50 de la Avenida Benavides)</p>

                                </div>

                                <div class="p-4" id="fieldset">

                                    <fieldset style="border:1px solid black" id="fld" >

                                        <h5><strong> R.U.C. N° 20111807958 </strong></h5>
                                        <h2 style="font-family: cambria; font-size: 25px;"><strong>Guía de Remisión</strong></h2>
                                        <div class="d-flex flex-row" id="ngui">
                                            <div class="p-3" style="font-family: arial;" >
                                                N° 
                                            </div>
                                            <div class="p-3">
                                                <input type="text" style="background: #F0E9FF;" name="idGuiaRemision">
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
                            <br>
                            <div class="row">
                                <div class="col-sm-4">
                                    <label for="fechaEmision">Fecha Emision:</label>
                                    <form:input path="fechaEmision"  style="background: #F0E9FF;" cssClass="form-control" placeholder="Ingresar fecha" />
                                </div>
                                <div class="col-sm-4">	
                                    <label for="puntoLlegada">Punto de Llegada:</label>
                                    <form:input path="puntoLlegada"  style="background: #F0E9FF;" cssClass="form-control" placeholder="Ingresar lugar de llegada" />
                                </div>	
                                <div class="col-sm-4">  
                                    <label for="destinatario">Destinatario:</label>
                                    <form:input path="destinatario"  style="background: #F0E9FF;" cssClass="form-control" placeholder="Ingresar Destinatario" />
                                </div>
                            </div>

                            <hr style="border: 1px solid gray">
                            <fieldset style="border: 1px solid gray" id="datostransporte">
                                <legend><strong>Ingresar datos</strong></legend>
                                <div class="row">
                                    <div class="col-sm-6">
                                        <label for="rUCtransporte" >RUC Transporte:</label>
                                        <form:input path="rUCtransporte" style="background: #F0E9FF;" cssClass="form-control" placeholder="RUC Transporte" />
                                    </div>
                                    <div class="col-sm-6">
                                        <label for="nombreTransportista">Nombre de Chofer:</label>
                                        <form:input path="nombreTransportista" style="background: #F0E9FF;" cssClass="form-control" placeholder="Nombre Chofer" />
                                    </div>
                                </div>
                                <br>
                                <div class="row">
                                    <div class="col-sm-6">		
                                        <label for="marcayPlaca">Marca y Placa:</label>
                                        <form:input path="marcayPlaca" style="background: #F0E9FF;" cssClass="form-control" placeholder="Marca y Placa" />
                                    </div>
                                    <div class="col-sm-6">      
                                        <label for="licencia">Licencia:</label>
                                        <form:input path="licencia" style="background: #F0E9FF;" cssClass="form-control" placeholder="Licencia"/>
                                    </div>
                                </div>
                            </fieldset>
                        </form:form>

                        <hr style="border: 1px solid gray ">

                        <form:form method="post" modelAttribute="guiaremisionDetalle">          
                            <fieldset style="border: 1px solid gray; width: 60%; margin: 0% 20%; padding: 2% 0% 0% 4%;">
                                <div class="row">
                                    <div class="col-sm-4">
                                        <label for="cantidad">Cantidad:</label>
                                        <input type="text" style="background: #F0E9FF;" id="cantidad" placeholder="Ingrese cantidad" />
                                    </div>
                                    <div class="col-sm-7">	
                                        <label for="descripcion">Descripcion:</label>
                                        <textarea type="text" name="descripcion" id="descripcion" style="height: 45%;background: #F0E9FF;" placeholder="Ingresar descripción" cssClass="form-control"></textarea>
                                    </div>	
                                </div>
                            </fieldset>
                        </form:form>


                        <hr style="border: solid gray 1px;">

                        <center>
                            <input type="button" class="btn btn-dark"value="Guardar" onclick="Enviar()" style="margin-right: 15px; width: 18%;"/>
                            <a class="btn btn-dark" href="menualmacen.htm" style="text-decoration: none;height: 47px; padding: 1%; width: 18%;" role="button" >Regresar al Menú</a> 
                        </center>
                    </div>
                </div>
            </div>
        </div>
        <br>
        <br>
        

        <script>
            $(function () {
                $("#btn").on("click", function () {
                    $("#formulario").validate({
                        rules:
                                {
                                    puntoLlegada: {required: true, maxlentgh: 50},
                                    destinatario: {required: true, minlength: 10, maxlentgh: 60},
                                    rUCtransporte: {required: true, minlength: 11, maxlength: 11, numbersonly: true},
                                    nombreTransportista: {required: true, minlength: 5, maxlentgh: 50, lettersonly: true},
                                    marcayPlaca: {required: true, maxlength: 30},
                                    licencia: {required: true, maxlength: 20},
                                    cantidad: {required: true, numbersonly: true},
                                    descripcion: {required: true, minlength: 5, maxlentgh: 60}
                                },
                        messages:
                                {
                                    puntoLlegada: {required: 'El campo es requerido', maxlength: 'El máximo permitido son 50 caracteres'},
                                    destinatario: {required: 'El campo es requerido', minlength: 'El minimo permitido son 10 caracteres', maxlength: 'El máximo permitido son 60 caracteres'},
                                    rUCtransporte: {required: 'El campo es requerido', minlength: 'El minimo permitido son 11 caracteres', maxlength: 'El máximo permitido son 11 caracteres', numbersonly: 'Porfavor, solo ingrese números'},
                                    nombreTransportista: {required: 'El campo es requerido', minlength: 'El minimo permitido son 5 caracteres', maxlength: 'El máximo permitido son 50 caracteres', lettersonly: 'Porfavor, solo letras'},
                                    marcayPlaca: {required: 'El campo es requerido', maxlength: 'El máximo permitido son 60 caracteres'},
                                    licencia: {required: 'El campo es requerido', maxlength: 'El máximo permitido son 60 caracteres'},
                                    descripcion: {required: 'El campo es requerido', minlength: 'El mínimo permitido son 5 caracteres',
                                        maxlength: 'El máximo permitido son 30 caracteres'},
                                    cantidad: {required: 'El campo es requerido', numbersonly: 'Por favor, solo ingrese números'},

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
