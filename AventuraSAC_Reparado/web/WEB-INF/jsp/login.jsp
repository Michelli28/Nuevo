<!------ Include the above in your HEAD tag ---------->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

    <head>

        <title>Aventura S.A.C.</title>
        <link href="<c:url value="webapp/resources/theme1/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/es.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/fonts/font.awesome.css" />" rel="stylesheet">
        <script src="<c:url value="webapp/resources/theme1/js/jquery.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="webapp/resources/theme1/js/jquery.modal.js"/>"></script>
        <script type="text/javascript" src="<c:url value="webapp/resources/theme1/js/jquery.validate.min.js"/>"></script>
        <link type="text/css" rel="stylesheet" href="<c:url value="webapp/resources/theme1/css/jquery.modal.css"/>" />



    </head>

    <!--Coded with love by Mutiullah Samim-->
    <body id="cuerpo">
        <div class="container h-100">
            <div class="d-flex justify-content-center h-100">
                <div class="user_card">
                    <div class="d-flex justify-content-center">
                        <div class="brand_logo_container">

                            <img src="webapp/resources/theme1/images/logo.PNG"  class="brand_logo" alt=""/>
                        </div>
                    </div>
                    <div class="d-flex justify-content-center form_container">
                        <form action="validar.htm" method="post" id="formulario">
                            <div class="input-group mb-3">
                                <div class="input-group-append">
                                    <div class="input-group-text"><i class="fas fa-user"></i></div>
                                </div>
                                <div>
                                    <input type="text" class="form-control input_user" placeholder="username" name="txtusuario" id="txtusuario">
                                </div>
                            </div>
                            <div class="input-group mb-2">
                                <div class="input-group-append">
                                    <div class="input-group-text"><i class="fas fa-key"></i></div>
                                </div>
                                <div>
                                    <input type="password" name="txtclave" class="form-control input_pass" placeholder="password">
                                </div>
                            </div>


                            <div class="d-flex justify-content-center mt-3 login_container">
                                <button type="submit" name="button" class="btn login_btn" id="btn">Login</button>
                            </div>
                        </form>
                    </div>

                    <div class="mt-4">
                        <div class="d-flex justify-content-center links">
                            ¿No tienes una cuenta?  <a href="nuevo.htm" id="registrar1" role="button" > Regístrate</a>
                        </div>
                        <div class="d-flex justify-content-center links" id="reg">
                            <a href="#">Olvidaste tu contraseña?</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>



        <div class="modal fade" id="myModal" role="dialog">
            <div class="modal-dialog" >
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalCenterTitle">Editar Cliente</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">

                        </button>
                    </div>
                    <div class="modal-body">

                        <form id="mod">
                            <input type="text" name="idCliente" id="idcliente"/>

                            <div class="form-group">
                                <label for="razonSocial">Razón Social:</label>
                                <input type="text" name="razonSocial" id="razonSocial" />
                            </div>

                            <div class="form-group">
                                <label for="ruc">RUC:</label>
                                <input type="text" name="ruc"  id="ruc" />
                            </div>

                            <div>
                                <label for="idDistrito">Distrito:</label>
                                <select name="idDistrito.idDistrito" >
                                    <c:forEach items="${listaDistrito}" var="x">
                                        <c:if test="${x.idDistrito == cliente.idDistrito.idDistrito}">
                                            <option value="${x.idDistrito}" selected="selected" id="idDistrito">${x.detalle}</option>
                                        </c:if>
                                        <c:if test="${x.idDistrito != cliente.idDistrito.idDistrito}">
                                            <option value="${x.idDistrito}" id="idDistrito">${x.detalle}</option>
                                        </c:if>
                                    </c:forEach>
                                </select > 
                            </div>

                            <br>
                            <div class="form-group">
                                <label for="direccion">Dirección:</label>
                                <input type="text" name="direccion" id="direccion"  />
                            </div>
                            <div class="form-group">
                                <label for="telefono">Teléfono</label>
                                <input type="text" name="telefono" id="telefono" />
                            </div>
                            <div class="form-group">
                                <label for="correo">Correo:</label>
                                <input type="text" name="correo" id="correo" />
                            </div>
                            <div class="form-group">
                                <label for="broker">Broker:</label>
                                <input type="text" name="broker" id="broker"  />
                            </div>
                            <input type="hidden" name="usuario" value="" />
                            <input type="hidden" name="clave" value="" />

                            <button onclick="registrar()" class="btn btn-primary"  type="button" value="Guardar"></button>
                            <a class="btn btn-secondary" href="clientes.htm" role="button">Regresar</a>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary">Save changes</button>
                    </div>
                </div>
            </div>
        </div>



        <script>
            $('#registrar1').on('click', function () {

                $('#myModal').modal('show');

                var razonSocial = ${'#razonSocial'}.val();
                var ruc = ${'#ruc'}.val();

                var direccion = ${'#direccion'}.val();
                var telefono = ${'#telefono'}.val();
                var correo = ${'#correo'}.val();
                var broker = ${'#broker'}.val();
                var usuario = ${'#usuario'}.val();
                var clave = ${'#clave'}.val();

                function registrar() {

                    $.ajax({
                        type: 'POST',
                        url: 'nuevo.htm',
                        data: {

                            'razonSocial': razonSocial,
                            'ruc': ruc,

                            'direccion': direccion,
                            'telefono': telefono,
                            'correo': correo,
                            'broker': broker,
                            'usuario': usuario,
                            'clave': clave
                        },
                        success: function (data) {
                            alert('los datos fueron agrupados con exito');
            ${'#razonSocial'}.val("");
            ${'#ruc'}.val("");

            ${'#direccion'}.val("");
            ${'#telefono'}.val("");
            ${'#correo'}.val("");
            ${'#broker'}.val("");
            ${'#usuario'}.val("");
            ${'#clave'}.val("");
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            alert("error:" + textStatus + " exception:" + errorThrown);
                        }
                    });
                }

            });
        </script>

        <script>
            $(function () {
                $("#btn").on("click", function () {
                    $("#formulario").validate({
                        rules:
                                {
                                    txtusuario: {required: true, minlength: 3, maxlength: 20, lettersonly: true},
                                    txtclave: {required: true, minlength: 6, maxlength: 15, numbersonly: true}
                                },
                        messages:
                                {
                                    txtusuario: {required: 'El campo es requerido', minlength: 'El mínimo permitido son 3 caracteres.',
                                        maxlength: 'El máximo permitido son 20 caracteres.'},
                                    txtclave: {required: 'El campo es requerido', minlength: 'El mínimo permitido son 6 caracteres.',
                                        maxlength: 'El máximo permitido son 15 caracteres.'}
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

