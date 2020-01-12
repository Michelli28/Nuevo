<%-- 
    Document   : combo
    Created on : 25/11/2019, 07:14:51 PM
    Author     : CHELLI BONITA
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <label for="cboArea">Área:</label>
                            <select name="cboArea">
                                <c:forEach var="x" items="${lista}">
                                    <option value="${x.idArea}">${x.detalle}</option>
                                </c:forEach>
                            </select>
    </body>
</html>
