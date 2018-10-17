package com.example.phant.appfood.Admin.Menu.Presenter;

import android.content.Context;

import com.example.phant.appfood.Admin.Menu.Model.MenuAdminModel;
import com.example.phant.appfood.Admin.Menu.View.MenuAdminView;
import com.example.phant.appfood.Model.Food;

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

    public void postFood(Food food,String typeFood) {
        model.upLoadFood(food,typeFood);
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
    public void loadDataMenuSuccess() {

    }

    @Override
    public void loaddataMenuFailure(String messages) {

    }
}
