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
            <a class="logo" href="MenuPrincipalTrabajador.html">Aventura S.A.C.</a>

        </header>
        <div class="container md-8">
            <div class="card" id="carta">
                <div class="card-header">
                    <h3>Editar Proveedor</h3>
                </div>
                <div class="card-body">
                    <form:form method="post" modelAttribute="proveedor" id="formulario">

                        <form:hidden path="idProveedor" value="${proveedor.idProveedor}" />
                        <div class="row">
                        <div class="col-sm-6" style="left:10%">
                            <label for="razonSocial">Razón Social:</label>
                            <form:input path="razonSocial" style="width:80%;" cssClass="form-control" value="${proveedor.razonSocial}"/>
                        </div>

                        <div class="col-sm-6" style="right:2%">
                            <label for="ruc">RUC:</label>
                            <form:input path="ruc" style="width:80%;" cssClass="form-control" value="${proveedor.ruc}"/>
                        </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-sm-4" style="left:10%">
                            <label for="idDistrito">Distrito:</label>
                            <form:select path="idDistrito.idDistrito" style="width:80%;" id="idDistrito">
                                <c:forEach items="${listaDistrito}" var="x">
                                    <c:if test="${x.idDistrito == proveedor.idDistrito.idDistrito}">
                                        <option value="${x.idDistrito}" selected="selected" required="required">${x.detalle}</option>
                                    </c:if>
                                    <c:if test="${x.idDistrito != proveedor.idDistrito.idDistrito}">
                                        <option value="${x.idDistrito}" required="required">${x.detalle}</option>
                                    </c:if>
                                </c:forEach>
                            </form:select > 
                        </div>
                        <div class="col-sm-4" style="left:2%">
                            <label for="direccion">Dirección:</label>
                            <form:input path="direccion" style="width:80%;" cssClass="form-control" value="${proveedor.direccion}"/>
                        </div>
                        <div class="col-sm-4" style="right:5%">
                            <label for="contacto">Contacto:</label>
                            <form:input path="contacto" style="width:79%;" cssClass="form-control"  value="${proveedor.contacto}"/>
                        </div>
                        </div>
                        <br>
                        <div class="row">
                        <div class="col-sm-6" style="left:10%">
                            <label for="telefono">Teléfono:</label>
                            <form:input path="telefono" style="width:80%;" cssClass="form-control"  value="${proveedor.telefono}"/>
                        </div>

                        <div class="col-sm-6" style="right:2%">
                            <label for="correo">Correo:</label>
                            <form:input path="correo" style="width:80%;" cssClass="form-control"  value="${proveedor.correo}"/>
                        </div>
                        </div>
                        
                        <center>
                            <div id="botones">
                                <fieldset id="fiel">
                                    <legend>¿Qué acción desea realizar?</legend>
                                    <input type="submit" class="btn btn-primary"  value="Guardar" id="btn">
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
                                    ruc: {required: true, minlength: 11, maxlength: 11, numbersonly: true},
                                    direccion: {required: true, minlength: 15, maxlength: 50},
                                    contacto: {required: true, minlength: 10, maxlength: 20},
                                    correo: {required: true, email: true, minlength: 15, maxlength: 30}
                                }, 
                        messages:
                                {
                                    dni: {required: 'El campo es requerido', minlength: 'El mínimo permitido son 8 caracteres' , maxlength: 'El máximo permitido son 8 caracteres', numbersonly: 'Porfavor, solo ingrese números'},
                                    razonSocial: {required: 'El campo es requerido', minlength: 'El mínimo permitido son 5 caracteres', maxlength: 'El máximo permitido son 30 caracteres', lettersonly: 'Porfavor, solo ingrese números'},
                                    ruc: {required: 'El campo es requerido', minlength: 'El minimo permitido son 11 caracteres',maxlength: 'El máximo permitido son 11 caracteres', numbersonly: 'Porfavor, solo ingrese números'},
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
    </body>
</html>

