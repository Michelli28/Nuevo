<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
    <head>
        <title>Orden de Compra</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <link href="<c:url value="webapp/resources/theme1/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/EstilosOrdenCompra2.css" />" rel="stylesheet">
        <link href="<c:url value="webapp/resources/theme1/css/main.css" />" rel="stylesheet" />
    </head>
    <body>
        <header id="header">
            <a class="logo" href="index.html">Aventura S.A.C.</a>
            <nav>
                <a href="#menu">Menu</a>
            </nav>
        </header>
        <form>
            <section id="Contenedor1" class="wrapper" >
                <div id="Contenedorlogo" class="inner" style="border:9px double">
                    <div id="Contenedorlogo2" class="content">
                        <div class="row">
                            <div class="col" id="imagen">
                                <h1><img src="images/LogoAventura.png"></h1>
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
                                    <H3><strong>Orden de Compra</strong></H3>
                                    <div class="row" id="ngui">
                                        N° &nbsp;&nbsp;&nbsp;<input type="text" name="nroguia">&nbsp;-&nbsp;0000041
                                    </div>
                                </fieldset>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <section id="main" class="wrapper" >
                <div id="main2" class="inner" style="border:9px double">
                    <div id="main3" class="content">
                        <fieldset id="fieldset1" style="border: 1px solid gray">
                            <legend><h5><STRONG>Ingresar datos</STRONG></h5></legend>
                            <div class="row" id="grupo1">
                                <div class="col" id="fil1">
                                    <label for="idEmpleado">ID de Empleado:</label>
                                    <input type="text" size="30" class="form-control" placeholder="Ingresar ID">
                                </div>
                                <div class="col" id="fil2">
                                    <label for="idProveedor">ID de Proveedor:</label>
                                    <input type="text" size="30" class="form-control" placeholder="Ingresar ID">
                                </div>
                            </div>

                            <br>

                            <div class="row" id="grupo2">
                                <div class="col" id="fil3">
                                    <label for="fechapedido">Fecha del Pedido:</label>
                                    <input type="text" size="30" class="form-control" placeholder="Ingresar fecha">
                                </div>
                                <div class="col" id="fil4">
                                    <label for="fechaentrega">Fecha de Entrega:</label>
                                    <input type="text" size="30" class="form-control" placeholder="Ingresar entrega">
                                </div>
                            </div>
                        </fieldset>

                        <hr style="border: 1px solid gray"><!--Aqui esta la linea-->

                        <div class="form-group row">
                            <div class="col" id="col1">
                                <fieldset id="fieldset2" style="border: 1px solid gray">
                                    <legend><h5><STRONG>Ingresar datos</STRONG></h5></legend>
                                    <div class="row" id="fila3">
                                        <div class="col-sm-2" >
                                            <label for="cantidad">Cantidad:</label>
                                            <input type="text" name="cantidad" placeholder=" Ingresar cantidad">
                                        </div>
                                        <div class="col-sm-2">
                                            <label for="descripcion">Descripción:</label>
                                            <input type="text" name="descripcion" id="descripcion" placeholder="Ingresar descripcion">
                                        </div>
                                        <div class="col-sm-2" id="Agregar">		
                                            <button type="button">AGREGAR</button> 
                                        </div>
                                    </div>
                                </fieldset>	
                            </div>


                            <div class="col" id="col2">
                                <fieldset id="fieldset3" style="border: 1px solid gray">	
                                    <legend><h5><STRONG>Ingresar Datos</STRONG></h5></legend>
                                    <div class="row" id="fila4" align="center">
                                        <div class="col-sm-4" >
                                            <label for="elaborado">Elaborado por:</label>
                                            <input type="text" name="elaborado" placeholder="Elaborado por">
                                        </div>
                                        <div class="col-sm-4">
                                            <label for="autorizado">Autorizado por:</label>
                                            <input type="text" name="autorizado" placeholder="Autorizado por">
                                        </div>
                                        <div class="col-sm-4">		
                                            <label for="recibido">Recibido por:</label>
                                            <input type="text" name="recibido"  placeholder="Recibido por">
                                        </div>
                                    </div>
                                </fieldset>
                            </div>
                        </div>
                        <table id="tabla">
                            <tr id="col1">
                                <th>ID Orden de Compra</th>
                                <th>Item</th>
                                <th>ID Insumo</th>
                                <th>Cantidad</th>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                        </table>

                    </div>
                </div>
                </div>
            </section>
        </form>
    </body>
</html>

