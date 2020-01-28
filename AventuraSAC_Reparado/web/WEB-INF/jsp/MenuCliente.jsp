<%-- 
    Document   : MenuCliente
    Created on : 29/11/2019, 11:30:49 AM
    Author     : Administrador
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Menu Principal Cliente</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <link href="<c:url value="webapp/resources/theme1/css/main.css" />" rel="stylesheet">
    </head>
    <body class="is-preload">

        <!-- Header -->
        <header id="header">
            <a class="logo" href="menucliente.html">Aventura S.A.C.</a>
            <nav>
                <a href="#menu">Menu</a>

            </nav>
        </header>

        <!-- Nav -->
        <nav id="menu">
            <ul class="links">
                <li><a href="menucliente.htm">Home</a></li>
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
                        <h1 style="margin: 10px; font-family: cambria;"><strong>BIENVENIDO ${usuario.razonSocial}</strong></h1>

                        <p style="font-family: arial; margin-bottom: 20px; color: black;">Estas en Aventura S.A.C. una de las empresas lideres en confección<br />
                            a nivel nacional, con la garantia de materiales y acabados de calidad.</p>
                        <br>
                    </div>
                </center>
            </div>
            <!-- <video autoplay loop muted playsinline src="webapp/resources/theme1/images/banner.mp4"></video>-->


        </section >

        <!-- Highlights -->
        <section class="wrapper" style="background-color:rgba(100,0,0, 0.1);">
            <div class="inner">
                <header class="special">
                    <center>
                        <div style="background-color:rgba(255,255,255, 0.4); width: 70%;">
                            <br>
                            <h2 style="font-family: cambria;">Servicios</h2>
                            <p style="margin-bottom: 0px;">Escoja uno de nuestros servicios para poder brindarle la atencion que requiera.</p>
                            <br>
                        </div>
                    </center>
                </header>
                <center>
                    <div class="highlights">
                        <div style="margin-left: 150px;">

                            <section style="background-color:rgba(150,36,13, 0.9);">
                                <div class="content" style="margin-left: 10px; width: 100%;  height: 530px;">

                                    <div class="author">
                                        <div class="image">
                                            <header>
                                                <a href="nuevopedido.htm" class="icon fa-tasks"><span class="label">Icon</span></a>
                                                <h2 style="font-family: cambria;">INGRESAR SOLICITUD DE PEDIDO</h2>
                                            </header>
                                            <blockquote>
                                                <p>"En este campo podrá ingresar detalles sobre su pedido con el cual podremos enviarle una cotización con el tiempo estimado."</p>
                                            </blockquote>
                                        </div>
                                    </div>
                            </section>
                        </div>
                        <div style="margin-left: 80px;">
                            <section style="background-color:rgba(150,36,13, 0.9);">
                                <div class="content" style="margin-left: 10px; width: 100%; height: 530px;">

                                    <div class="author">
                                        <div class="image">
                                            <header>
                                                <a href="listapedidos.htm" class="icon fa-list-alt"><span class="label">Icon</span></a>
                                                <h2 style="font-family: cambria;">MIS SOLICITUDES DE PEDIDOS</h2>
                                            </header>
                                            <blockquote>
                                                <p>"En este campo podra ver el estado de sus pedidos, la cotización respectiva y podra acceder a pagar por el pedido."</p>
                                            </blockquote>
                                        </div>
                                    </div>
                            </section>

                        </div>
                    </div>
                </center>
        </section>

        <!-- CTA 
        <div  style="background-color:rgba(150,36,13, 0.9);">
            <div class="testimonials" style="background-color:rgba(150,36,13, 0.9); margin-top: 2px; padding-bottom: 10px; padding-top: 10px;">
                <section style="background-color:rgba(150,36,13, 0.9);">
                    <div class="content" style="margin-left: 10px; width: 100%;">
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
                    <div class="content" style="margin-left: -3px; width: 100%;">
                        <blockquote>
                            <p>--------------------------.</p>
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
                    <div class="content" style="margin-left: -10px; width: 100%;">
                        <blockquote>
                            <p>----------------------.</p>
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
        </div>
        -->
        <div style="margin-top: -36px !important;">
            <footer id="footer" >
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
                            <img id="yo" src="webapp/resources/theme1/images/logo.PNG" style="margin-left: -200px; margin-top: 20px;">
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
        </div>
        <!-- Scripts -->

        <script src="<c:url value="webapp/resources/theme1/js/browser.min.js"/>"></script>
        <script src="<c:url value="webapp/resources/theme1/js/jquery.min.js" />"></script>
        <script src="<c:url value="webapp/resources/theme1/js/breakpoints.min.js" />"></script>
        <script src="<c:url value="webapp/resources/theme1/js/util.js" />"></script>
        <script src="<c:url value="webapp/resources/theme1/js/main.js" />"></script>
    </body>
</html>
