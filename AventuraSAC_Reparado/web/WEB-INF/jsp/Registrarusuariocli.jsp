

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
        <link href="<c:url value="webapp/resources/theme1/css/registrarusuario.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/main.css" />" rel="stylesheet">
        <script src="<c:url value="webapp/resources/theme1/js/jquery.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="webapp/resources/theme1/js/jquery.validate.min.js"/>"></script>
    </head>
    <body id="bodys">

        <header id="header">
            <a class="logo" href="login.htm">Login</a>
            <!-- <nav>
                <a href="#menu">Menu</a>
            </nav> -->
        </header>

        <!--<nav id="menu">
            <ul class="links">
                <li><a href="menu.htm">Home</a></li>
                <li><a href="quienessomos.htm">Quienes Somos</a></li>
                <li><a href="#">Mantenimientos</a>
                    <ul>
                        <li>
                            <a href="empleados.htm">Empleados</a>
                        </li>
                        <li>
                            <a href="clientes.htm">Cliente</a>
                        </li>
                        <li>
                            <a href="proveedores.htm">Proveedor</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </nav>-->

        <div class="container md-8">
            <div class="card" id="carta">
                <div class="card-header">
                    <h3>Registrar Cliente</h3>
                </div>

                <div class="card-body">

                    <form:form method="post" modelAttribute="cliente" id="formulario">

                        <div class="d-flex flex-row">
                            <div class="form-group" class="p-3" id="razon1">
                                <label for="razonSocial">Razón Social:</label>
                                <form:input path="razonSocial" style="font-family: arial;" placeholder="Ingresar razón social" id="razon" cssClass="form-control" />
                            </div>

                            <div class="form-group" class="p-3" id="ruc2">
                                <label for="ruc">RUC:</label>
                                <form:input path="ruc"  id="ruc" style="font-family: arial;" placeholder="Ingresar ruc" cssClass="form-control" />
                            </div>


                            <div class="form-group" class="p-3" id="dis1">
                                <label for="idDistrito">Distrito:</label>
                                <form:select path="idDistrito.idDistrito" id="idDistrito"  style="font-family: arial;">
                                    <c:forEach items="${listaDistrito}" var="x">
                                        <option value="${x.idDistrito}" required="required">${x.detalle}</option>
                                    </c:forEach>
                                </form:select>
                            </div>


                            <br>
                            <div class="form-group" class="p-3" id="dir1">
                                <div class="form-group">
                                    <label for="direccion">Dirección:</label>
                                    <form:input path="direccion" cssClass="form-control" style="font-family: arial;" placeholder="Ingresar dirección" />
                                </div>
                            </div>
                        </div>
                        <div class="d-flex flex-row">

                            <div class="form-group" class="p-3" id="tel">
                                <label for="telefono">Teléfono:</label>
                                <form:input path="telefono" cssClass="form-control" style="font-family: arial;" id="tel1" placeholder="Ingresar N° de teléfono" />
                            </div>
                            <div class="form-group" class="p-3" id="brok1">
                                <label for="broker">Broker:</label>
                                <form:input path="broker" cssClass="form-control" style="font-family: arial;" placeholder="Ingresar broker" />
                            </div>
                            <div class="form-group" class="p-3" id="corr1">
                                <label for="correo">Correo:</label>
                                <form:input path="correo" cssClass="form-control" style="font-family: arial;" placeholder="Ingresar correo" />
                            </div>
                            <div class="form-group" class="p-3" id="usu">

                                <label for="usuario">Usuario:</label>
                                <form:input path="usuario"  cssClass="form-control" style="font-family: arial;" placeholder="Ingresar nombre de usuario"/>
                            </div>
                        </div>
                        <div class="d-flex flex-row">
                            <div class="form-group" class="p-3" id="clav1">
                                <label for="clave">Clave:</label>
                                <form:password path="clave" cssClass="form-control"  placeholder="*********" />
                            </div>
                        </div>
                        <center>
                            <div id="botones">
                                <fieldset id="fiel">
                                    <legend>¿Qué acción desea realizar?</legend>
                                    <input type="submit" class="btn btn-primary"  value="Registrar" id="btn">
                                    <a class="btn btn-secondary" href="login.htm" role="button">Regresar</a>
                                </fieldset>
                            </div>

                        </center>

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
                                    razonSocial: {required: true, minlength: 3, maxlength: 20, alfanumOespacio: true},
                                    direccion: {required: true, maxlength: 30, alfanumOespacio: true},
                                    telefono: {required: true, minlength: 7, maxlength: 9, numbersonly: true},
                                    broker: {required: true, minlength: 15, maxlength: 30, alfanumOespacio: true},
                                    correo: {required: true, email: true, minlength: 15, maxlength: 30},
                                    usuario: {required: true, minlength: 5, maxlength: 20, alfanumOespacio: true},
                                    clave: {required: true, minlength: 7, maxlength: 9, numbersonly: true, alfanumOespacio: true}

                                },
                        messages:
                                {
                                    razonSocial: {required: 'El campo es requerido', minlength: 'El mínimo permitido son 3 caracteres',
                                        maxlength: 'El máximo permitido son 20 caracteres'},
                                    direccion: {required: 'El campo es requerido', maxlength: 'El máximo permitido son 30 caracteres'},
                                    telefono: {required: 'El campo es requerido', minlength: 'El mínimo permitido son 7 caracteres para teléfono',
                                        maxlength: 'El máximo permitido son 9 caracteres para celular',
                                        numbersonly: 'Por favor, solo ingrese números'},
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
                    return this.optional(element) || /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/i.test(value);
                }, "Minimo 8 caracteres, con al menos una letra y un número.");
                
            });

        </script>
    </body>
</html>
