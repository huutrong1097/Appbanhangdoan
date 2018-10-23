package com.example.phant.appfood.Admin.Chat.Presenter;

import android.content.Context;

import com.example.phant.appfood.Admin.Chat.Model.ChatAdminModel;
import com.example.phant.appfood.Admin.Chat.View.ChatAdminView;

public class ChatAdminPresenterImp implements ChatAdminPresenter {
    private Context context;
    private ChatAdminView view;
    private ChatAdminModel model;

    public ChatAdminPresenterImp(Context context, ChatAdminView view) {
        this.context = context;
        this.view = view;
        this.config();
    }

    void config() {
        model = new ChatAdminModel(context, this);
    }

    public void getListUser() {
        model.getListUser();
    }


    @Override
    public void getListUserSuccess(String user) {
        view.displayListUser(user);
    }

    @Override
    public void getDataFailure(String messages) {

    }
}
