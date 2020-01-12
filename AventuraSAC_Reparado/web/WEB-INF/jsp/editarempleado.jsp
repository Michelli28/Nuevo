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
            <a class="logo" href="menu.html">Aventura S.A.C.</a>
            
        </header>
        
        <div class="container md-8">
            
            <div class="card" id="carta">
                
                <div class="card-header">
                    <h3>Editar Empleado</h3>
                </div>
                
                <div class="card-body">
                    <form:form method="post" modelAttribute="empleado" id="formulario">
                        <form:hidden path="idEmpleado" value="${empleado.idEmpleado}" required="required"/>
                        <div class="row">
                            <div class="col-sm-6" style="left:10%;">
                            <label for="dni">DNI:</label>
                            <form:input path="dni" style="height:55%; width:75%;" cssClass="form-control" value="${empleado.dni}"/>
                        </div>
                        <div class="col-sm-6" style="right:2%;">
                            <label for="nombre">Nombre:</label>
                            <form:input path="nombre" style="width:80%;" cssClass="form-control" value="${empleado.nombre}"/>
                        </div>
                        </div>
                        <br>
                        <div class="row">
                        <div class="col-sm-4" style="left:10%;">
                            <label for="apellidoPaterno">Apellido Paterno:</label>
                            <form:input path="apellidoPaterno" style="width:77%;" cssClass="form-control"  value="${empleado.apellidoPaterno}"/>
                        </div>
                        <div class="col-sm-4" style="left:2%;">
                            <label for="apellidoMaterno">Apellido Materno:</label>
                            <form:input path="apellidoMaterno" style="width:80%;" cssClass="form-control" value="${empleado.apellidoMaterno}"/>
                        </div>
                        <div class="col-sm-4" style="right:5%;">
                            <label for="idArea">Área:</label>
                            <form:select path="idArea.idArea" style="width:79%;" id="idArea">
                                <c:forEach items="${lista}" var="x">
                                    <c:if test="${x.idArea == empleado.idArea.idArea}">
                                        <option value="${x.idArea}" selected="selected" required="required">${x.detalle}</option>
                                    </c:if>
                                    <c:if test="${x.idArea != empleado.idArea.idArea}">
                                        <option value="${x.idArea}">${x.detalle}</option>
                                    </c:if>
                                </c:forEach>
                            </form:select > 
                        </div>
                        </div>
                        <br>
                        <div class="row">
                        <div class="col-sm-6" style="left:10%;">
                            <label for="idCargo">Cargo:</label>
                            <form:select path="idCargo.idCargo" style="width:75%;" id="idCargo">
                                <c:forEach items="${listaCargo}" var="x">
                                    <c:if test="${x.idCargo == empleado.idCargo.idCargo}">
                                        <option value="${x.idCargo}" selected="selected" required="required">${x.detalle}</option>
                                    </c:if>
                                    <c:if test="${x.idCargo != empleado.idCargo.idCargo}">
                                        <option value="${x.idCargo}">${x.detalle}</option>
                                    </c:if>
                                </c:forEach>
                            </form:select > 
                        </div>
                        <div class="col-sm-6" style="right:2%;">
                            <label for="telefono">Teléfono:</label>
                            <form:input path="telefono" style="width:80%;" cssClass="form-control"  value="${empleado.telefono}"/>
                        </div>
                        </div>
                        
                        <form:hidden path="usuario" value="${empleado.usuario}" />
                        <form:hidden path="clave" value="${empleado.clave}" />
                        
                        <center>
                            <div id="botones">
                                <fieldset id="fiel">
                                    <legend>¿Qué acción desea realizar?</legend>
                                    <input type="submit" class="btn btn-primary"  value="Guardar" id="btn">
                                    <a class="btn btn-secondary" href="empleados.htm" role="button">Regresar</a>
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
                                    nombre: {required: true, maxlength: 25, lettersonly: true},
                                    apellidoPaterno: {required: true, minlength: 5, maxlength: 20, lettersonly: true},
                                    apellidoMaterno: {required: true, minlength: 5, maxlength: 10, lettersonly: true},
                                    telefono: {required: true, minlength: 7, maxlength: 9, numbersonly: true},
                                    usuario: {required: true, minlength: 5, maxlength: 20},
                                    clave: {required: true, minlength: 7, maxlength: 9, numbersonly: true},
                                },
                        messages:
                                {
                                    dni: {required: 'El campo es requerido', minlength: 'El mínimo permitido son 8 caracteres', maxlength: 'El máximo permitido son 8 caracteres', numbersonly: 'Porfavor, solo ingrese números'},
                                    nombre: {required: 'El campo es requerido', maxlength: 'El máximo permitido son 25 caracteres', lettersonly: 'Porfavor, solo ingrese letras'},
                                    apellidoPaterno: {required: 'El campo es requerido', minlength: 'El mínimo permitido son 5 caracteres', maxlength: 'El máximo permitido son 20 caracteres', lettersonly: 'Porfavor, solo ingrese letras'},
                                    apellidoMaterno: {required: 'El campo es requerido', minlength: 'El mínimo permitido son 5 caracteres', maxlength: 'El máximo permitido son 20 caracteres', lettersonly: 'Porfavor, solo ingrese letras'},
                                    telefono: {required: 'El campo es requerido', minlength: 'El mínimo permitido son 7 caracteres para teléfono',
                                        maxlength: 'El máximo permitido son 9 caracteres para celular',
                                        numbersonly: 'Por favor, solo ingrese números'},
                                    usuario: {required: 'El campo es requerido', minlength: 'El mínimo permitido son 20 caracteres',
                                        maxlength: 'El máximo permitido son 5 caracteres'},
                                    clave: {required: 'El campo es requerido', minlength: 'El mínimo permitido son 7 caracteres', maxlength: 'El máximo permitido son 9 caracteres',
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
            });
        </script>
        
        
    </body>
</html>

