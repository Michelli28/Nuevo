<%-- 
    Document   : nuevopedido
    Created on : 12/01/2020, 08:40:39 AM
    Author     : CHELLI BONITA
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link href="<c:url value="webapp/resources/theme1/css/estilospedido.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/main.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/fonts/font.awesome.css" />" rel="stylesheet">
        <script src="<c:url value="webapp/resources/theme1/js/jquery.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="webapp/resources/theme1/js/jquery.modal.js"/>"></script>
        <link type="text/css" rel="stylesheet" href="<c:url value="webapp/resources/theme1/css/jquery.modal.css"/>" />

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
                        <div class="d-flex flex-row">

                            <div class="p-4" class="form-group">
                                <br>
                                <h3>Agregar Pedido</h3>
                            </div>
                            <form:hidden path="idPedido" id="idPedido"/>
                            <div class="p-4" class="form-group" id="femision">

                                <label for="fechaRegistro">Fecha Emisión: </label>
                                <form:input path="fechaRegistro" style="background: #F0E9FF;" cssClass="form-control" id="femisionI"/>
                            </div>
                            <div class="p-4" class="form-group" id="fentrega">
                                <label for="fechaRegistro">Fecha Entrega: </label>
                                <form:input path="fechaEntrega" style="background: #F0E9FF;" cssClass="form-control" id="fentregaI"/>
                            </div>
                        </div>
                    </div>
                    <div class="card-body" >
                            
                        <a class="btn btn-info" style="text-decoration: none;" href="FichaTecnica.htm" role="button" id="nuevo">Agregar Ficha</a>
                        <br>
                        <br>
                        <table class="table">
                            <thead class="thead-dark">
                                <tr>
                                    <th scope="col">IdFicha</th>
                                    <th scope="col">Descripcion</th>
                                    <th scope="col">Cantidad</th>
                                    <th scope="col">Estado</th>
                                    <th scope="col">Acciones</th>
                                </tr>
                            </thead>
                            <tbody style="background: #F0E9FF;">
                                <c:forEach var="item" items="${ficha}">
                                    <tr>
                                        <th scope="row">${item.idFicha}</th>
                                        <th>${item.descripcion}</th>
                                        <th>${item.cantidad}</th>
                                        <th>${item.idEstado.nombre}</th>
                                        <td scope="col-2">
                                            <a class="btn btn-warning" role="button" style="height: 50%;" href="editarficha.htm?id=${item.idFicha}" ><i class="fas fa-edit"></i></a> 
                                            <button  class="btn btn-danger" type="button" onclick="eliminar('${item.idFicha}')" ><i class="fas fa-trash-alt"></i></button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                       
                        
                        <form:input path="idEstado.idEstado" id="fentregaI" type="hidden" value="1"/>
                        
                        <center>
                            <input type="submit" class="btn btn-dark"value="Enviar"/>
                            <a class="btn btn-dark" style="text-decoration: none;" href="menucliente.htm" role="button">Regresar al Menú</a> 
                        </center>
                    </div>
                </form:form>
            </div>
        </div>
         <script type="text/javascript">
            function eliminar(id) {
                if (confirm("¿Desea eliminar la ficha técnica?")) {
                    window.location.href = "eliminarficha.htm?id=" + id;
                    return true;
                }
                return false;
            }
        </script>
    </body>
</html>