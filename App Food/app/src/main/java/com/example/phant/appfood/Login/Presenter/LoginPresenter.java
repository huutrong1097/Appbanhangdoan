package com.example.phant.appfood.Login.Presenter;

import com.example.phant.appfood.Model.User;

public interface LoginPresenter {
    void loginSuccess(User user);

    void loginFailure(String messages);
}
