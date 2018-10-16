package com.example.phant.appfood.Client.Menu.View;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.phant.appfood.R;
import com.example.phant.appfood.databinding.ActivityMenuBinding;

public class MenuActivity extends AppCompatActivity implements MenuView {
    private ActivityMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_menu);
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
