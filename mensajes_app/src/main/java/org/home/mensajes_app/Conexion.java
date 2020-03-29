package org.home.mensajes_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public Connection get_connection(){
        Connection conection = null;
        try {
            conection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mensajes_app", "mensajes_app","welcome1");
            if(conection != null){
                System.out.println("conexión realizada");
            }
        }catch (SQLException e) {
            System.err.println("Error al conectar \n"+e);
        }
        return conection;
    }
}
