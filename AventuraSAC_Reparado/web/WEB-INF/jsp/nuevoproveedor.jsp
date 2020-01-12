<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="<c:url value="webapp/resources/theme1/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/crud.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/main.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/fonts/font.awesome.css" />" rel="stylesheet">
        <script src="<c:url value="webapp/resources/theme1/js/jquery.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="webapp/resources/theme1/js/jquery.validate.min.js"/>"></script>
    </head>
    <body id="bodys">
        
        <header id="header">
            <a class="logo" href="menu.htm">Aventura S.A.C.</a>
            
        </header>

        <div class="container md-8">
            <div class="card" id="carta">
                <div class="card-header">
                    <h3>Nuevo Proveedor</h3>
                </div>
                <div class="card-body">
                    
                    <form:form method="post" modelAttribute="proveedor" id="formulario">
                        
                        <div class="row" id="fila1">
                            <div class="col-sm-6" style="left:10%;">
                            <label for="razonSocial">Razón Social:</label>
                            <form:input path="razonSocial" style="width:80%;" cssClass="form-control"/>
                            </div>
                            <div class="col-sm-6" style="right: 2%;">
                            <label for="ruc">RUC:</label>
                            <form:input path="ruc" style="width:80%;" cssClass="form-control"/>
                            </div>
                        </div>
                        <br>
                        <div class="row" id="fila2">
                            <div class="col-sm-4" style="left:10%;">                     
                            <label for="idDistrito">Distrito:</label>
                            <form:select path="idDistrito.idDistrito" style="width:80%;" id="idDistrito" >
                                <c:forEach items="${listarDistrito}" var="x" >
                                    <option value="${x.idDistrito}" required="required">${x.detalle}</option>
                                </c:forEach>
                            </form:select>
                            </div>
                            <div class="col-sm-4" style="left:2%;">
                            <label for="direccion">Dirección:</label>
                            <form:input path="direccion" style="width:80%;" cssClass="form-control"/>
                            </div>
                            <div class="col-sm-4" style="right: 5%;">
                            <label for="contacto">Contacto:</label>
                            <form:input path="contacto" style="width:80%;" cssClass="form-control"/>
                            </div>
                        </div>
                            <br>
                            <div class="row">
                                <div class="col-sm-6" style="left:11%;">
                                    <label for="telefono">Teléfono:</label>
                                    <form:input path="telefono" style="width:80%;" cssClass="form-control"/>
                                </div>
                                <div class="col-sm-6" style="right:1%;">
                                    <label for="correo">Correo:</label>
                                    <form:input path="correo" style="width:76%;" cssClass="form-control"/>
                                </div>
                            </div>
                        
                        <center>
                            <div id="botones">
                                <fieldset id="fiel">
                                    <legend>¿Qué acción desea realizar?</legend>
                                    <input type="submit" class="btn btn-primary"  value="Registrar" id="btn">
                                    <a class="btn btn-secondary" href="proveedores.htm" role="button">Regresar</a>
                                </fieldset>
                            </div>

                        </center>
                    </form:form>
                </div>
            </div>
            <br>
        </div>
        
        <script>
            $(function () {
                $("#btn").on("click", function () {
                    $("#formulario").validate({
                        rules:
                                {
                                    dni: {required: true, minlength: 8, maxlength:8 },
                                    razonSocial: {required: true, minlength: 5, maxlength: 30, lettersonly: true},
                                    ruc: {required: true, maxlength: 11, numbersonly: true},
                                    direccion: {required: true, minlength: 15, maxlength: 50},
                                    contacto: {required: true, minlength: 10, maxlength: 20},
                                    correo: {required: true, email: true, minlength: 15, maxlength: 30}
                                },
                        messages:
                                {
                                    dni: {required: 'El campo es requerido', minlength: 'El mínimo permitido son 8 caracteres' , maxlength: 'El máximo permitido son 8 caracteres', numbersonly: 'Porfavor, solo ingrese números'},
                                    razonSocial: {required: 'El campo es requerido', minlength: 'El mínimo permitido son 5 caracteres', maxlength: 'El máximo permitido son 30 caracteres', lettersonly: 'Porfavor, solo ingrese números'},
                                    ruc: {required: 'El campo es requerido', maxlength: 'El máximo permitido son 11 caracteres', numbersonly: 'Porfavor, solo ingrese números'},
                                    direccion: {required: 'El campo es requerido', minlength: 'El mínimo permitido son 15 caracteres', maxlength: 'El máximo permitido son 50 caracteres'},
                                    telefono: {required: 'El campo es requerido', minlength: 'El mínimo permitido son 7 caracteres para teléfono',
                                        maxlength: 'El máximo permitido son 9 caracteres para celular',
                                        numbersonly: 'Por favor, solo ingrese números'},
                                    correo: {required: 'El campo es requerido', email: 'El caracter "@" es requerido', minlength: 'El mínimo permitido son 15 caracteres', maxlength: 'El máximo permitido son 30 caracteres'}
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

