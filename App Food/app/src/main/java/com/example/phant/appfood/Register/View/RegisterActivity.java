package com.example.phant.appfood.Register.View;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.phant.appfood.R;
import com.example.phant.appfood.Register.Model.RegisterModel;
import com.example.phant.appfood.Register.Model.User;
import com.example.phant.appfood.databinding.ActivityRegisterBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    private FirebaseAuth xacThuc;
    private DatabaseReference databaseUser;
    private RegisterModel registerModel;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        xacThuc = FirebaseAuth.getInstance();
        registerModel = new RegisterModel(xacThuc, this);

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
                user = registerModel.taoTaiKhoan(email, pass);
                Toast.makeText(RegisterActivity.this, user.getEmail(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
