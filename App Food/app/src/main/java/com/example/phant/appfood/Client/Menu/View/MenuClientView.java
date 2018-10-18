package com.example.phant.appfood.Client.Menu.View;

import com.example.phant.appfood.Model.Food;

import java.util.List;

public interface MenuClientView {
    void showProgressBar();
    void hideProgressBar();
    void showMessages(String messages);
    void displayFragmentFood(List<Food> foods);
}
