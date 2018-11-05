package com.example.phant.appfood.Client.Order.View;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.example.phant.appfood.Client.Order.Adapter.OrderClientAdapter;
import com.example.phant.appfood.Client.Order.Presenter.OrderClientPresenterIml;
import com.example.phant.appfood.Model.Order;
import com.example.phant.appfood.Model.User;
import com.example.phant.appfood.R;
import com.example.phant.appfood.databinding.ActivityOrderClientBinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderClientActivity extends AppCompatActivity implements OrderClientView {
    private ActivityOrderClientBinding binding;
    private OrderClientAdapter adapter;
    private List<Order> list;
    private OrderClientPresenterIml presenterIml;
    private Intent intent;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order_client);
        this.configView();
        this.resultAdapter();
    }

    void configView() {
        intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        presenterIml = new OrderClientPresenterIml(this, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recyclerViewOrder.setLayoutManager(linearLayoutManager);
        list = new ArrayList<>();
        adapter = new OrderClientAdapter(list);
        binding.recyclerViewOrder.setAdapter(adapter);
        presenterIml.getListOrder(user.getIdUser());
    }

    void resultAdapter() {
        adapter.onCallback(new OrderClientAdapter.CallbackOrderClientAdapter() {
            @Override
            public void result(Order order) {
                intent = new Intent(OrderClientActivity.this, OrderClientStepActivity.class);
                intent.putExtra("order", (Serializable) order);
                intent.putExtra("type", 1);
                startActivity(intent);
            }
        });
    }

    @Override
    public void display(List<Order> stringList) {
        list.addAll(stringList);
        adapter.notifyDataSetChanged();
    }
}
