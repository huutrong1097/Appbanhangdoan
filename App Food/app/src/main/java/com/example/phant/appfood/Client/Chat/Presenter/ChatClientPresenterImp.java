package com.example.phant.appfood.Client.Chat.Presenter;

import android.content.Context;

import com.example.phant.appfood.Client.Chat.Model.ChatClientModel;
import com.example.phant.appfood.Client.Chat.View.ChatClientView;
import com.example.phant.appfood.Model.Chat;
import com.example.phant.appfood.Model.User;

public class ChatClientPresenterImp implements ChatClientPresenter {
    private Context context;
    private ChatClientView view;
    private ChatClientModel model;
    private User user;

    public ChatClientPresenterImp(Context context, ChatClientView view, User user) {
        this.context = context;
        this.view = view;
        this.user  =user;
        this.config();
    }

    void config() {
        this.model = new ChatClientModel(context, this);
    }

    public void getData() {
        view.showLoading();
        model.getDataMessages();
    }

    public void sendMessages(Chat chat) {
        model.sendMessages(chat);
    }

    @Override
    public void getMessagesSuccess(Chat chat) {
    if (user.getIdUser().equals(chat.getUser().getIdUser())){
        view.hideLoading();
        view.displayChat(chat);
    }else return;
    }

    @Override
    public void getMessagesFailure(String messages) {
        view.hideLoading();
        view.showMessages(messages);
    }
}
