package com.example.phant.appfood.Admin.Menu.View;

import com.example.phant.appfood.Model.Food;

public interface MenuAdminView {
    void showProgressBar();
    void hideProgressBar();
    void showMessages(String messages);
    void displayFragmentDetailFood(Food food);
}
