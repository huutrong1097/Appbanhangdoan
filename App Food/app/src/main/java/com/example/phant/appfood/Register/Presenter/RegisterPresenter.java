package com.example.phant.appfood.Register.Presenter;

import com.example.phant.appfood.Model.User;

public interface RegisterPresenter {
    void taoTaiKhoanSuccess(String email,String pass);
    void taoTaiKhoanFailure(String messages);

}
