/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.models;

import java.sql.Date;

/**
 *
 * @author Estudiante
 */
public class Warehouse {
    private int id;
    private Piece piece;
    private Provider provider;
    private int quantity;
    private Date date;

    public Warehouse() {
    }

    public Warehouse(int id, Piece piece, Provider provider, int quantity, Date date) {
        this.id = id;
        this.piece = piece;
        this.provider = provider;
        this.quantity = quantity;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
    
    
}
