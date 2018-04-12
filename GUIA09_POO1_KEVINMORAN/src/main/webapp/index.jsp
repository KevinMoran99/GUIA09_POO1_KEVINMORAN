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
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
        <script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
        <script type="text/javascript" src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <%
            boolean update = Boolean.parseBoolean((String)request.getAttribute("update"));
            String btnName = update ? "Nuevo" : "Guardar"; // Para el texto del botón
            String btnEditClass = update ? "" : "display: none"; //Para ocultar botones
            
            int pieceCode;
            try {
                pieceCode = (Integer)request.getAttribute("piece"); 
            } catch (Exception e) {
                pieceCode = 0;
            }
            
            int provCode;
            try {
                provCode = (Integer)request.getAttribute("prov"); 
            } catch (Exception e) {
                provCode = 0;
            }
            
        %>
        <div class="container">
            <div class="row">
                <h1>Bodegas</h1>
                <div class="col-md-5">
                    <div class="panel panel-primary">
                        <div class="panel-heading">El Formulario</div>
                        <div class="panel-body">
                            <div class="alert alert-success">
                                ${message}
                            </div>
                            <form method="POST" action="WarehouseServ" name="Demo">
                                <input type="hidden" name="code" id="code" value="${id}"/>
                                <div class="form-group">
                                    <label for="team">Pieza:</label>
                                    <select class="form-control" name="piece" id="piece">
                                    <% 
                                        for(Piece temp : new PieceController().getAll())
                                        {
                                    %>
                                        <option value="<%= temp.getId() %>"
                                                <% 
                                                    if (temp.getId() == pieceCode) {%>
                                                    selected
                                                    <% } %>
                                                ><%= temp %></option>
                                    <%
                                        }
                                    %>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="team">Proveedor:</label>
                                    <select class="form-control" name="prov" id="prov">
                                    <% 
                                        for(Provider temp : new ProviderController().getAll())
                                        {
                                    %>
                                        <option value="<%= temp.getId() %>"
                                                <% 
                                                    if (temp.getId() == provCode) {%>
                                                    selected
                                                    <% } %>
                                                ><%= temp %></option>
                                    <%
                                        }
                                    %>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="nomb">Cantidad:</label>
                                    <input type="number" min="1" class="form-control" name="quan" id="quan" value="${quan}"/>
                                </div>
                                <div class="form-group">
                                    <label>Fecha de compra:</label>
                                    <p><%= Utils.formatDate(new Date(), Utils.DATE_UI) %></p>
                                </div>
                                <input type="submit" class="btn btn-default" name="teamBtn" value="Guardar"/>
                                <input type="submit" class="btn btn-primary" style="" name="teamBtn" value="Modificar"/>
                                <input type="submit" class="btn btn-danger" style="" name="teamBtn" value="Eliminar" onclick="return confirm('¿Desea eliminar este registro?')"/>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-md-7">
                    <div class="panel panel-primary">
                        <div class="panel-heading">La Tabla</div>
                        <div class="panel-body">
                            <form method="POST" action="WarehouseServ" name="Tabl">
                                <table class="table table-bordered">
                                    <tr>
                                        <th>Cons</th>
                                        <th>Pieza</th>
                                        <th>Proveedor</th>
                                        <th>Cantidad</th>
                                        <th>Fecha de compra</th>
                                    </tr>
                                    <%
                                        for(Warehouse temp : new WarehouseController().getAll())
                                        {
                                    %>
                                        <tr>
                                            <td><input type="radio" name="wareCodeRadio" value="<%= temp.getId() %>"/></td>
                                            <td><%= temp.getPiece().getName() %></td>
                                            <td><%= temp.getProvider().getName() %></td>
                                            <td><%= temp.getQuantity() %></td>
                                            <td><%= Utils.formatDate(temp.getDate(), Utils.DATE_UI) %></td>
                                        </tr>
                                    <%
                                        }
                                    %>
                                </table>
                                <input type="submit" class="btn btn-success" name="teamBtn" value="Consultar"/>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
                                
            
        </div>
    </body>
</html>
