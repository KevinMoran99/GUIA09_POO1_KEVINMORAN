<%-- 
    Document   : process
    Created on : 04-19-2018, 04:47:17 PM
    Author     : Estudiante
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <jsp:useBean id="mrBean" class="com.sv.udb.models.Warehouse" scope="request">
            <jsp:setProperty name="mrBean" property="*"/>
        </jsp:useBean>
        <h2>
            <c:out value="${mrBean.piece.id}"></c:out>
        </h2>
    </body>
</html>
