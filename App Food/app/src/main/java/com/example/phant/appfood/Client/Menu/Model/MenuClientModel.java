package com.example.phant.appfood.Client.Menu.Model;

import android.content.Context;

import com.example.phant.appfood.Client.Menu.Presenter.MenuClientPresenter;

public class MenuClientModel {
    private Context context;
    private MenuClientPresenter presenter;

    public MenuClientModel(Context context, MenuClientPresenter presenter) {
        this.context = context;
        this.presenter = presenter;
    }
}
