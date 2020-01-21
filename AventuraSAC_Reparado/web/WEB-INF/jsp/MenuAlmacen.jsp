
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
        <link href="<c:url value="webapp/resources/theme1/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/main.css" />" rel="stylesheet">
        <script src="<c:url value="webapp/resources/theme1/js/jquery.min.js" />"></script>

    </head>
    <body class="is-preload">

        <!-- Header -->
        <header id="header">
            <a class="logo" href="menualmacen.htm">Aventura S.A.C.</a>
            <nav>
                <a href="#menu">Menu</a>
            </nav>
        </header>

        <!-- Nav -->
        <nav id="menu">
            <ul class="links">
                <li><a href="menualmacen.htm">Home</a></li>
                <li><a href="quienessomos.htm">Quienes Somos</a></li>
            </ul>
        </nav>

        <!-- Banner -->
        <section id="banner">
            <div class="inner">
                <h1>BIENVENIDO ${usuario.apellidoPaterno}</h1>
                <p>Estas en Aventura S.A.C. una de las empresas lideres en confección<br />
                    a nivel nacional, con la garantia de materiales y acabados de calidad.</p>
            </div>
            <video autoplay loop muted playsinline src="webapp/resources/theme1/images/banner.mp4"></video>
        </section>

        <!-- Highlights -->
        <section class="wrapper">
            <div class="inner">
                <header class="special">
                    <h1>AREA DE ALMACEN</color></h1>
                    <p> </p>
                </header>
                <div class="highlights">
                    <section>
                        <div class="content">
                            <header>
                                <a href="nuevomovimiento.htm" class="icon fa-laptop"><span class="label">Icon</span></a>
                                <h2>REGISTRAR ENTRADAS Y SALIDAS</h2>
                            </header>
                            <p>Añade al registro cada vez que ingresa o retiran materiales.</p>								
                        </div>
                    </section>
                    <section>
                        <div class="content">
                            <header>
                                <a href="#" class="icon fa-list-alt"><span class="label">Icon</span></a>
                                <h2>LISTA ALMACEN</h2>
                            </header>
                            <p>Lista de la materia prima, insumos y pedidos que ingresaron. </p>
                        </div>
                    </section>
                    <section>
                        <div class="content">
                            <header>
                                <a href="GuiaRemision.htm" class="icon fa-file"><span class="label">Icon</span></a>
                                <h2>GENERAR GUIA DE REMISION</h2>
                            </header>
                            <p>Elabora la guia de remision por cada pedido que sale del almacen.</p>								
                        </div>

                    </section>

                </div>
            </div>
        </section>

        <!-- CTA -->

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
                        <p>Ni idea de que ponerle :'v 2.</p>
                    </blockquote>
                    <div class="author">
                        <div class="image">
                            <img src="webapp/resources/theme1/images/pic03.jpg" alt="" />
                        </div>
                        <p class="credit">- <strong>Pepito</strong> <span>CEO - Aventura S.A.C.</span></p>
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


        <!-- Footer -->
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
                        <img id="yo" src="webapp/resources/theme1/images/logo.PNG">
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
            </div>
        </footer>

        <!-- Scripts -->
        <script src="<c:url value="webapp/resources/theme1/js/browser.min.js"/>"></script>
        <script src="<c:url value="webapp/resources/theme1/js/jquery.min.js" />"></script>
        <script src="<c:url value="webapp/resources/theme1/js/breakpoints.min.js" />"></script>
        <script src="<c:url value="webapp/resources/theme1/js/util.js" />"></script>
        <script src="<c:url value="webapp/resources/theme1/js/main.js" />"></script>

    </body>
</html>