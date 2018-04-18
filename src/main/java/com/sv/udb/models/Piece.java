/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.models;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Estudiante
 */
public class Piece {
    @Getter @Setter private int id;
    @Getter @Setter private String name;
    @Getter @Setter private String type;
    @Getter @Setter private String brand;
    
    public Piece() {
    }

    public Piece(int id, String name, String type, String brand) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.brand = brand;
    }

    @Override
    public String toString() {
        return name;
    }
    
    
}
