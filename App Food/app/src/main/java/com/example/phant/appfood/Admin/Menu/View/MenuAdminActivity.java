package com.example.phant.appfood.Admin.Menu.View;

import android.Manifest;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

import com.example.phant.appfood.Admin.Menu.Adapter.MenuAdminFoodAdapter;
import com.example.phant.appfood.Admin.Menu.Dialog.AddFoodFragmentDialog;
import com.example.phant.appfood.Admin.Menu.Fragment.MenuAdminDetailFoodFragment;
import com.example.phant.appfood.Admin.Menu.Presenter.MenuAdminPresenterImp;
import com.example.phant.appfood.Model.Food;
import com.example.phant.appfood.R;
import com.example.phant.appfood.databinding.ActivityMenuAdminBinding;

import java.util.ArrayList;
import java.util.List;

public class MenuAdminActivity extends AppCompatActivity implements MenuAdminView {
    private ActivityMenuAdminBinding binding;
    private FragmentManager fragmentManager;
    private MenuAdminPresenterImp presenterImp;
    private List<String> listTypeFood;
    private MenuAdminFoodAdapter foodAdapter;
    private MenuAdminDetailFoodFragment detailFoodFragment;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_menu_admin);
        this.bundle = savedInstanceState;
        this.configView();
        this.listenResult();
        this.aksPermissions();
    }

    void configView() {
        this.fragmentManager = getSupportFragmentManager();
        this.presenterImp = new MenuAdminPresenterImp(this, this);
        this.listTypeFood = new ArrayList<>();
        this.listTypeFood.add("Gỏi");
        this.listTypeFood.add("Cuốn");
        this.listTypeFood.add("Cơm");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.layout.recyclerView.setLayoutManager(linearLayoutManager);
        this.foodAdapter = new MenuAdminFoodAdapter(listTypeFood);
        binding.layout.recyclerView.setAdapter(foodAdapter);
    }

    void listenResult() {
        foodAdapter.onCallback(new MenuAdminFoodAdapter.Callback() {
            @Override
            public void reuslt(String type) {
                presenterImp.getDataFirebase(type);
                binding.containerFragmentAdmin.setVisibility(View.VISIBLE);
                if (bundle == null) {
                    detailFoodFragment = new MenuAdminDetailFoodFragment();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerFragmentAdmin, detailFoodFragment, "detailFood");
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else {
                    detailFoodFragment = (MenuAdminDetailFoodFragment) fragmentManager.findFragmentByTag("detailFood");
                }
            }
        });
    }

    void aksPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 123);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addFood:
                AddFoodFragmentDialog dialogAddFood = new AddFoodFragmentDialog();
                dialogAddFood.onCallback(new AddFoodFragmentDialog.Callback() {
                    @Override
                    public void resultFood(Food food, String typeFood) {
                        presenterImp.postFood(food, typeFood);
                    }
                });
                dialogAddFood.show(fragmentManager, null);
                break;
        }
        return true;
    }

    @Override
    public void showProgressBar() {
        binding.layout.recyclerView.setVisibility(View.INVISIBLE);
        binding.layout.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        binding.layout.recyclerView.setVisibility(View.VISIBLE);
        binding.layout.progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showMessages(String messages) {
        Toast.makeText(MenuAdminActivity.this, messages, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayFragmentDetailFood(List<Food> foodList) {
        detailFoodFragment.receiverFood(foodList);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 123 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Xin quyền thành công!", Toast.LENGTH_SHORT).show();
        }
    }
}
