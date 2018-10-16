package com.example.phant.appfood.Admin.Menu.Presenter;

public interface MenuAdminPresenter {
    void addFoodSuccess();
    void addFoodFailure(String messages);
    void loadDataMenuSuccess();
    void loaddataMenuFailure(String messages);
}
