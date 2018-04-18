/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.models;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Estudiante
 */
public class Warehouse {
    @Getter @Setter private int id;
    @Getter @Setter private Piece piece;
    @Getter @Setter private Provider provider;
    @Getter @Setter private int quantity;
    @Getter @Setter private Date date;

    public Warehouse() {
    }

    public Warehouse(int id, Piece piece, Provider provider, int quantity, Date date) {
        this.id = id;
        this.piece = piece;
        this.provider = provider;
        this.quantity = quantity;
        this.date = date;
    }
    
    @Override
    public String toString() {
        return String.valueOf(id);
    }
    
    
}
