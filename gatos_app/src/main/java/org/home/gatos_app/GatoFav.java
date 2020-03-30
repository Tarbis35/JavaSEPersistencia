package org.home.gatos_app;

public class GatoFav {
    String id;
    String user_id;
    String image_id;
    String sub_id;
    String created_at;
    String apiKey = "7ae1048d-7a74-48e6-b407-c639fc6bc1ff";
    GatoImg image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getSub_id() {
        return sub_id;
    }

    public void setSub_id(String sub_id) {
        this.sub_id = sub_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public GatoImg getImagen() {
        return image;
    }

    public void setImagen(GatoImg image) {
        this.image = image;
    }
}
