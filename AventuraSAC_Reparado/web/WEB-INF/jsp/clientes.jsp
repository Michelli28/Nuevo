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
    <body id="bodys">
        <header id="header">
            <a class="logo" href="menu.htm">Aventura S.A.C.</a>
            <nav>
                <a href="login.htm">Salir</a>
            </nav>
        </header>
        
        
        
        <div class="container md-8">

            <div class="card" id="carta">
                <div class="card-header">
                    <h3>Listado de Clientes</h3>
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

                <!-- Modal editar -->


                <div class="modal fade" id="myModal" role="dialog">
                    <div class="modal-dialog" >
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalCenterTitle">Editar Cliente</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">

                                </button>
                            </div>
                            <div class="modal-body">

                                <form id="mod" action="obtienecliente.htm" method="post">
                                    <input type="hidden" name="idCliente" id="idcliente"/>

                                    <div class="form-group">
                                        <label for="razonSocial">Razón Social:</label>
                                        <input type="text" name="razonSocial" id="razonSocial"  minlength="3" maxlength="40" required="required"/>
                                    </div>

                                    <div class="form-group">
                                        <label for="ruc">RUC:</label>
                                        <input type="text" name="ruc" min="10000000000" man="99999999999" required pattern="[00000000000-99999999999]" />
                                    </div>

                                    <div>
                                        <label for="idDistrito">Distrito:</label>
                                        <select name="idDistrito.idDistrito" >
                                            <c:forEach items="${listaDistrito}" var="x">
                                                <c:if test="${x.idDistrito == cliente.idDistrito.idDistrito}">
                                                    <option value="${x.idDistrito}" selected="selected" id="idDistrito" required>${x.detalle}</option>
                                                </c:if>
                                                <c:if test="${x.idDistrito != cliente.idDistrito.idDistrito}">
                                                    <option value="${x.idDistrito}" id="idDistrito" required>${x.detalle}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select > 
                                    </div>

                                    <br>
                                    <div class="form-group">
                                        <label for="direccion">Dirección:</label>
                                        <input type="text" name="direccion" id="direccion" maxlength="30" required="required" />
                                    </div>
                                    <div class="form-group">
                                        <label for="telefono">Teléfono</label>
                                        <input type="number" name="telefono" id="telefono" min="0000000" maxlength="999999999" />
                                    </div>
                                    <div class="form-group">
                                        <label for="correo">Correo:</label>
                                        <input type="email" name="correo" id="correo" required="required"/>
                                    </div>
                                    <div class="form-group">
                                        <label for="broker">Broker:</label>
                                        <input type="text" name="broker" id="broker" maxlength="20" required="required"/>
                                    </div>
                                    <input type="hidden" name="usuario" id="usuario" value="" />
                                    <input type="hidden" name="clave" id="clave" value="" />

                                    <input type="submit" class="btn btn-primary"  value="Guardar">
                                    <a class="btn btn-secondary" href="clientes.htm" role="button">Regresar</a>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                <button type="button" class="btn btn-primary">Save changes</button>
                            </div>
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
