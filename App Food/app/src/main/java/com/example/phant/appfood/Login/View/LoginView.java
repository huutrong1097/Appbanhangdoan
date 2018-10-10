package com.example.phant.appfood.Login.View;

import com.example.phant.appfood.Model.User;

public interface LoginView {
    void showProgressBar();
    void hideProgressBar();
    void showMessages(String messages);
    void getUser(User user);
}
