package com.example.phant.appfood.Admin.Menu.Presenter;

import com.example.phant.appfood.Model.Food;

public interface MenuAdminPresenter {
    void addFoodSuccess();
    void addFoodFailure(String messages);
    void loadDataMenuSuccess(Food food);
    void loaddataMenuFailure(String messages);
}
