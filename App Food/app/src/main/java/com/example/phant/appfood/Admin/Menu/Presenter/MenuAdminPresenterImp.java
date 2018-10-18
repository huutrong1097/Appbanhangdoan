package com.example.phant.appfood.Admin.Menu.Presenter;

import android.content.Context;
import android.util.Log;

import com.example.phant.appfood.Admin.Menu.Model.MenuAdminModel;
import com.example.phant.appfood.Admin.Menu.View.MenuAdminView;
import com.example.phant.appfood.Model.Food;

import java.util.List;

public class MenuAdminPresenterImp implements MenuAdminPresenter {
    private Context context;
    private MenuAdminView view;
    private MenuAdminModel model;

    public MenuAdminPresenterImp(Context context, MenuAdminView view) {
        this.context = context;
        this.view = view;
        this.config();
    }

    void config() {
        this.model = new MenuAdminModel(context, this);
    }

    public void postFood(Food food, String typeFood) {
        model.upLoadFood(food, typeFood);
    }

    public void getDataFirebase(String typeFood) {
        view.showProgressBar();
        model.getDataFood(typeFood);
    }


    @Override
    public void addFoodSuccess() {
        view.showMessages("Món ăn thành công!");
    }

    @Override
    public void addFoodFailure(String messages) {
        view.showMessages(messages);
    }

    @Override
    public void getDataFoodSuccess(List<Food> foods) {
        view.hideProgressBar();
        view.displayFragmentDetailFood(foods);
    }

    @Override
    public void getDataFoodFailure(String message) {
        view.hideProgressBar();
        view.showMessages(message);
    }

}
