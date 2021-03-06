package com.example.phant.appfood.Client.Order.View;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.example.phant.appfood.Client.Order.Adapter.OrderClientCartAdapter;
import com.example.phant.appfood.Model.Food;
import com.example.phant.appfood.Model.User;
import com.example.phant.appfood.R;
import com.example.phant.appfood.databinding.ActivityCartBinding;

import java.io.Serializable;
import java.util.List;

public class OrderClientCartActivity extends AppCompatActivity {
    private ActivityCartBinding binding;
    private List<Food> foodList;
    private Intent intent;
    private OrderClientCartAdapter adapter;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cart);
        this.configView();
        this.listenClcik();
    }

    void configView() {
        intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        foodList = (List<Food>) intent.getSerializableExtra("data");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recyclerViewListGioHang.setLayoutManager(linearLayoutManager);
        adapter = new OrderClientCartAdapter(foodList);
        binding.recyclerViewListGioHang.setAdapter(adapter);
    }
    void listenClcik(){
        binding.buttonCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(OrderClientCartActivity.this,OrderClientStepActivity.class);
                intent.putExtra("user",user);
                intent.putExtra("listCart", (Serializable) foodList);
                startActivity(intent);
                finish();
            }
        });
    }
}
