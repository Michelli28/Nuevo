
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
        <script src="<c:url value="webapp/resources/theme1/js/jquery.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="webapp/resources/theme1/js/jquery.validate.min.js"/>"></script>
        <link href="<c:url value="webapp/resources/theme1/css/main.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/fonts/font.awesome.css" />" rel="stylesheet">

    </head>

    <body id="bodys">

        <header id="header">
            <a class="logo" href="menu.htm">Aventura S.A.C.</a>
            <nav>
                <a href="login.htm">Salir</a>
            </nav>
        </header>

        <div class="container md-8">
            <div class="card" id="carta">
                <div class="card-header">
                    <h3>Nuevo Cliente</h3>
                </div>
                <div class="card-body">
                    <form:form method="post" modelAttribute="cliente" id="formulario">
                        <div class="row" id="fila1">
                            <div class="col-sm-4">
                                <label for="razonSocial" class="fal fa-clipboard-list">Razón Social:</label>
                                <form:input path="razonSocial" cssClass="form-control"  />
                            </div>
                            <div class="col-sm-4" id="ruc">
                                <label for="ruc">RUC:</label>
                                <form:input path="ruc" cssClass="form-control"/>
                            </div>
                            <div class="col-sm-4">
                                <label for="idDistrito">Distrito:</label>
                                <form:select path="idDistrito.idDistrito" id="idDistrito" >
                                    <c:forEach items="${listaDistrito}" var="x">
                                        <option value="${x.idDistrito}" required="required">${x.detalle}</option>
                                    </c:forEach>
                                </form:select>
                            </div>
                        </div>
                        <br>
                        <div class="row" id="fila2">
                            <div class="col-sm-4">
                                <label for="direccion">Dirección:</label>
                                <form:input path="direccion" cssClass="form-control" />
                            </div>
                            <div class="col-sm-4" id="telefono">
                                <label for="telefono">Teléfono:</label>
                                <form:input path="telefono" cssClass="form-control"/>
                            </div>
                            <div class="col-sm-4">
                                <label for="broker">Broker:</label>
                                <form:input path="broker" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="row" id="fila3">
                            <div class="col-sm-4">
                                <label for="correo">Correo:</label>
                                <form:input path="correo" cssClass="form-control" />
                            </div>
                            <div class="col-sm-4">
                                <label for="usuario">Usuario:</label>
                                <form:input path="usuario"  cssClass="form-control" />
                            </div>
                            <div class="col-sm-4">
                                <label for="clave">Clave:</label>
                                <form:password path="clave" cssClass="form-control" maxlength="15" required="required" placeholder="*********" />
                            </div>
                        </div>

                        <center>
                            <div id="botones">
                                <fieldset id="fiel">
                                    <legend>¿Qué acción desea realizar?</legend>
                                    <input type="submit" class="btn btn-primary" value="Registrar" id="btn">
                                    <a class="btn btn-secondary" href="clientes.htm" role="button">Regresar</a>
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
                                    razonSocial: {required: true, minlength: 3, maxlength: 20, alfanumOespacio: true},
                                    ruc: {required: true, minimo8: true},
                                    direccion: {required: true, maxlength: 30, alfanumOespacio: true},
                                    telefono: {required: true,  minimo7: true},
                                    broker: {required: true, minlength: 15, maxlength: 30, alfanumOespacio: true},
                                    correo: {required: true, email: true, minlength: 15, maxlength: 30},
                                    usuario: {required: true, minlength: 5, maxlength: 20, alfanumOespacio: true},
                                    clave: {required: true, minlength: 7, maxlength: 9, numbersonly: true, alfanumOespacio: true}

                                },
                        messages:
                                {
                                    razonSocial: {required: 'El campo es requerido', minlength: 'El mínimo permitido son 3 caracteres',
                                        maxlength: 'El máximo permitido son 20 caracteres'},
                                     ruc: {required: 'El campo es requerido', minimo8: 'Deben ser 11 dígitos'},
                                    direccion: {required: 'El campo es requerido', maxlength: 'El máximo permitido son 30 caracteres'},
                                    telefono: {required: 'El campo es requerido', minimo7: 'Deben ser 7 dígitos o 9 dígitos'},
                                    broker: {required: 'El campo es requerido', minlength: 'El mínimo permitido son 15 caracteres',
                                        maxlength: 'El máximo permitido son 30 caracteres'},
                                    correo: {required: 'El campo es requerido', email: 'El caracter "@" es requerido', minlength: 'El mínimo permitido son 15 caracteres', maxlength: 'El máximo permitido son 30 caracteres'},
                                    usuario: {required: 'El campo es requerido', minlength: 'El mínimo permitido son 20 caracteres',
                                        maxlength: 'El máximo permitido son 5 caracteres'},
                                    clave: {required: 'El campo es requerido', minlength: 'El mínimo permitido son 7 caracteres', maxlength: 'El máximo permitido son 20 caracteres',
                                        numbersonly: 'Por favor, solo ingrese números'}
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
                    return this.optional(element) || /^(?=.*\d)[0-9\d]{11,11}$/i.test(value);
                }, "Minimo 8 caracteres, con al menos una letra y un número.");
                jQuery.validator.addMethod("minimo7", function (value, element) {
                    return this.optional(element) || /^(?=.*\d)[0-9\d]{7,9}$/i.test(value);
                },
            });

        </script>

    </body>
</html>
