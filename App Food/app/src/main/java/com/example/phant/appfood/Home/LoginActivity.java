package com.example.phant.appfood.Home;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.phant.appfood.R;
import com.example.phant.appfood.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
   // private ActivityLoginBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // binding = DataBindingUtil.
                setContentView( R.layout.activity_options);

    }
}
