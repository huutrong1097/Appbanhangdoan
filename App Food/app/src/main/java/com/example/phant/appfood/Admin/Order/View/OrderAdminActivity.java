package com.example.phant.appfood.Admin.Order.View;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.example.phant.appfood.Admin.Order.Adapter.OrderAdminAdapter;
import com.example.phant.appfood.Admin.Order.Fragment.OrderAdminDetailFragment;
import com.example.phant.appfood.Admin.Order.Presenter.OrderAdminPresenterImp;
import com.example.phant.appfood.Model.Order;
import com.example.phant.appfood.R;
import com.example.phant.appfood.databinding.ActivityOrderAdminBinding;

import java.util.ArrayList;
import java.util.List;

public class OrderAdminActivity extends AppCompatActivity implements OrderAdminView {
    private ActivityOrderAdminBinding binding;
    private List<Order> orders;
    private OrderAdminPresenterImp presenterImp;
    private OrderAdminAdapter adapter;
    private List<String> keyOrder;
    private OrderAdminDetailFragment fragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order_admin);
        this.configView();
    }

    void configView() {
        presenterImp = new OrderAdminPresenterImp(this, this);
        fragmentManager = getSupportFragmentManager();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recyclerViewOrderAdmin.setLayoutManager(linearLayoutManager);
        orders = new ArrayList<>();
        adapter = new OrderAdminAdapter(orders);
        binding.recyclerViewOrderAdmin.setAdapter(adapter);
        presenterImp.getListOrder();
        keyOrder = new ArrayList<>();
        adapter.onCallback(new OrderAdminAdapter.CallbackOrderAdminAdapter() {
            @Override
            public void result(Order order) {
                fragment = new OrderAdminDetailFragment(order);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.containerOrderAdminFragment, fragment, "fragment");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                fragment.onCallback(new OrderAdminDetailFragment.CallbackDetailFragment() {
                    @Override
                    public void result(Order order) {
                        presenterImp.upDateOrder(order);
                    }
                });
            }
        });
    }

    @Override
    public void display(Order order) {
        keyOrder.add(0, order.getKey());
        orders.add(0, order);
        adapter.notifyDataSetChanged();
    }
}
