package com.example.phant.appfood.Register.Presenter;

import android.content.Context;

import com.example.phant.appfood.Register.Model.RegisterModel;
import com.example.phant.appfood.Register.Model.User;
import com.example.phant.appfood.Register.View.RegisterView;

public class RegisterPresenterImp implements RegisterPresenter{
    private Context context;
    private RegisterView view;
    private RegisterModel model;

    public RegisterPresenterImp(Context context, RegisterView view) {
        this.context = context;
        this.view = view;
        this.model = new RegisterModel(context,this);
    }

    @Override
    public void taoTaiKhoanSuccess(User user) {

    }

    @Override
    public void taoTaiKhoanFailure(String messages) {

    }
}
