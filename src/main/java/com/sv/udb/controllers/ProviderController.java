/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controllers;

import com.sv.udb.models.Provider;
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
public class ProviderController {
    Connection conn;

    public ProviderController() {
        conn = new ConnectionDB().getConn();
    }
    
    public List<Provider> getAll () {
        List<Provider> resp = new ArrayList<>();
        try {
            PreparedStatement cmd = this.conn.prepareStatement("SELECT * FROM Proveedores");
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                resp.add(new Provider(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException ex) {
            System.err.println("Error al consultar proveedores: " + ex.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    if (!this.conn.isClosed()) {
                        this.conn.close();
                    }
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión al consultar proveedores: " + ex.getMessage());
            }
        }
        return resp;
    }
    
    public Provider get(int id) {
        Provider resp = null;
        try {
            PreparedStatement cmd = this.conn.prepareStatement("SELECT * FROM proveedores WHERE codi_prov = ?");
            cmd.setInt(1, id);
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                resp = new Provider(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
        } catch (SQLException ex) {
            System.err.println("Error al consultar proveedor: " + ex.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    if (!this.conn.isClosed()) {
                        this.conn.close();
                    }
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión al consultar proveedor: " + ex.getMessage());
            }
        }
        return resp;
    }
}
