package org.home.mensajes_app.service;

import org.home.mensajes_app.DAO.MensajeDAO;
import org.home.mensajes_app.model.Mensaje;

import java.util.Scanner;
import java.util.function.Consumer;

public class MensajeService {
    public static void crearMensaje(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe tu mensaje:");
        String mensaje = sc.nextLine();

        System.out.println("Tu nombre: ");
        String autor = sc.nextLine();
        Consumer<Mensaje> crearMensaje = MensajeDAO::CrearMensajeDB;
        crearMensaje.accept(new Mensaje(mensaje, autor));

    }
    public static void listarMensajes(){
        MensajeDAO.leeMensajesDB();
    }
    public static void borrarMensaje(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa el id del mensaje a borrar: ");
        MensajeDAO.borrarMensajeDB(sc.nextInt());

    }
    public static void editarMensaje(){
        MensajeDAO.leeMensajesDB();
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el nuevo mensaje");
        String txt = sc.nextLine();
        System.out.println("Escoja el mensaje a borrar :");
        int id_mensaje = sc.nextInt();

        System.out.println("");
        Mensaje mensaje = MensajeDAO.consultarMensaje(id_mensaje);
        System.out.println("Modificando el mensaje : ");
        mensaje.toString();
        mensaje.setMensaje(txt);
        MensajeDAO.actualizarMensajeDB(mensaje);

    }
}
