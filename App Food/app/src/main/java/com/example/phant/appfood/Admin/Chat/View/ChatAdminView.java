package com.example.phant.appfood.Admin.Chat.View;

import com.example.phant.appfood.Model.Chat;

import java.util.List;

public interface ChatAdminView {
    void showLoading();
    void hideLoading();
    void showMessages(String messages);
    void displayListUser(List<Chat> listUser);
    void displayFragmentChat(Chat chat);
}
