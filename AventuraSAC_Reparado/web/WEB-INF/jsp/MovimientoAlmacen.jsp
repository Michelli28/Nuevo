<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="webapp/resources/theme1/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/js/bootstrap.min.js" />" rel="stylesheet">
        <title>JSP Page</title>
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
                <form:form method="post" modelAttribute="pedido" >
                    <div class="card-header" id="cardheader">
                        <br>
                        <center><h3>Movimiento de Almacén</h3></center>
                        <br>
                    </div>

                    <div class="card-body">
                        
                                <table class="table table-bordered" style="width: 100%;">
                                    <thead style="background-color: #D6D9D9;">
                                        <tr>
                                            <th style="text-align: center; width: 10%;">ID</th>
                                            <th style="text-align: center; width: 20%;">Orden de Compra</th>
                                            <th style="text-align: center; width: 15%;">Tipo</th>
                                            <th style="text-align: center;">Descripción</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                            <tr> 
                                                <th></th>
                                                <th></th>
                                                <th></th>
                                                <th></th>
                                            </tr>
                                            <tr> 
                                                <th></th>
                                                <th></th>
                                                <th></th>
                                                <th></th>
                                            </tr>
                                            <tr> 
                                                <th></th>
                                                <th></th>
                                                <th></th>
                                                <th></th>
                                            </tr>
                                    </tbody>
                                </table>

                        <hr style="border: solid gray 1px;">
                        <center>
                            <input type="submit" class="btn btn-dark"value="Guardar"/>
                            <a class="btn btn-dark" href="menucliente.htm" role="button">Regresar al Menú</a> 
                        </center>

                    </div>
                </form:form>


            </div>

        </div>

        <br>
        <br>

    </body>
</html>
