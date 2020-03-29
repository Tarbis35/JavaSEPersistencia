package org.home.mensajes_app;

import org.home.mensajes_app.service.MensajeService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Inicio {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("-----------------------");
            System.out.println("Aplicacion de mensajes ");
            System.out.println("1. Crear mensaje.");
            System.out.println("2. Listar mensajes.");
            System.out.println("3. Editar mensajes.");
            System.out.println("4. Eliminar mensajes.");
            System.out.println("5. Salir");

            opcion = scanner.nextInt();

            switch (opcion){
                case 1:
                    MensajeService.crearMensaje();
                    break;
                case 2:
                    MensajeService.listarMensajes();
                    break;
                case 3:
                    MensajeService.editarMensaje();
                    break;
                case 4:
                    MensajeService.borrarMensaje();
                    break;
                default:
                    break;
            }

        }while(opcion != 5);
    }
}
