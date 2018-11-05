package com.example.phant.appfood.Client.Order.Presenter;

import android.content.Context;

import com.example.phant.appfood.Client.Order.Model.OrderClientModel;
import com.example.phant.appfood.Client.Order.View.OrderClientView;
import com.example.phant.appfood.Model.Order;

import java.util.List;

public class OrderClientPresenterIml implements OrderClientPresenter {
    private Context context;
    private OrderClientModel model;
    private OrderClientView view;

    public OrderClientPresenterIml(Context context, OrderClientView view) {
        this.context = context;
        this.view = view;
        model = new OrderClientModel(context, this);
    }

    public void getListOrder(String customer) {
        model.getListOrder(customer);
    }



    @Override
    public void getListOrderSuccess(List<Order> stringList) {
        view.display(stringList);
    }
}
