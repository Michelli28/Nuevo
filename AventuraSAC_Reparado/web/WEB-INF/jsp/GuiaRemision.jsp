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
    <body>
        <header id="header">
            <a class="logo" href="index.html">Aventura S.A.C.</a>
            <nav>
                <a href="#menu">Menu</a>
            </nav>
        </header>
         
            <div id="container">
                <section id="Contenedor1" class="wrapper" >
                    <div id="Contenedorlogo" class="inner" style="border:9px double">
                        <div id="Contenedorlogo2" class="content">
                            <div class="row">
                                <div class="col" id="imagen">
                                    <h1><img src="webapp/resources/theme1/images/logo.PNG"></h1>
                                </div> 
                                <div class="col" id="escritos">
                                    <center>
                                        <p>Cal. Mercaderes Nro. 164 Dpto. 203</p>
                                        <p>Urb. Las Gardenias (Alt. Cuadra 50 de la Avenida Benavides)</p>
                                    </center>
                                </div>
                                <div class="col">
                                    <fieldset style="border:1px solid black" id="fieldset">
                                        <h3> R.U.C. N° 20111807958 </h3>
                                        <H3><strong>Guía de Remisión</strong></H3>
                                        <div class="row" id="ngui">
                                            N° &nbsp;&nbsp;&nbsp;<input type="text" name="nroguia">&nbsp;-&nbsp;0000041
                                        </div>
                                    </fieldset>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
<form:form method="post" modelAttribute="guiaremision">
                <section id="main" class="wrapper" >
                    <div id="main2" class="inner" style="border:9px double">
                        <div id="main3" class="content">

                            <fieldset id="fieldset1" style="border: 1px solid gray">
                                <legend><strong>Ingresar datos</strong></legend>
                                <div class="row" id="fila1">
                                    <div class="col-sm-4">
                                      <label for="fechaEmision">Fecha Emision:</label>
                                <form:input path="fechaEmision"  cssClass="form-control" placeholder="Ingresar fecha" />
                                    </div>
                                </div>
                                <div class="row" id="fila2">
                                    <div class="col-sm-3">	
                                         <label for="puntoLlegada">Punto de Llegada:</label>
                                <form:input path="puntoLlegada"  cssClass="form-control" placeholder="Ingresar lugar de llegada" />
                                    </div>	
                                    <div class="col-sm-3">  
                                        <label for="destinatario">Destinatario:</label>
                                <form:input path="destinatario"  cssClass="form-control" placeholder="Ingresar Destinatario" />
                                    </div>
                                </div>
                            </fieldset>

                            <hr style="border: 1px solid gray">

                            <fieldset id="fieldset2" style="border: 1px solid gray">
                                <legend><strong>Ingresar datos</strong></legend>
                                <div class="row" id="fila3">
                                    <div class="col-sm-3">
                                        <label for="rUCtransporte" >RUC Transporte:</label>
                                   <form:input path="rUCtransporte"  cssClass="form-control" />
                                    </div>
                                    <div class="col-sm-3">
                                        <label for="nombreTransportista">Nombre de Chofer:</label>
                                     <form:input path="nombreTransportista"  cssClass="form-control" />
                                    </div>
                                    <div class="col-sm-3">		
                                        <label for="marcayPlaca">Marca y Placa:</label>
                                     <form:input path="marcayPlaca"  cssClass="form-control" />
                                    </div>
                                    <div class="col-sm-3">      
                                        <label for="licencia">Licencia:</label>
                                     <form:input path="licencia"  cssClass="form-control" />
                                    </div>
                                </div>
                            </fieldset>
                        </form:form>
                            <br>
                            
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
                </section>
            </div>
        </form>
    </body>
</html>