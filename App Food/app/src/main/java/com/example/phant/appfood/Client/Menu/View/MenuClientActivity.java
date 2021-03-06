package com.example.phant.appfood.Client.Menu.View;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.phant.appfood.Client.Menu.Adapter.MenuClientFoodAdapter;
import com.example.phant.appfood.Client.Menu.Fragment.MenuClientDetailFoodFragment;
import com.example.phant.appfood.Client.Menu.Presenter.MenuClientPresenterImp;
import com.example.phant.appfood.Client.Order.View.OrderClientCartActivity;
import com.example.phant.appfood.Model.Food;
import com.example.phant.appfood.Model.User;
import com.example.phant.appfood.R;
import com.example.phant.appfood.databinding.ActivityMenuBinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuClientActivity extends AppCompatActivity implements MenuClientView {
    private ActivityMenuBinding binding;
    private MenuClientPresenterImp presenterImp;
    private List<String> listTypeFood;
    private MenuClientFoodAdapter adapter;
    private MenuClientDetailFoodFragment detailFoodFragment;
    private FragmentManager fragmentManager;
    private Bundle bundle;
    private List<Food> listFoodOrder;
    private Intent intent;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_menu);
        this.bundle = savedInstanceState;
        this.configView();
        this.listenResult();
    }

    void configView() {
        intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        this.presenterImp = new MenuClientPresenterImp(this, this);
        this.fragmentManager = getSupportFragmentManager();
        listFoodOrder = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        listTypeFood = new ArrayList<>();
        listTypeFood.add("Gỏi");
        listTypeFood.add("Cuốn");
        listTypeFood.add("Cơm");
        this.adapter = new MenuClientFoodAdapter(listTypeFood);
        binding.recyclerView.setAdapter(adapter);
    }

    void listenResult() {
        adapter.onCallback(new MenuClientFoodAdapter.Callback() {
            @Override
            public void resultType(String typeFood) {
                presenterImp.getDataFood(typeFood);
                binding.containerFragment.setVisibility(View.VISIBLE);
                if (bundle == null) {
                    detailFoodFragment = new MenuClientDetailFoodFragment();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerFragment, detailFoodFragment, "detailFood");
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else {
                    detailFoodFragment = (MenuClientDetailFoodFragment) fragmentManager.findFragmentByTag("detailFood");
                }
                detailFoodFragment.onCallback(new MenuClientDetailFoodFragment.CallbackClientDetailFoodFragment() {
                    @Override
                    public void themFoodOrder(Food food) {
                        listFoodOrder.add(food);
                    }

                    @Override
                    public void xoaFoodOrder(Food food) {
                        listFoodOrder.remove(food);
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.create_order, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.idGioHang:
                if (listFoodOrder.size() > 0) {
                    intent = new Intent(MenuClientActivity.this, OrderClientCartActivity.class);
                    intent.putExtra("user", user);
                    intent.putExtra("data", (Serializable) listFoodOrder);
                    intent.putExtra("type", 0);
                    startActivity(intent);
                } else {
                    Toast.makeText(MenuClientActivity.this, "Giỏ hàng của bạn trống!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return true;
    }

    @Override
    public void showProgressBar() {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.recyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgressBar() {
        binding.progressBar.setVisibility(View.INVISIBLE);
        binding.recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMessages(String messages) {
        Toast.makeText(this, messages, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayFragmentFood(List<Food> foods) {
        detailFoodFragment.setListFood(foods);
    }
}
