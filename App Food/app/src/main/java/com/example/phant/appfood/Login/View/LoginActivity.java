package com.example.phant.appfood.Login.View;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.phant.appfood.Login.Presenter.LoginPresenterImp;
import com.example.phant.appfood.Model.User;
import com.example.phant.appfood.Options.View.OptionsActivity;
import com.example.phant.appfood.R;
import com.example.phant.appfood.Register.View.RegisterActivity;
import com.example.phant.appfood.databinding.ActivityLoginBinding;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements LoginView {
    private ActivityLoginBinding binding;
    private LoginPresenterImp presenterImp;
    private FirebaseAuth firebaseAuth;
    private Intent intent;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        this.confitView();
        this.listenEvent();
    }

    void confitView() {
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.presenterImp = new LoginPresenterImp(this, firebaseAuth, this);

    }

    void listenEvent() {
        binding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.editEmail.getText().toString();
                String pass = binding.editPass.getText().toString();
                presenterImp.starLogin(email, pass);
            }
        });
        binding.textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, 123);
            }
        });
    }

    @Override
    public void showProgressBar() {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.linearLayoutEdit.setVisibility(View.INVISIBLE);
        binding.buttonLogin.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgressBar() {
        binding.progressBar.setVisibility(View.GONE);
        binding.linearLayoutEdit.setVisibility(View.VISIBLE);
        binding.buttonLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMessages(String messages) {
        binding.progressBar.setVisibility(View.GONE);
        binding.linearLayoutEdit.setVisibility(View.VISIBLE);
        Toast.makeText(LoginActivity.this, messages, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getUser(User user) {
        intent = new Intent(LoginActivity.this,OptionsActivity.class);
        intent.putExtra("user",user);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123 && resultCode == RESULT_OK && data != null) {
            String email = data.getStringExtra("email");
            String pass = data.getStringExtra("pass");
            binding.editEmail.setText(email);
            binding.editPass.setText(pass);
        }
    }
}
