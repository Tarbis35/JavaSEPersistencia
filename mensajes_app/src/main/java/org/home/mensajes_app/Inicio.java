package org.home.mensajes_app;

import java.sql.Connection;
import java.sql.SQLException;

public class Inicio {
    public static void main(String[] args) {
        Conexion conexion = new Conexion();
        try(Connection cnx = conexion.get_connection()){
            System.out.println("");
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}
