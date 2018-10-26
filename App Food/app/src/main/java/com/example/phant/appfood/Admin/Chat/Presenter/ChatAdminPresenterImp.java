package com.example.phant.appfood.Admin.Chat.Presenter;

import android.content.Context;

import com.example.phant.appfood.Admin.Chat.Model.ChatAdminModel;
import com.example.phant.appfood.Admin.Chat.View.ChatAdminView;
import com.example.phant.appfood.Model.Chat;
import com.example.phant.appfood.Model.User;

import java.util.List;

public class ChatAdminPresenterImp implements ChatAdminPresenter {
    private Context context;
    private ChatAdminView view;
    private ChatAdminModel model;
    private User user;

    public ChatAdminPresenterImp(Context context, ChatAdminView view, User user) {
        this.context = context;
        this.view = view;
        this.user = user;
        this.config();
    }

    void config() {
        model = new ChatAdminModel(context, this);
    }

    public void getListUser() {
        view.showLoading();
        model.getListUser(user);
    }

    public void getMessage(User user){
        model.getDataDetailChat(user);
    }

    public void sendMessage(Chat chat){
        model.sendMessages(chat);
    }


    @Override
    public void getListUserSuccess(List<Chat> listUser) {
        view.hideLoading();
        view.displayListUser(listUser);
    }

    @Override
    public void getDataFailure(String messages) {
        view.hideLoading();
        view.showMessages(messages);
    }

    @Override
    public void getDetailMessages(Chat chat) {
        view.displayFragmentChat(chat);
    }
}
