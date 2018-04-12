/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Estudiante
 */
public class Utils {
    
    public static final int DATE_UI = 1;
    public static final int DATE_DB = 2;
    
    public static String formatDate (Date date, int type) {
        if (type == Utils.DATE_UI)
            return new SimpleDateFormat("dd/MM/yyyy").format(date);
        else
            return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
    
}
