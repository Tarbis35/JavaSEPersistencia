package org.home.gatos_app.service;

import com.google.gson.Gson;
import com.squareup.okhttp.*;
import org.home.gatos_app.Gato;
import org.home.gatos_app.GatoFav;
import org.home.gatos_app.utils.ImageUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public interface GatoService {
    static void verGatos(){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.thecatapi.com/v1/images/search")
                .method("GET", null)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String json = response.body().string();
            json = json.substring(1);
            json = json.substring(0,json.length()-1);
            Gson gson = new Gson();
            Gato gato = gson.fromJson(json, Gato.class);


            ImageUtils<Gato, ImageIcon> scaleImage = cat ->  {
                ImageIcon fondoGato= null;
                try {
                    fondoGato = new ImageIcon(ImageIO.read(new URL(cat.getUrl())));
                    if(fondoGato.getIconWidth() > 800){
                        Image fondo = fondoGato.getImage();
                        Image modificada = fondo.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);
                        fondoGato = new ImageIcon(modificada);                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    return fondoGato;
                }
            };
            ImageIcon imagenGato = scaleImage.scaleImage(gato);

            String menu = "Opciones : \n"
                    +" 1. Ver otra imagen \n"
                    +" 2. Favorito \n"
                    +" 3. Volver \n";
            String[] botones = {"Ver otra imagen", "Favorito", "Volver"};

            String opcion = (String) JOptionPane.showInputDialog(null
                    , menu
                    , gato.getId()
                    , JOptionPane.INFORMATION_MESSAGE
                    , imagenGato
                    , botones
                    , botones[0]);
            int seleccion = Arrays.asList(botones).indexOf(opcion);
            switch (seleccion){
                case 0:
                    verGatos();
                    break;
                case 1:
                    favoritoGato(gato);
                    break;
                default:
                    break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void favoritoGato(Gato gato){
        try {


            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\"image_id\" :\""+gato.getId()+"\"}");
            Request request = new Request.Builder()
                    .url("https://api.thecatapi.com/v1/favourites")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("x-api-key", gato.getApiKey())
                    .build();
            Response response = client.newCall(request).execute();
            System.out.println("Response: "+response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void verFavoritos(String apiKey){
        try {

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.thecatapi.com/v1/favourites")
                    .method("GET", null)
                    .addHeader("x-api-key", apiKey)
                    .build();
            Response response = client.newCall(request).execute();
            String json = response.body().string();
            Gson gson = new Gson();
            GatoFav[] gatosArray = gson.fromJson(json, GatoFav[].class);
            int i= 0;
            if (gatosArray.length > 1) {
                i = (int) (Math.random() * (gatosArray.length-1));
            } else i = 0;
            GatoFav gatoFav = gatosArray[i];
            ImageUtils<GatoFav, ImageIcon> scaleImage = cat ->  {
                ImageIcon fondoGato= null;
                try {
                    fondoGato = new ImageIcon(ImageIO.read(new URL(cat.getImagen().getUrl())));
                    if(fondoGato.getIconWidth() > 800){
                        Image fondo = fondoGato.getImage();
                        Image modificada = fondo.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);
                        fondoGato = new ImageIcon(modificada);                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    return fondoGato;
                }
            };
            ImageIcon imagenGato = scaleImage.scaleImage(gatoFav);

            String menu = "Opciones : \n"
                    +" 1. Ver otra imagen \n"
                    +" 2. Remover favorito \n"
                    +" 3. Volver \n";
            String[] botones = {"Ver otra imagen", "Remover favorito", "Volver"};

            String opcion = (String) JOptionPane.showInputDialog(null
                    , menu
                    , gatoFav.getId()
                    , JOptionPane.INFORMATION_MESSAGE
                    , imagenGato
                    , botones
                    , botones[0]);
            int seleccion = Arrays.asList(botones).indexOf(opcion);
            switch (seleccion){
                case 0:
                    verFavoritos(gatoFav.getApiKey());
                    break;
                case 1:
                    removerFavorito(gatoFav);
                    break;
                default:
                    break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void removerFavorito(GatoFav gatoFav) {
        try {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://api.thecatapi.com/v1/favourites/" + gatoFav.getId())
                    .delete(null)
                    .addHeader("x-api-key", gatoFav.getApiKey())
                    .build();

            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
