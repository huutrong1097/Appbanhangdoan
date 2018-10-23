package com.example.phant.appfood.Client.Chat.Presenter;

import com.example.phant.appfood.Model.Chat;

public interface ChatClientPresenter {
    void getMessagesSuccess(Chat chat);
    void getMessagesFailure(String messages);
}
