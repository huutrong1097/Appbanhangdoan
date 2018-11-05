package com.example.phant.appfood.Admin.Order.Presenter;

import android.content.Context;

import com.example.phant.appfood.Admin.Order.Model.OrderAdminModel;
import com.example.phant.appfood.Admin.Order.View.OrderAdminView;
import com.example.phant.appfood.Model.Order;

public class OrderAdminPresenterImp implements OrderAdminPresenter {
    private Context context;
    private OrderAdminView view;
    private OrderAdminModel model;

    public OrderAdminPresenterImp(Context context, OrderAdminView view) {
        this.context = context;
        this.view = view;
        this.model = new OrderAdminModel(context,this);
    }

    public void getListOrder(){
        model.getListOrder();
    }
    public void upDateOrder(Order order){
        model.upDateOrder(order);
    }


    @Override
    public void resultListOrder(Order order) {
        view.display(order);
    }

}
