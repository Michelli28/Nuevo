<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="<c:url value="webapp/resources/theme1/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/crud.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/main.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/fonts/font.awesome.css" />" rel="stylesheet">
        <script src="<c:url value="webapp/resources/theme1/js/jquery.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="webapp/resources/theme1/js/jquery.modal.js"/>"></script>
        <link type="text/css" rel="stylesheet" href="<c:url value="webapp/resources/theme1/css/jquery.modal.css"/>" />

    </head>
    <body id="bodys" style="background-color:rgba(100,0,0, 0.3);">
        <header id="header">
            <a class="logo" href="menu.htm">Aventura S.A.C.</a>
            <nav>
                <a href="login.htm">Salir</a>
            </nav>
        </header>



        <div class="container md-8">

            <div style="background-color:rgba(150,36,13, 0.9);">
                <div class="card" id="carta" style="margin-left: 10px;">

                    <div class="card-header" id="cardheader" style="background-color:rgba(150,36,13, 0.6);">

                        <h3 style="font-family: cambria; font-size: 30px !important"><strong>Listado de Clientes</strong></h3>
                    </div>
                    <div class="card-body">
                        <a class="btn btn-primary" href="nuevocliente.htm" role="button" id="nuevo">Nuevo</a>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">Razón Social</th>
                                    <th scope="col">RUC</th>
                                    <th scope="col">Distrito</th>
                                    <th scope="col">Dirección</th>
                                    <th scope="col">Teléfono</th>
                                    <th scope="col">Correo</th>
                                    <th scope="col">Broker</th>

                                    <th scope="col">Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="item" items="${clientes}">
                                    <tr>
                                        <th scope="row" >${item.idCliente}</th>
                                        <td >${item.razonSocial}</td>
                                        <td >${item.ruc}</td>
                                        <td >${item.idDistrito.detalle}</td>
                                        <td >${item.direccion}</td>
                                        <td >${item.telefono}</td>
                                        <td >${item.correo}</td>
                                        <td >${item.broker}</td>

                                        <td scope="col-2">


                                            <a class="btn btn-info" role="button" href="editarcliente.htm?id=${item.idCliente}" ><i class="fas fa-edit"></i></a> 
                                            <!--  <button  class="btn btn-info" type="button" id="editar" onclick="editar('${item.idCliente}', '${item.idDistrito.idDistrito}')" ><i class="fas fa-edit"></i></button>  -->
                                            <button  class="btn btn-warning" type="button" onclick="eliminar('${item.idCliente}')" ><i class="fas fa-trash-alt"></i></button>
                                        </td>



                                    </tr>
                                </c:forEach>



                            </tbody>
                        </table>

                        <center>
                            <button><a href="menu.htm" id="regre">Regresar al Menú</a></button>
                        </center>

                    </div>
                </div>
            </div>



            <script>
                function editar(id, iddistrito) {
                    $("#idcliente").val(id);

                    $.ajax({
                        type: 'get',
                        url: 'obtienecliente.htm',
                        data: {id: id},
                        success: function (response) {
                            var obj = JSON.parse(response.toString('utf8'));
                            $('#razonSocial').val(obj.razonSocial);
                            $('#ruc').val(obj.ruc);
                            $('#direccion').val(obj.direccion);
                            $('#telefono').val(obj.telefono);
                            $('#correo').val(obj.correo);
                            $('#broker').val(obj.broker);
                            $('#usuario').val(obj.usuario);
                            $('#clave').val(obj.clave);
                            $('#idDistrito').val(iddistrito);
                            $('#myModal').modal('show');
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            alert("error:" + textStatus + " exception:" + errorThrown);
                        }
                    });
                }
            </script>

        </div>
    
    <script type="text/javascript">
        function eliminar(id) {
            if (confirm("¿Desea eliminar el cliente?")) {
                window.location.href = "eliminarcliente.htm?id=" + id;
                return true;
            }
            return false;
        }
    </script>

</body>

</html>
