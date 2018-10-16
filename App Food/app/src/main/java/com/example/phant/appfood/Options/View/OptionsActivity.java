package com.example.phant.appfood.Options.View;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.phant.appfood.Admin.Menu.View.MenuAdminActivity;
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
        this.listenEvent();
    }

    void confit() {
        this.intent = getIntent();
        this.user = (User) intent.getSerializableExtra("user");
        Toast.makeText(OptionsActivity.this, user.getEmail(), Toast.LENGTH_SHORT).show();
    }

    void listenEvent() {
        binding.actionMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(OptionsActivity.this, MenuAdminActivity.class);
                startActivity(intent);
            }
        });
        binding.actionChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
