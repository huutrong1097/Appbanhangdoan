package com.example.phant.appfood.Login.Presenter;

import android.content.Context;

import com.example.phant.appfood.Login.Model.LoginModel;
import com.example.phant.appfood.Login.View.LoginView;
import com.example.phant.appfood.Model.User;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPresenterImp implements LoginPresenter {
    private Context context;
    private LoginView view;
    private LoginModel model;
    private FirebaseAuth firebaseAuth;

    public LoginPresenterImp(Context context, FirebaseAuth firebaseAuth, LoginView view) {
        this.context = context;
        this.view = view;
        this.firebaseAuth = firebaseAuth;
        this.confit();
    }

    void confit() {
        this.model = new LoginModel(context, firebaseAuth, this);
    }


    public void starLogin(String email, String pass) {
        view.showProgressBar();
        model.loginFirebase(email, pass);
    }

    @Override
    public void loginSuccess(User user) {
        view.showMessages("Đăng nhập thành công");
        view.getUser(user);
    }

    @Override
    public void loginFailure(String messages) {
        view.showMessages(messages);
    }
}
