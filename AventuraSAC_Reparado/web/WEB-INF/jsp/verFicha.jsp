<%-- 
    Document   : Ficha1
    Created on : 15/01/2020, 10:29:20 PM
    Author     : CHELLI BONITA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="<c:url value="webapp/resources/theme1/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/EstilosFicha.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/main.css" />" rel="stylesheet" />
        <script src="<c:url value="webapp/resources/theme1/js/jquery.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="webapp/resources/theme1/js/jquery.minicolors.js"/>"></script>
        <link type="text/css" rel="stylesheet" href="<c:url value="webapp/resources/theme1/css/jquery.minicolors.css"/>" />
        <script type="text/javascript" src="<c:url value="webapp/resources/theme1/js/jquery.modal.js"/>"></script>
        <script type="text/javascript" src="<c:url value="webapp/resources/theme1/js/jquery.validate.min.js"/>"></script>


    </head>
    <body id="bodys" style="background-color:rgba(100,0,0, 0.2);">
        <header id="header">
            <a class="logo" href="index.html">Aventura S.A.C.</a>
            <nav>
                <a href="#menu">Menu</a>
            </nav>
        </header>
        <div class="container m-8" >
            <br>
            <div style="background-color:rgba(100,0,0, 0.4);">
                
                <div class="card"  id="carta" style="margin-left:10px;">
                    <form id="formulario" enctype="multipart/form-data">
                        <div class="card-header" id="cardheader" style="background-color:rgba(100,0,0, 0.4);">
                            <br>
                            
                            <div class="d-flex flex-row">

                                <div  class="p-4" id="img">

                                    <img src="webapp/resources/theme1/images/logo.PNG">

                                </div>

                                <div class="p-4"  id="escritos">
                                    <p style="font-family: arial;">Cal. Mercaderes Nro. 164 Dpto. 203 </p>
                                    <p style="font-family: arial;">Urb. Las Gardenias (Alt. Cuadra 50 de la Avenida Benavides)</p>

                                </div>

                                <div class="p-4" id="fieldset">

                                    <fieldset style="border:1px solid black" id="fld" >

                                        <h5><strong> R.U.C. N° 20111807958 </strong></h5>
                                        <h2 style="font-family: cambria; font-size: 25px;"><strong>Ficha Técnica</strong></h2>
                                        <div class="d-flex flex-row" >
                                            <div class="p-3" style="font-family: arial;">
                                                N° 
                                            </div>
                                            <div class="p-3">
                                                <input type="text" style="background: #F0E9FF; text-align: center; width: 45px; height: 30px;"  value="${fichatecnica.idFicha.idFicha}">

                                            </div>
                                            <div class="p-3" style="font-family: arial;">
                                                -&nbsp;0000041
                                            </div>
                                        </div>

                                    </fieldset>
                                </div>

                            </div>

                        </div>
                        <div class="card-body">

                            <center><h3 style="font-family: cambria; font-size: 25px;"><strong>Datos de la Prenda</strong></h3></center>


                            <input name="idEstado.idEstado" id="fentregaI" type="hidden" value="1"/>


                            <div class="row" style="text-align: left;">
                                <div class="col-sm-4">
                                    <label for="descripcion">Descripción:</label>
                                    <input name="descripcion" style="background: #F0E9FF;font-family: arial;" type="text" cssClass="form-control" placeholder="Ingresar descripcion" value="${fichatecnica.idFicha.descripcion}" />
                                </div>
                                <div class="col-sm-4">
                                    <label for="idTipoModelo" >Modelo: </label>
                                    <input type="text" value="${fichatecnica.idFicha.idTipoModelo.nombre}" style="font-family: Arial;">
                                </div>
                                <div class="col-sm-4">	
                                    <label for="idTipo">Tela:</label>
                                    <input type="text" value="${fichatecnica.idFicha.idTipo.nombre}" style="font-family: Arial;">
                                </div>
                            </div>
                            <br>
                            <div class="row" style="text-align: left;">  
                                <div class="col-sm-4"> 
                                    <label for="etiqueta"><strong>Etiqueta:</strong></label>
                                    <input name="etiqueta" style="background: #F0E9FF; font-family: Arial;" cssClass="form-control" type="text" placeholder="Ingresar nombres de etiqueta" value="${fichatecnica.idFicha.etiqueta}"/>
                                </div>
                                <div class="col-sm-4">
                                    <label for="idTalla"><strong>Talla:</strong></label>
                                    <input type="text" value="${fichatecnica.idFicha.idTalla.nombre}" style="font-family: Arial;">
                                </div>
                                <div class="col-sm-4">
                                    <label for="cantidad" >Cantidad:</label>
                                    <input name="cantidad"  style="background: #F0E9FF;font-family: Arial;" type="text" cssClass="form-control" placeholder="Ingresar cantidad" value="${fichatecnica.idFicha.cantidad}"/>
                                </div>
                            </div>
                            <br>
                            
                            <hr style="border: 1px solid gray">

                            <center>
                                <div class="card" id="carta2" >
                                    <div class="card-body" id="body2">
                                        <h5 class="card-title">Combinación de colores:</h5>
                                        <h6 class="card-subtitle mb-2 text-muted">Seleccionar 1 color como mínimo..</h6>
                                        <div class="d-flex flex-row">
                                            <div  class="p-4">
                                                <label for="color1" style="font-family: arial;" >Color 1:</label>
                                                <input name="color1" id="c1" style="height: 35px; " type="text" cssClass="form-control" value="${fichatecnica.idFicha.color1}"/>
                                                <script>
                                                    $(function () {

                                                        $('#c1').minicolors();

                                                    });

                                                </script>

                                            </div>

                                            <div  class="p-4">

                                                <label for="color2" style="font-family: arial;" >Color 2:</label>
                                                <input name="color2" id="c2" style="height: 35px; font-family: arial;" value="${fichatecnica.idFicha.color2}" type="text" cssClass="form-control"/>

                                                <script>
                                                    $(function () {

                                                        $('#c2').minicolors();

                                                    });
                                                </script>

                                            </div>

                                            <div  class="p-4">

                                                <label for="color3" >Color 3:</label>
                                                <input name="color3" id="c3" style="height: 35px;font-family: arial;" value="${fichatecnica.idFicha.color3}" type="text" cssClass="form-control"/>

                                                <script>
                                                    $(function () {

                                                        $('#c3').minicolors();

                                                    });
                                                </script>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </center>
                            <hr style="border: 1px solid gray;font-family: arial;">
                            <center>
                                <a class="btn btn-dark" style="text-decoration: none; width: 200px; height: 50px; padding: 13px; margin: 0px 10px; font-family: arial;" href="verPedido.htm?idPedido=${fichatecnica.idPedido.idPedido}">Regresar</a>
                            </center>


                        </div>
                    </form>
                </div>
            </div>
                            <br>
                            <br>
        </div>

    </body>
</html>
