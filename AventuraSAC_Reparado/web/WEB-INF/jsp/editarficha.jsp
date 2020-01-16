
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
    <head>
        <title>Registrar Pedido</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <link href="<c:url value="webapp/resources/theme1/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/EstilosFicha.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/main.css" />" rel="stylesheet" />
        <script src="<c:url value="webapp/resources/theme1/js/jquery.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="webapp/resources/theme1/js/jquery.minicolors.js"/>"></script>
        <link type="text/css" rel="stylesheet" href="<c:url value="webapp/resources/theme1/css/jquery.minicolors.css"/>" />
        <script type="text/javascript" src="<c:url value="webapp/resources/theme1/js/jquery.modal.js"/>"></script>
        <link type="text/css" rel="stylesheet" href="<c:url value="webapp/resources/theme1/css/jquery.modal.css"/>" />


    </head>
    <body>
        <header id="header">
            <a class="logo" href="index.html">Aventura S.A.C.</a>
            <nav>
                <a href="#menu">Menu</a>
            </nav>
        </header>
        <br>
        <div class="container md-8">


            <form:form method="post" modelAttribute="fichatecnica">
                
                 <form:input path="idEstado.idEstado" id="fentregaI" type="hidden" value="1"/>

                <div class="card" >

                    <div class="card-header">

                        <div class="d-flex flex-row">

                            <div  class="p-4" id="img">

                                <img src="webapp/resources/theme1/images/logo2.png" alt="Card image cap">

                            </div>

                            <div class="p-4"  id="escritos">

                                <h3>Cal. Mercaderes Nro. 164 Dpto. 203 </h3>
                                <h3>Urb. Las Gardenias (Alt. Cuadra 50 de la Avenida Benavides)</h3>

                            </div>

                            <div class="p-4" id="fieldset">

                                <fieldset style="border:1px solid black" >

                                    <h3> R.U.C. N° 20111807958 </h3>
                                    <h3><strong>Ficha Técnica</strong></h3>
                                    <div class="row" id="ngui">
                                        N° &nbsp;&nbsp;&nbsp;<form:hidden path="idFicha" value="${fichatecnica.idFicha}" />&nbsp;-&nbsp;0000041
                                    </div>

                                </fieldset>
                            </div>
                        </div>

                    </div>

                    <br />
                    <br />

                    <div class="card-body">


                        

                        <div class="card-title"> Ingresar datos de la Prenda</div>


                        <div class="d-flex flex-row" id="fila1">

                            <div class="p-4">
                                <label for="descripcion">Descripción:</label>

                                <form:input path="descripcion"  cssClass="form-control" value="${fichatecnica.descripcion}"/>

                            </div>

                            <div class="p-5">

                                <label for="idTipoModelo" >Modelo: </label>

                                <form:select path="idTipoModelo.idTipoModelo" id="idTipoModelo">

                                    <c:forEach items="${listamodelo}" var="x">

                                        <c:if test="${x.idTipoModelo == fichatecnica.idTipoModelo.idTipoModelo}">
                                            <option value="${x.idTipoModelo}" selected="selected" required="required">${x.nombre}</option>
                                        </c:if>
                                        <c:if test="${x.idTipoModelo != fichatecnica.idTipoModelo.idTipoModelo}">
                                            <option value="${x.idTipoModelo}">${x.nombre}</option>
                                        </c:if>

                                    </c:forEach>

                                </form:select>

                            </div>

                            <div class="p-4">		

                                <label for="idTipo">Tela:</label>

                                <form:select path="idTipo.idTipo" id="idTipo">

                                    <c:forEach items="${tipotelas}" var="x">

                                        <c:if test="${x.idTipo == fichatecnica.idTipo.idTipo}">
                                            <option value="${x.idTipo}" selected="selected" required="required">${x.nombre}</option>
                                        </c:if>
                                        <c:if test="${x.idTipo != fichatecnica.idTipo.idTipo}">
                                            <option value="${x.idTipo}">${x.nombre}</option>
                                        </c:if>

                                    </c:forEach>

                                </form:select>

                            </div>

                        </div>

                        <div class="d-flex flex-row" id="fila2">

                            <div class="p-4">

                                <label for="etiqueta">Etiqueta:</label>

                                <form:input path="etiqueta" cssClass="form-control" value="${fichatecnica.etiqueta}"/>

                            </div>


                            <div class="p-4">

                                <label for="idTalla" >Talla:</label>

                                <form:select path="idTalla.idTalla" id="idTalla">

                                    <c:forEach items="${listatallas}" var="x">

                                        <c:if test="${x.idTalla == fichatecnica.idTalla.idTalla}">
                                            <option value="${x.idTalla}" selected="selected" required="required">${x.nombre}</option>
                                        </c:if>
                                        <c:if test="${x.idTalla != fichatecnica.idTalla.idTalla}">
                                            <option value="${x.idTalla}">${x.nombre}</option>
                                        </c:if>

                                    </c:forEach>

                                </form:select>

                            </div>

                            <div class="p-4">

                                <label for="cantidad" >Cantidad:</label>

                                <form:input path="cantidad" cssClass="form-control" value="${fichatecnica.cantidad}" />

                            </div>

                        </div>
                    </div>

                    <hr style="border: 1px solid gray">

                    <div class="d-flex flex-row">

                        <label for="Muestra" class="col-sm-2 col-form-label">Muestra:</label>

                        <div class="col-sm-3">

                            <input type="submit" value="ADJUNTAR IMAGEN PNG">

                        </div>	

                    </div>


                    <hr style="border: 1px solid gray">

                    <div class="d-flex flex-row">

                        <div  class="p-6">

                            <div class="card" id="carta2">

                                <div class="card-body" id="body2">

                                    <h5 class="card-title">Combinación de colores:</h5>

                                    <h6 class="card-subtitle mb-2 text-muted">Seleccionar..</h6>

                                    <div class="d-flex flex-row">

                                        <div  class="p-4">

                                            <label for="color1" >Color 1:</label>
                                            <input type="text" name="color1" id="c1" value="${fichatecnica.color1}" data-wheelcolorpicker>

                                            <script>
                                                $(function () {

                                                    $('#c1').minicolors();

                                                });

                                            </script>

                                        </div>

                                        <div  class="p-4">

                                            <label for="color2" >Color 2:</label>
                                            <input type="text" name="color2" id="c2" value="${fichatecnica.color1}" data-wheelcolorpicker>

                                            <script>
                                                $(function () {

                                                    $('#c2').minicolors();

                                                });
                                            </script>

                                        </div>

                                        <div  class="p-4">

                                            <label for="color3" >Color 3:</label>
                                            <input type="text" name="color3" id="c3" value="${fichatecnica.color1}" data-wheelcolorpicker>

                                            <script>
                                                $(function () {

                                                    $('#c3').minicolors();

                                                });
                                            </script>

                                        </div>

                                    </div>

                                </div>

                            </div>

                        </div>

                    </div>

                    <div class="d-flex flex-row">

                        <div class="p-4">

                            <input type="submit" class="btn btn-primary"  value="Registrar" >

                        </div>

                        <div  class="p-4">

                            <a class="btn btn-secondary" href="pedidos.htm" role="button" >Regresar</a>

                        </div>

                    </div>





                </div>
            </form:form>
        </div>
    </body>
</html>



