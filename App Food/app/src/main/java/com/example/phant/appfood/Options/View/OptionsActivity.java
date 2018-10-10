package com.example.phant.appfood.Options.View;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.phant.appfood.Model.User;
import com.example.phant.appfood.R;
import com.example.phant.appfood.databinding.ActivityOptionsBinding;

public class OptionsActivity extends AppCompatActivity {
    private ActivityOptionsBinding binding;
    private User user;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_options);
        this.confit();
    }

    void confit() {
        this.intent = getIntent();
        this.user = (User) intent.getSerializableExtra("user");
        Toast.makeText(OptionsActivity.this, user.getEmail(), Toast.LENGTH_SHORT).show();
    }
}
