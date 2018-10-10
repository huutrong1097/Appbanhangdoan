package com.example.phant.appfood.Register.Presenter;

import com.example.phant.appfood.Register.Model.User;

public interface RegisterPresenter {
    void taoTaiKhoanSuccess(User user);
    void taoTaiKhoanFailure(String messages);

}
