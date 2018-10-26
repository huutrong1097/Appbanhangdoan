package com.example.phant.appfood.Model;

public class DetailChat {
    String idUser;
    String bodyMessages;
    String imageMessages;

    public DetailChat(String idUser, String bodyMessages, String imageMessages) {
        this.idUser = idUser;
        this.bodyMessages = bodyMessages;
        this.imageMessages = imageMessages;
    }

    public DetailChat() {
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getBodyMessages() {
        return bodyMessages;
    }

    public void setBodyMessages(String bodyMessages) {
        this.bodyMessages = bodyMessages;
    }

    public String getImageMessages() {
        return imageMessages;
    }

    public void setImageMessages(String imageMessages) {
        this.imageMessages = imageMessages;
    }
}
