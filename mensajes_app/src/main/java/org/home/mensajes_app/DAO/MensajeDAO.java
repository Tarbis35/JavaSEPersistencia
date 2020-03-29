package org.home.mensajes_app.DAO;

import org.home.mensajes_app.DB.IDBConnection;
import org.home.mensajes_app.model.Mensaje;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public interface MensajeDAO {

    static void CrearMensajeDB(Mensaje mensaje){
        try(Connection cnx =  IDBConnection.connectionToDB()){
            String query = "INSERT INTO MENSAJES (mensaje, autor_mensaje) VALUES (?, ?)";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setString(1, mensaje.getMensaje());
            preparedStatement.setString(2, mensaje.getAutor_mensaje());
             preparedStatement.executeUpdate();
            System.out.println("Mensaje creado");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    static Mensaje consultarMensaje(int id_mensaje){
        try(Connection cnx =  IDBConnection.connectionToDB()){
            String query = "SELECT * FROM MENSAJES";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                return new Mensaje(rs.getInt("id_mensaje"),
                        rs.getString("mensaje"),
                        rs.getString("autor_mensaje"),
                        rs.getString("fecha_mensaje"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Mensaje();
    }
    static void leeMensajesDB(){
        try(Connection cnx =  IDBConnection.connectionToDB()){
            String query = "SELECT * FROM MENSAJES";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                System.out.println(rs.getInt("id_mensaje")+". "+
                        rs.getString("mensaje"));
                System.out.println(rs.getString("autor_mensaje")+" "+
                        new Date(rs.getTimestamp("fecha_mensaje").getTime()));


            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    static void borrarMensajeDB(int id_mensaje){
        try(Connection cnx =  IDBConnection.connectionToDB()){
            String query = "DELETE FROM MENSAJES WHERE id_mensaje = ?";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setInt(1, id_mensaje);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    static void actualizarMensajeDB(Mensaje mensaje){
        try(Connection cnx =  IDBConnection.connectionToDB()){
            String query = "UPDATE MENSAJES SET mensaje = ? WHERE id_mensaje = ?";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setString(1, mensaje.getMensaje());
            preparedStatement.setInt(2, mensaje.getId_mensaje());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
