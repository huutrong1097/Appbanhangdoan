package com.example.phant.appfood.Client.Menu.Presenter;

import android.content.Context;

import com.example.phant.appfood.Client.Menu.View.MenuView;

public class MenuPresenterImp {
    private Context context;
    private MenuView view;

    public MenuPresenterImp(Context context, MenuView view) {
        this.context = context;
        this.view = view;
    }
}
