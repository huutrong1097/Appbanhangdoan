package com.example.phant.appfood.Client.Menu.Presenter;

import android.content.Context;

import com.example.phant.appfood.Client.Menu.Model.MenuClientModel;
import com.example.phant.appfood.Client.Menu.View.MenuClientView;
import com.example.phant.appfood.Model.Food;

import java.util.List;


public class MenuClientPresenterImp implements MenuClientPresenter {
    private Context context;
    private MenuClientView view;
    private MenuClientModel model;

    public MenuClientPresenterImp(Context context, MenuClientView view) {
        this.context = context;
        this.view = view;
        this.config();
    }

    void config() {
        this.model = new MenuClientModel(context, this);
    }

    public void getDataFood(String typeFood) {
        view.showProgressBar();
        model.getDataFood(typeFood);
    }


    @Override
    public void getListFoodSuceess(List<Food> foods) {
        view.hideProgressBar();
        view.displayFragmentFood(foods);
    }

    @Override
    public void getListFoodFailure(String mesage) {
        view.hideProgressBar();
        view.showMessages(mesage);
    }
}
