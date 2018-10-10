package com.example.phant.appfood.Register.View;

public interface RegisterView {
    void showProgressBar();
    void hideProgressBar();
    void showMessages(String messages);
    void getTaiKhoan(String email,String pass);
}
