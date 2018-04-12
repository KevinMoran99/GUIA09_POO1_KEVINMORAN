/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controllers;

import com.sv.udb.models.Piece;
import com.sv.udb.models.Provider;
import com.sv.udb.models.Warehouse;
import com.sv.udb.resources.ConnectionDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Estudiante
 */
public class WarehouseController {
    Connection conn;

    public WarehouseController() {
        conn = new ConnectionDB().getConn();
    }
    
    /**
     * Obtiene todas las bodegas registradas
     * @return Una lista conteniendo todas las bodegas
     */
    public List<Warehouse> getAll () {
        List<Warehouse> resp = new ArrayList<>();
        try {
            PreparedStatement cmd = this.conn.prepareStatement("SELECT b.codi_bode, pie.*, pro.*, "
                    + "b.cant, b.fecha_comp  FROM Bodega b INNER JOIN Piezas pie ON b.codi_piez = pie.codi_piez "
                    + "INNER JOIN Proveedores pro ON b.codi_prov = pro.codi_prov");
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                resp.add(
                        new Warehouse(
                                rs.getInt(1), 
                                new Piece(rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5)),
                                new Provider(rs.getInt(6),rs.getString(7),rs.getString(8),rs.getString(9)),
                                rs.getInt(10),
                                rs.getDate(11)
                        ));
            }
        } catch (SQLException ex) {
            System.err.println("Error al consultar bodega: " + ex.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    if (!this.conn.isClosed()) {
                        this.conn.close();
                    }
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión al consultar bodega: " + ex.getMessage());
            }
        }
        return resp;
    }
    
    /**
     * Ingresa una nueva bodega
     * @param piece La pieza de la bodega
     * @param provider El proveedor de la bodega
     * @param quantity La cantidad de piezas
     * @return Booleano que indica si la operación fue exitosa o no
     */
    public boolean insert (Piece piece, Provider provider, int quantity) {
        boolean resp = false;
        
        try {
            PreparedStatement cmd = this.conn.prepareStatement("INSERT INTO Bodega VALUES(NULL,?,?,?,NOW())");
            //ESTABLECER VALORES DE LA QUERY
            cmd.setInt(1, piece.getId());
            cmd.setInt(2, provider.getId());
            cmd.setInt(3, quantity);
            cmd.executeUpdate();
            resp = true;
        } catch (Exception ex) {
            System.err.println("Error al guardar bodega: " + ex.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    if (!this.conn.isClosed()) {
                        this.conn.close();
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexion al guardar bodega: : " + e.getMessage());
            }
        }
        
        return resp;
    }
    
    /**
     * 
     * @param id El id de la bodega a modificar
     * @param piece La pieza de la bodega
     * @param provider El proveedor de la bodega
     * @param quantity La cantidad de piezas
     * @return Booleano que indica si la operación fue exitosa o no
     */
    public boolean update (int id, Piece piece, Provider provider, int quantity) {
        boolean resp = false;
        
        try {
            PreparedStatement cmd = this.conn.prepareStatement("UPDATE Bodega SET codi_piez = ?, codi_prov = ?, cant = ? WHERE codi_bode = ?");
            //ESTABLECER VALORES DE LA QUERY
            cmd.setInt(1, piece.getId());
            cmd.setInt(2, provider.getId());
            cmd.setInt(3, quantity);
            cmd.setInt(4, id);
            cmd.executeUpdate();
            resp = true;
        } catch (Exception ex) {
            System.err.println("Error al modificar bodega " + ex.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    if (!this.conn.isClosed()) {
                        this.conn.close();
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexion al modificar bodega : " + e.getMessage());
            }
        }
        
        return resp;
    }
    
    /**
     * 
     * @param id El id de la bodega a eliminar
     * @return Booleano que indica si la operación fue exitosa o no
     */
    public boolean delete (int id) {
        boolean resp = false;
        
        try {
            PreparedStatement cmd = this.conn.prepareStatement("DELETE FROM Bodega WHERE codi_bode = ?");
            //ESTABLECER VALORES DE LA QUERY
            cmd.setInt(1, id);
            cmd.executeUpdate();
            resp = true;
        } catch (Exception ex) {
            System.err.println("Error al eliminar bodega: " + ex.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    if (!this.conn.isClosed()) {
                        this.conn.close();
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexion al eliminar bodega: " + e.getMessage());
            }
        }
        
        return resp;
    }
    
    
    public Warehouse get(int id) {
        Warehouse resp = null;
        try {
            PreparedStatement cmd = this.conn.prepareStatement("SELECT b.codi_bode, pie.*, pro.*, "
                    + "b.cant, b.fecha_comp  FROM Bodega b INNER JOIN Piezas pie ON b.codi_piez = pie.codi_piez "
                    + "INNER JOIN Proveedores pro ON b.codi_prov = pro.codi_prov WHERE codi_bode = ?");
            cmd.setInt(1, id);
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                resp = new Warehouse(
                                rs.getInt(1), 
                                new Piece(rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5)),
                                new Provider(rs.getInt(6),rs.getString(7),rs.getString(8),rs.getString(9)),
                                rs.getInt(10),
                                rs.getDate(11)
                        );
            }
        } catch (SQLException ex) {
            System.err.println("Error al consultar bodega: " + ex.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    if (!this.conn.isClosed()) {
                        this.conn.close();
                    }
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión al consultar bodega: " + ex.getMessage());
            }
        }
        return resp;
    }
    
}
