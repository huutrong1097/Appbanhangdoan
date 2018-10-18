package com.example.phant.appfood.Client.Menu.Presenter;

import com.example.phant.appfood.Model.Food;

import java.util.List;

public interface MenuClientPresenter {
    void getListFoodSuceess(List<Food> foods);
    void getListFoodFailure(String mesage);
}
