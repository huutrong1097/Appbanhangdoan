package com.example.phant.appfood.Register.View;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.phant.appfood.R;
import com.example.phant.appfood.Model.User;
import com.example.phant.appfood.Register.Presenter.RegisterPresenterImp;
import com.example.phant.appfood.databinding.ActivityRegisterBinding;
import com.google.firebase.database.DatabaseReference;

public class RegisterActivity extends AppCompatActivity implements RegisterView {
    private ActivityRegisterBinding binding;
    private RegisterPresenterImp presenterImp;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        this.confit();
        this.listenEvent();

    }

    void confit() {
        presenterImp = new RegisterPresenterImp(this, this);
        intent = getIntent();
    }

    void listenEvent() {
        binding.buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.editEmail.getText().toString();
                String pass = binding.editPass.getText().toString();
                String xnPass = binding.editXnPass.getText().toString();
                if (email.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Bạn chưa nhập email!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (pass.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Bạn chưa nhập mật khẩu!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (pass.length() < 6) {
                    Toast.makeText(RegisterActivity.this, "Mật khẩu phải dài hơn 6 kí tự!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (xnPass.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Bạn chưa xác nhận mật khẩu!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!xnPass.equals(pass)) {
                    Toast.makeText(RegisterActivity.this, "Xác nhận mật khẩu sai!", Toast.LENGTH_SHORT).show();
                    return;
                }
                presenterImp.startRegister(email, pass);
            }
        });
    }

    @Override
    public void showProgressBar() {
        binding.progressBarRegister.setVisibility(View.VISIBLE);
        binding.linearLayout5.setVisibility(View.INVISIBLE);
        binding.buttonRegister.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgressBar() {
        binding.progressBarRegister.setVisibility(View.GONE);
        binding.linearLayout5.setVisibility(View.VISIBLE);
        binding.buttonRegister.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMessages(String messages) {
        Toast.makeText(RegisterActivity.this, messages, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getTaiKhoan(String email, String pass) {
        intent.putExtra("email", email);
        intent.putExtra("pass", pass);
        setResult(RESULT_OK, intent);
        finish();
    }


}
