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

    <body id="cuerpo">
        <div class="container h-100">
            <div class="d-flex justify-content-center h-100">
                <div class="user_card">
                    <div class="d-flex justify-content-center">
                        <div class="brand_logo_container">

                            <img src="webapp/resources/theme1/images/logo.PNG" class="brand_logo" alt=""/>
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

    </body>

</html>

