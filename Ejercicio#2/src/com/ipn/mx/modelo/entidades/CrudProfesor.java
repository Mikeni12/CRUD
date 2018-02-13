/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.entidades;

import java.sql.SQLException;

/**
 *
 * @author vilchery
 */
public class CrudProfesor {
    

    
        
    
    public static void main(String args[]) throws SQLException{
       
        
        ProfesorDAO profesorDAO = new ProfesorDAO();
        int salir = 0;
        do{
            salir = profesorDAO.menu();
            
        }while(salir==1);
        
        
    }
    
    
}
