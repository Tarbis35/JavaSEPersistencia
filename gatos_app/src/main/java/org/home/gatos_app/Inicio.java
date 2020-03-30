package org.home.gatos_app;

import org.home.gatos_app.service.GatoService;

import javax.swing.*;
import java.util.Arrays;
import java.util.stream.Stream;

public class Inicio {
    public static void main(String[] args) {
        int opcion_menu = -1;
        String[] botones = {"1. Ver Gatos"
                , "2. Favoritos"
                , "3. Salir"};
        do{
            String opcion =(String) JOptionPane.showInputDialog(null
                    , "Gatitos Java"
                    , "Menu principal"
                    ,JOptionPane.INFORMATION_MESSAGE
                    ,null,botones, botones[0]);

            opcion_menu = Arrays.asList(botones).indexOf(opcion);

            switch (opcion_menu){
                case 0:
                    GatoService.verGatos();
                    break;
                case 1:
                    GatoFav gato = new GatoFav();
                    GatoService.verFavoritos(gato.getApiKey());
                    break;
                case 2:
                    break;
                default :
                    break;
            }

        }while (opcion_menu != 2);

    }
}
