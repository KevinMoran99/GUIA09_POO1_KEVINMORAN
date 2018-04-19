<%-- 
    Document   : index
    Created on : 04-12-2018, 04:28:27 PM
    Author     : Estudiante
--%>

<%@page import="com.sv.udb.utilities.Utils"%>
<%@page import="java.util.Date"%>
<%@page import="com.sv.udb.controllers.ProviderController"%>
<%@page import="com.sv.udb.models.Provider"%>
<%@page import="com.sv.udb.controllers.PieceController"%>
<%@page import="com.sv.udb.models.Piece"%>
<%@page import="com.sv.udb.controllers.WarehouseController"%>
<%@page import="com.sv.udb.models.Warehouse"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
        <script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
        <script type="text/javascript" src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/pdfjs/build/pdf.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/js/pdfobject.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/js/report.js"></script>
        <style>
            /*
            PDFObject appends the classname "pdfobject-container" to the target element.
            This enables you to style the element differently depending on whether the embed was successful.
            In this example, a successful embed will result in a large box.
            A failed embed will not have dimensions specified, so you don't see an oddly large empty box.
            */

            .pdfobject-container {
                    width: 100%;
                    height: 600px;
                    margin: 2em 0;
            }

            .pdfobject { border: solid 1px #666; }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <h1>Bodegas</h1>
                <div class="col-md-5">
                    <div class="panel panel-primary">
                        <div class="panel-heading">El Formulario</div>
                        <div class="panel-body">
                            <c:choose>
                                <c:when test="${error}">
                                    <div class="alert alert-danger">
                                        ${message}
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="alert alert-success">
                                        ${message}
                                    </div>
                                </c:otherwise>
                            </c:choose>
                            <form method="POST" action="process.jsp" name="Demo">
                                <input type="hidden" name="id" id="id" value="${id}"/>
                                <div class="form-group">
                                    <label for="team">Pieza:</label>
                                    <select class="form-control" name="piece.id" id="piece">
                                        <c:forEach var="pieceItem" items="<%=new PieceController().getAll()%>">
                                            <c:choose>
                                                <c:when test="${pieceItem.getId() == piece}">
                                                    <option value="${pieceItem.getId()}" selected>${pieceItem}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${pieceItem.getId()}">${pieceItem}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                     </select>
                                </div>
                                <div class="form-group">
                                    <label for="team">Proveedor:</label>
                                    <select class="form-control" name="provider.id" id="prov">
                                        <c:forEach var="provItem" items="<%=new ProviderController().getAll()%>">
                                            <c:choose>
                                                <c:when test="${provItem.getId() == prov}">
                                                    <option value="${provItem.getId()}" selected>${provItem}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${provItem.getId()}">${provItem}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="nomb">Cantidad:</label>
                                    <input type="number" min="1" class="form-control" name="quantity" id="quan" value="${quan}"/>
                                </div>
                                <div class="form-group">
                                    <label>Fecha de compra:</label>
                                    <c:choose>
                                        <c:when test="${date != null}">
                                            <p><fmt:formatDate pattern = "dd/MM/yyyy" value = "${date}" /></p>
                                        </c:when>
                                        <c:otherwise>
                                            <p><fmt:formatDate pattern = "dd/MM/yyyy" value = "<%=new Date()%>" /></p>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                
                                <c:choose>
                                    <c:when test="${update == null}">
                                        <input type="submit" class="btn btn-default" name="teamBtn" value="Guardar"/>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="submit" class="btn btn-default" name="teamBtn" value="Nuevo"/>
                                        <input type="submit" class="btn btn-primary" name="teamBtn" value="Modificar"/>
                                        <input type="submit" class="btn btn-danger" name="teamBtn" value="Eliminar" onclick="return confirm('¿Desea eliminar este registro?')"/>
                                    </c:otherwise>
                                </c:choose>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-md-7">
                    <div class="panel panel-primary">
                        <div class="panel-heading">La Tabla</div>
                        <div class="panel-body">
                            <form method="POST" action="WarehouseServ" name="Tabl">
                                <display:table id="tablWare" export="true" name="<%= new WarehouseController().getAll()%>">
                                    <display:column title="Cons">
                                        <input type="radio" name="wareCodeRadio" value="${tablWare.id}"/>
                                    </display:column>
                                    <display:column property="piece" title="Pieza" sortable="true" />
                                    <display:column property="provider" title="Proveedor" sortable="true" />
                                    <display:column property="quantity" title="Cantidad" sortable="true" />
                                    <display:column property="date" title="Fecha de compra" sortable="true" format="{0,date,dd/MM/yyyy}" />
                                </display:table>
                                <input type="submit" class="btn btn-success" name="teamBtn" value="Consultar"/>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="panel panel-primary">
                    <div class="panel-heading">Generar reporte</div>
                        <div class="panel-body">
                            <form id="reportForm" data-ctxt="${pageContext.request.contextPath}">
                                <div class="form-group col-sm-5">
                                    <label for="from">Desde</label>
                                    <input type="date" class="form-control" name="from" id="from" required/>
                                </div>
                                <div class="form-group col-sm-5">
                                    <label for="to">Hasta</label>
                                    <input type="date" class="form-control" name="to" id="to" required/>
                                </div>
                                <label for="btnReport" class="text-white">.</label>
                                <input id="btnReport" type="submit" class="btn btn-success col-sm-2" name="teamBtn" value="Generar reporte"/>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
                                
            
        </div>
                                
        <!-- Modal -->
        <div class="modal fade" id="modalReport" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                        <h4 class="modal-title" id="myModalLabel">Reporte de bodegas</h4>
                        </div>
                    <div class="modal-body">
                        <div id="pdfViewer"></div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Salir</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
