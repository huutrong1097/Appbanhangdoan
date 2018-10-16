package com.example.phant.appfood.Admin.Menu.View;

import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.phant.appfood.Admin.Menu.Presenter.MenuAdminPresenterImp;
import com.example.phant.appfood.Model.Food;
import com.example.phant.appfood.R;
import com.example.phant.appfood.databinding.ActivityMenuAdminBinding;

public class MenuAdminActivity extends AppCompatActivity implements MenuAdminView {
    private ActivityMenuAdminBinding binding;
    private FragmentManager fragmentManager;
    private MenuAdminPresenterImp presenterImp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_menu_admin);
        this.config();
    }

    void config() {
        this.fragmentManager = getSupportFragmentManager();
        this.presenterImp = new MenuAdminPresenterImp(this,this);
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
                    public void resultFood(Food food) {
                        Log.e("nhan", food.getName());
                        presenterImp.postFood(food);
                    }
                });
                dialogAddFood.show(fragmentManager, null);
                break;
        }
        return true;
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void showMessages(String messages) {

    }

}
