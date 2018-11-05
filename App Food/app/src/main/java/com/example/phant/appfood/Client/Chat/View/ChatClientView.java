package com.example.phant.appfood.Client.Chat.View;

import com.example.phant.appfood.Model.Chat;

public interface ChatClientView {
    void showMessages(String messages);
    void displayChat(Chat chat);
    void showLoading();
    void hideLoading();
    void chaneMessa(Chat chat);
}
