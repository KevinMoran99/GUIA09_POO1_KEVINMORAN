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
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
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
            boolean isValid = request.getMethod().equals("POST");
            String message = "";
            if(!isValid) {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
            else {
                String CRUD = request.getParameter("teamBtn");
                if(CRUD.equals("Guardar")) {
                    Piece piece = new PieceController().get(Integer.parseInt(request.getParameter("piece")));
                    Provider prov = new ProviderController().get(Integer.parseInt(request.getParameter("prov")));
                    int quan = Integer.parseInt(request.getParameter("quan"));
                    
                    if (new WarehouseController().insert(piece, prov, quan))
                        message = "Datos guardados";
                    else 
                        message = "Error al guardar";
                }
                else if (CRUD.equals("Consultar")) {
                    int code = Integer.parseInt(request.getParameter("wareCodeRadio") == null ? "-1" : request.getParameter("wareCodeRadio"));
                    Warehouse ware = new WarehouseController().get(code);
                    if (ware != null) {
                        request.setAttribute("id", ware.getId());
                        request.setAttribute("piece", ware.getPiece().getName());
                        request.setAttribute("prov", ware.getProvider().getName());
                        request.setAttribute("quan", ware.getQuantity());
                        request.setAttribute("date", ware.getDate());

                        message = "Información consultada";

                        request.setAttribute("update", "true");
                    }
                    else
                        message = "Error al consultar";
                }
                
                request.setAttribute("message", message);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new ServletException(e);
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
