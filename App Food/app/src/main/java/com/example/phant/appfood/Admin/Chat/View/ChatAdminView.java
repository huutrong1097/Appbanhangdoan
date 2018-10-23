package com.example.phant.appfood.Admin.Chat.View;

public interface ChatAdminView {
    void showLoading();
    void hideLoading();
    void showMessages(String messages);
    void displayListUser(String user);
}
