/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.views;

import com.sv.udb.controllers.PieceController;
import com.sv.udb.controllers.ProviderController;
import com.sv.udb.controllers.WarehouseController;
import com.sv.udb.models.Piece;
import com.sv.udb.models.Provider;
import com.sv.udb.models.Warehouse;
import com.sv.udb.utilities.ReportGenerator;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Estudiante
 */
@WebServlet(name = "WarehouseServ", urlPatterns = {"/WarehouseServ"})
public class WarehouseServ extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            boolean isPost = request.getMethod().equals("POST");
            String message = "", error = "";
            if(!isPost) {
                String CRUD = request.getParameter("teamBtn");
                if (CRUD.equals("Reporte")) {
                    String from = request.getParameter("from");
                    String to = request.getParameter("to");
                    byte[] bytes = ReportGenerator.detailReport(getServletContext(), from, to);
                    ServletOutputStream out = response.getOutputStream();
                    if (bytes != null) {
                        response.setContentType("application/pdf");
                        response.setHeader("Content-Disposition", "inline; filename=bodega.pdf");
                        response.setContentLength(bytes.length);
                        out.write(bytes, 0, bytes.length);
                        out.flush();
                        out.close();
                    }
                    else {
                        StringWriter stringWriter = new StringWriter();
                        PrintWriter printWriter = new PrintWriter(stringWriter);
                        response.setContentType("text/plain");
                        response.getOutputStream().print(stringWriter.toString());
                    }
                    
                }
                
                else {
                    response.sendRedirect(request.getContextPath() + "/index.jsp");
                }
                        
            }
            else {
                String CRUD = request.getParameter("teamBtn");
                if(CRUD.equals("Guardar")) {
                    Piece piece = new PieceController().get(Integer.parseInt(request.getParameter("piece")));
                    Provider prov = new ProviderController().get(Integer.parseInt(request.getParameter("prov")));
                    int quan = Integer.parseInt(request.getParameter("quan"));
                    
                    if (new WarehouseController().insert(piece, prov, quan))
                        message = "Datos guardados";
                    else {
                        message = "Error al guardar";
                        error = "true";
                    }
                }
                else if (CRUD.equals("Consultar")) {
                    int code = Integer.parseInt(request.getParameter("wareCodeRadio") == null ? "-1" : request.getParameter("wareCodeRadio"));
                    Warehouse ware = new WarehouseController().get(code);
                    if (ware != null) {
                        request.setAttribute("id", ware.getId());
                        request.setAttribute("piece", ware.getPiece().getId());
                        request.setAttribute("prov", ware.getProvider().getId());
                        request.setAttribute("quan", ware.getQuantity());
                        request.setAttribute("date", ware.getDate());

                        message = "Información consultada";

                        request.setAttribute("update", "true");
                    }
                    else {
                        message = "Error al consultar";
                        error = "true";
                    }
                }
                else if (CRUD.equals("Modificar")) {
                    Piece piece = new PieceController().get(Integer.parseInt(request.getParameter("piece")));
                    Provider prov = new ProviderController().get(Integer.parseInt(request.getParameter("prov")));
                    int quan = Integer.parseInt(request.getParameter("quan"));
                    if (new WarehouseController().update(Integer.parseInt(request.getParameter("id")), piece, prov, quan))
                        message = "Datos modificados";
                    else {
                        message = "Error al modificar";
                        error = "true";
                    }
                }
                else if (CRUD.equals("Eliminar")) {
                    if (new WarehouseController().delete(Integer.parseInt(request.getParameter("id"))))
                        message = "Datos eliminados";
                    else {
                        message = "Error al eliminar";
                        error = "true";
                    }
                }
                
                else {
                    message = "Parámetro inválido";
                    error = "true";
                }
                
                request.setAttribute("message", message);
                request.setAttribute("error", error);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            //throw new ServletException(e);
            request.setAttribute("message", "Llene todos los campos con datos válidos");
            request.setAttribute("error", "true");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
