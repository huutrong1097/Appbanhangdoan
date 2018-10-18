package com.example.phant.appfood.Admin.Menu.Presenter;

import com.example.phant.appfood.Model.Food;

import java.util.List;

public interface MenuAdminPresenter {
    void addFoodSuccess();
    void addFoodFailure(String messages);
   void getDataFoodSuccess(List<Food> foods);
   void getDataFoodFailure(String message);
}
