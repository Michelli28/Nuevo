
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html>
    <head>
        <title>Menu Principal Trabajador</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <link href="<c:url value="webapp/resources/theme1/css/main.css" />" rel="stylesheet">
        
    </head>
    <body class="is-preload">

        <!-- Header -->
        <header id="header">
            <a class="logo" href="menulogistica.htm">Aventura S.A.C.</a>
            <nav>
                <a href="#menu">Menu</a>
            </nav>
        </header>

        <!-- Nav -->
        <nav id="menu">
            <ul class="links">
                <li><a href="menuventas.htm">Home</a></li>
                <li><a href="quienessomos.htm">Quienes Somos</a></li>
                <li><a href="login.htm">Salir</a>
            </ul>
        </nav>

        <!-- Banner -->
        <section style="background-color:rgba(100,36,13, 0.7);">
            <div class="inner" style="text-align: center; background-position: top; background: url(webapp/resources/theme1/images/confeccion.jpg) no-repeat center center fixed;
                 background-size: cover;  width: 6000px; height: 500px;">
                <br>
                <br>
                <br>
                <br>
                <center>
                    <div style="background-color:rgba(255,255,255, 0.5); width: 50%; height: 35%;text-align: center; margin: 50px;">
                        <br>
                        <h1 style="margin: 10px; font-family: cambria;"><strong>BIENVENIDO ${usuario.apellidoPaterno}</strong></h1>
                        <p style="font-family: arial; margin-bottom: 20px; color: black;">Estas en Aventura S.A.C. una de las empresas lideres en confección<br />
                            a nivel nacional, con la garantía de materiales y acabados de calidad.</p>
                        <br>
                    </div>
                </center>
            </div>
        </section>

        <!-- Highlights -->
        <section class="wrapper" style="background-color:rgba(100,0,0, 0.1);">
            <div class="inner">
                <header class="special">
                    <h1 style="font-family: cambria;">ÁREA DE LOGÍSTICA</color></h1>
                    <p> </p>
                </header>
                <div class="highlights">
                    <div style="margin-left: 150px;">

                        <section style="background-color:rgba(150,36,13, 0.9);">
                            <div class="content" style="margin-left: 10px; width: 100%;  height: 450px;">

                                <div class="author">
                                    <div class="image">
                                        <header>
                                            <a href="listapedidoslogistica.htm" class="icon fa-list"><span class="label">Icon</span></a>
                                            <h2 style="font-family: cambria;">PEDIDOS</h2>
                                        </header>
                                        <blockquote>
                                            <p>"Lista los pedidos con toda la información, adicionalmente podras generar su respectiva cotización con esta herramienta."</p>
                                        </blockquote>
                                    </div>
                                </div>
                        </section>
                    </div>

                    <div style="margin-left: 80px;">
                        <section style="background-color:rgba(150,36,13, 0.9);">
                            <div class="content" style="margin-left: 10px; width: 100%; height: 450px;">

                                <div class="author">
                                    <div class="image">
                                        <header>
                                            <a href="listaordenes.htm" class="icon fa-list"><span class="label">Icon</span></a>
                                            <h2 style="font-family: cambria;">LISTA DE ÓRDENES DE COMPRA</h2>
                                        </header>
                                        <blockquote>
                                            <p>"Visualiza la lista de las ordenes de compra."</p>
                                        </blockquote>
                                    </div>
                                </div>
                        </section>

                    </div>


                </div>
            </div>
        </section>

        <!-- CTA 

        <div class="testimonials">
            <section>
                <div class="content">
                    <blockquote>
                        <p>"La creatividad solo es la inteligencia divirtiendose".</p>
                    </blockquote>
                    <div class="author">
                        <div class="image">
                            <img src="webapp/resources/theme1/images/pic01.jpg" alt="" />
                        </div>
                        <p class="credit">- <strong>Erika Espinoza</strong> <span>CEO Aventura</span></p>
                    </div>
                </div>
            </section>
            <section>
                <div class="content">
                    <blockquote>
                        <p>Ni idea de que ponerle 2.</p>
                    </blockquote>
                    <div class="author">
                        <div class="image">
                            <img src="webapp/resources/theme1/images/pic03.jpg" alt="" />
                        </div>
                        <p class="credit">- <strong>Juan Valdez</strong> <span>CEO - Aventura S.A.C.</span></p>
                    </div>
                </div>
            </section>
            <section>
                <div class="content">
                    <blockquote>
                        <p>Ni idea de que ponerle 3.</p>
                    </blockquote>
                    <div class="author">
                        <div class="image">
                            <img src="webapp/resources/theme1/images/pic02.jpg" alt="" />
                        </div>
                        <p class="credit">- <strong>Carlos Quesada</strong> <span>Jefe de Producción.</span></p>
                    </div>
                </div>
            </section>
        </div>

-->
        <!-- Footer -->
        <div style="margin-top: -36px !important;">
        <footer id="footer">
            <div class="inner">
                <div class="content">
                    <section>
                        <h3>¿Que hacemos?</h3>
                        <p>Confeccion de prendas.</p><br>
                        <p>Todo tipo de telas.</p><br>
                        <p>Materiales de calidad.</p><br>
                        <p>Puntualidad en las entregas.</p><br>

                    </section>

                    <section>
                        <style>
                            #yo { 
                                margin-left: -120px;
                            }
                        </style>
                    </section>
                    <section>
                        <h4>Contáctanos:</h4>
                        <ul class="plain">
                            <li><a href="#"><i class="icon fa-twitter">&nbsp;</i>Twitter</a></li>
                            <li><a href="#"><i class="icon fa-facebook">&nbsp;</i>Facebook</a></li>
                            <li><a href="#"><i class="icon fa-instagram">&nbsp;</i>Instagram</a></li>
                        </ul>
                    </section>
                </div>
                <div class="copyright">
                    Crea tu pagina web - Contactanos 970312126
                </div>
                <div>
                    <select name="idProveedor" id="id" >
                        <c:forEach items="${proveedor}" var="x">
                            <option value="${x.idProveedor}" >${x.razonSocial}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </footer>
        </div>
        <!-- Scripts -->
        <script src="<c:url value="webapp/resources/theme1/js/browser.min.js"/>"></script>
        <script src="<c:url value="webapp/resources/theme1/js/jquery.min.js" />"></script>
        <script src="<c:url value="webapp/resources/theme1/js/breakpoints.min.js" />"></script>
        <script src="<c:url value="webapp/resources/theme1/js/util.js" />"></script>
        <script src="<c:url value="webapp/resources/theme1/js/main.js" />"></script>

        <script>
            function Enviar() {

                //var fichas = obtenerFichas();

                //alert(fichas);
                $.ajax({
                    type: 'GET',
                    url: 'OrdenCompra.htm',
                    data: {
                        'idProveedor': $("#id").val()
                    },
                    success: function (data) {
                        window.location.href = 'OrdenCompra.htm';


                    }
                });
            }

        </script>
    </body>

</html>