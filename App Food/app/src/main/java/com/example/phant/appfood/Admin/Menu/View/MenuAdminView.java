package com.example.phant.appfood.Admin.Menu.View;

import com.example.phant.appfood.Model.Food;

import java.util.List;

public interface MenuAdminView {
    void showProgressBar();
    void hideProgressBar();
    void showMessages(String messages);
    void displayFragmentDetailFood(List<Food> foodList);
}
