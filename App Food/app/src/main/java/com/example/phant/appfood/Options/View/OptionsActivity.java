package com.example.phant.appfood.Options.View;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.phant.appfood.Admin.Chat.View.ChatAdminActivity;
import com.example.phant.appfood.Admin.Menu.View.MenuAdminActivity;
import com.example.phant.appfood.Admin.Order.View.OrderAdminActivity;
import com.example.phant.appfood.Client.Chat.View.ChatClientActivity;
import com.example.phant.appfood.Client.Menu.View.MenuClientActivity;
import com.example.phant.appfood.Client.Order.View.OrderClientActivity;
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
                if (user.getIdUser().equals("G1lLx7aCEJZD43ODEeuxo9U0AY82")) {
                    intent = new Intent(OptionsActivity.this, MenuAdminActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                } else {
                    intent = new Intent(OptionsActivity.this, MenuClientActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                }

            }
        });
        binding.actionChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getIdUser().equals("G1lLx7aCEJZD43ODEeuxo9U0AY82")) {
                    intent = new Intent(OptionsActivity.this, ChatAdminActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                } else {
                    intent = new Intent(OptionsActivity.this, ChatClientActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                }

            }
        });
        binding.actionOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getIdUser().equals("G1lLx7aCEJZD43ODEeuxo9U0AY82")) {
                    intent = new Intent(OptionsActivity.this, OrderAdminActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                } else {
                    intent = new Intent(OptionsActivity.this, OrderClientActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                }
            }
        });
    }
}
