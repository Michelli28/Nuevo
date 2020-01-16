<%-- 
    Document   : RegistrarPago
    Created on : 15/01/2020, 10:54:41 AM
    Author     : Administrador
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Orden de Compra</title>
        <link href="<c:url value="webapp/resources/theme1/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/EstilosRegistrarPago.css" />" rel="stylesheet">
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
                
                    <div class="card-header" id="cardheader">
                        <br>
                        <center><h1>Registrar Pago</h1></center>
                        <br>
                    </div>

                    <div class="card-body">
                        
                        <form:form method="post" modelAttribute="pago" >
                            
                  <p style="text-align: left;">Ingresar los datos del comprobante de su deposito</p>    
                  
                        <fieldset style="border: 1px solid gray; width:40%; height: 50%; margin: 0% 30%; padding: 3% 4% 2% 0%;"  >
                            <div class="form-group row">
                                <div class="col-sm-5" style="left:15%;">
                                    <label for="numeroOperacion">N° de Operación:</label>
                                </div>
                                <div class="col-sm-6" style="left:7%; width: 100%;" >
                                    <form:input path="numeroOperacion" style="width:100%;" cssClass="form-control"  placeholder="Ingrese numero"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-5" style="left:15%;">
                                    <label>Monto:</label>
                                </div>
                                <div class="col-sm-6" style="left:7%;">
                                    <form:input path="monto" cssClass="form-control"  placeholder="Ingrese monto"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-5" style="left:15%;">
                                    <label>Entidad Bancaria:</label>
                                </div>
                                <div class="col-sm-6" style="left:7%;">
                                    <form:input path="banco" cssClass="form-control"  placeholder="Ingrese banco"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-5" style="left:15%;">
                                    <label>Fecha:</label>
                                </div>
                                <div class="col-sm-6" style="left:7%;">
                                    <form:input path="fecha" cssClass="form-control"  placeholder="Ingrese fecha"/>
                                </div>
                            </div>
                        </fieldset>

                        <hr style="border: solid gray 1px;">

                        <center>
                            <input type="submit" class="btn btn-dark"value="Enviar"/>
                            <a class="btn btn-dark" id="a" href="menucliente.htm" style="text-decoration: none;" role="button">Regresar al Menú</a> 
                        </center>
                        </form:form>
                    </div>
                


            </div>

        </div>

        <br>
        <br>

    </body>
</html>
