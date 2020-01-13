<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>
<html>
    <head>
        <title>Guía de Remisión</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <link href="<c:url value="webapp/resources/theme1/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/estilosguia.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/main.css" />" rel="stylesheet" />


    </head>
    <body id="body">

        <header id="header">
            <a class="logo" href="menu.htm">Aventura S.A.C.</a>
            <nav>
                <a href="login.htm">Salir</a>
            </nav>
        </header>

        <div class="container md-8">

            <div class="card" id="carta">

                <form:form method="post" modelAttribute="guiaremision">

                    <div class="card-header" id="cardheader">
                        <br>
                        <center><h2><strong>Guía de Remisión</strong></h2></center>

                        <div class="d-flex flex-row">

                            <div  class="p-4" id="img">

                                <img src="webapp/resources/theme1/images/logo2.png">

                            </div>

                            <div class="p-4"  id="escritos">
                                <p>Cal. Mercaderes Nro. 164 Dpto. 203 </p>
                                <p>Urb. Las Gardenias (Alt. Cuadra 50 de la Avenida Benavides)</p>

                            </div>

                            <div class="p-4" id="fieldset">

                                <fieldset style="border:1px solid black" id="fld" >

                                    <h5><strong> R.U.C. N° 20111807958 </strong></h5>
                                    <div class="d-flex flex-row" id="ngui">
                                        <div class="p-3">
                                            N° 
                                        </div>
                                        <div class="p-3">
                                            <input type="text" style="background: #F0E9FF;" name="idGuiaRemision">
                                        </div>
                                        <div class="p-3">
                                            -&nbsp;0000041
                                        </div>
                                    </div>

                                </fieldset>
                            </div>
                        </div>
                    </div>


                    <div class="card-body">
                        <br>
                        <div class="row">
                            <div class="col-sm-4">
                                <label for="fechaEmision">Fecha Emision:</label>
                                <form:input path="fechaEmision"  style="background: #F0E9FF;" cssClass="form-control" placeholder="Ingresar fecha" />
                            </div>
                            <div class="col-sm-4">	
                                <label for="puntoLlegada">Punto de Llegada:</label>
                                <form:input path="puntoLlegada"  style="background: #F0E9FF;" cssClass="form-control" placeholder="Ingresar lugar de llegada" />
                            </div>	
                            <div class="col-sm-4">  
                                <label for="destinatario">Destinatario:</label>
                                <form:input path="destinatario"  style="background: #F0E9FF;" cssClass="form-control" placeholder="Ingresar Destinatario" />
                            </div>
                        </div>

                        <hr style="border: 1px solid gray">
                        <fieldset style="border: 1px solid gray" id="datostransporte">
                            <legend><strong>Ingresar datos</strong></legend>
                            <div class="row">
                                <div class="col-sm-6">
                                    <label for="rUCtransporte" >RUC Transporte:</label>
                                    <form:input path="rUCtransporte" style="background: #F0E9FF;" cssClass="form-control" placeholder="RUC Transporte" />
                                </div>
                                <div class="col-sm-6">
                                    <label for="nombreTransportista">Nombre de Chofer:</label>
                                    <form:input path="nombreTransportista" style="background: #F0E9FF;" cssClass="form-control" placeholder="Nombre Chofer" />
                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <div class="col-sm-6">		
                                    <label for="marcayPlaca">Marca y Placa:</label>
                                    <form:input path="marcayPlaca" style="background: #F0E9FF;" cssClass="form-control" placeholder="Marca y Placa" />
                                </div>
                                <div class="col-sm-6">      
                                    <label for="licencia">Licencia:</label>
                                    <form:input path="licencia" style="background: #F0E9FF;" cssClass="form-control" placeholder="Licencia"/>
                                </div>
                            </div>
                        </fieldset>
                    </form:form>

                    <hr style="border: 1px solid gray ">

                    <form:form method="post" modelAttribute="guiaremisionDetalle">          
                        <fieldset id="fieldset1" style="border: 1px solid gray">
                            <legend><strong>Detalles</strong></legend>
                            <div class="row" id="fila1">
                                <div class="col-sm-4">
                                    <label for="cantidad">Cantidad:</label>
                                    <input type=""  />
                                </div>
                            </div>
                            <div class="row" id="fila2">
                                <div class="col-sm-2">	
                                    <label for="descripcion">Descripcion:</label>
                                    <input type="text" name="descripcion"  cssClass="form-control" />
                                </div>	
                            </div>
                        </fieldset>
                    </form:form>



                    <fieldset id="fieldset4" style="border: 1px solid gray">
                        <legend>¿Qué acción desea realizar?</legend>
                        <div class="row" id="fila8">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-outline-secondary">Enviar</button>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-outline-secondary">Cancelar</button>
                        </div>
                    </fieldset>
                </div>
            </div>
        </div>
    </body>
</html>
