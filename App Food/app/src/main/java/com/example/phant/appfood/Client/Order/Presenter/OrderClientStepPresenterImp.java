package com.example.phant.appfood.Client.Order.Presenter;

import android.content.Context;

import com.example.phant.appfood.Client.Order.Model.OrderClientStepModel;
import com.example.phant.appfood.Client.Order.View.OrderClientStepView;
import com.example.phant.appfood.Model.Order;

public class OrderClientStepPresenterImp implements OrderClientStepPresenter{
    private Context context;
    private OrderClientStepView view;
    private OrderClientStepModel model;

    public OrderClientStepPresenterImp(Context context, OrderClientStepView view) {
        this.context = context;
        this.view = view;
        model = new OrderClientStepModel(context,this);
    }

    public void sendOrder(Order order){
        model.sendOrder(order);
    }
}
