package com.example.phant.appfood.Register.Presenter;

import android.content.Context;

import com.example.phant.appfood.Register.Model.RegisterModel;
import com.example.phant.appfood.Model.User;
import com.example.phant.appfood.Register.View.RegisterView;

public class RegisterPresenterImp implements RegisterPresenter {
    private Context context;
    private RegisterView view;
    private RegisterModel model;

    public RegisterPresenterImp(Context context, RegisterView view) {
        this.context = context;
        this.view = view;
        this.model = new RegisterModel(context, this);
    }

    public void startRegister(String email, String pass) {
        view.showProgressBar();
        model.taoTaiKhoan(email, pass);
    }


    @Override
    public void taoTaiKhoanSuccess(String email, String pass) {
        view.hideProgressBar();
        view.showMessages("Tạo tài khoản thành công!");
        view.getTaiKhoan(email,pass);
    }

    @Override
    public void taoTaiKhoanFailure(String messages) {
        view.hideProgressBar();
        view.showMessages(messages);
    }
}
