/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controllers;

import com.sv.udb.models.Piece;
import com.sv.udb.resources.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Estudiante
 */
public class PieceController {
    Connection conn;

    public PieceController() {
        conn = new ConnectionDB().getConn();
    }
    
    public List<Piece> getAll () {
        List<Piece> resp = new ArrayList<>();
        try {
            PreparedStatement cmd = this.conn.prepareStatement("SELECT * FROM Piezas");
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                resp.add(new Piece(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException ex) {
            System.err.println("Error al consultar piezas: " + ex.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    if (!this.conn.isClosed()) {
                        this.conn.close();
                    }
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión al consultar piezas: " + ex.getMessage());
            }
        }
        return resp;
    }
    
    public Piece get(int id) {
        Piece resp = null;
        try {
            PreparedStatement cmd = this.conn.prepareStatement("SELECT * FROM piezas WHERE codi_piez = ?");
            cmd.setInt(1, id);
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                resp = new Piece(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
        } catch (SQLException ex) {
            System.err.println("Error al consultar pieza: " + ex.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    if (!this.conn.isClosed()) {
                        this.conn.close();
                    }
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión al consultar pieza: " + ex.getMessage());
            }
        }
        return resp;
    }
}
