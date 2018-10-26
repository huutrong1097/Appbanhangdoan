package com.example.phant.appfood.Admin.Chat.Presenter;

import com.example.phant.appfood.Model.Chat;

import java.util.List;

public interface ChatAdminPresenter {
    void getListUserSuccess(List<Chat> listUser);
    void getDataFailure(String messages);
    void getDetailMessages(Chat chat);
}
