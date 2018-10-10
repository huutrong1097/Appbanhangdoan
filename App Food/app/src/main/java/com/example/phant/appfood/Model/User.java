package com.example.phant.appfood.Model;

public class User {
    String idUser,email;

    public User(String idUser, String email) {
        this.idUser = idUser;
        this.email = email;
    }

    public User() {

    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
