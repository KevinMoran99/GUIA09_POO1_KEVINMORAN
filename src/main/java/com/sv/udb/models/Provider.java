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
public class Provider {
    @Getter @Setter private int id;
    @Getter @Setter private String name;
    @Getter @Setter private String address;
    @Getter @Setter private String phone;

    public Provider() {
    }

    public Provider(int id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return name;
    }
    
    
}
