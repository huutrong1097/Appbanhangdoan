package com.example.phant.appfood.Register.Model;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.phant.appfood.Model.User;
import com.example.phant.appfood.Register.Presenter.RegisterPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterModel {
    private FirebaseAuth xacThuc;
    private RegisterPresenter presenter;

  private Context context;

    public RegisterModel( Context context,RegisterPresenter presenter) {
        this.presenter = presenter;
        this.context = context;
        this.xacThuc = FirebaseAuth.getInstance();
    }

    public void taoTaiKhoan(String email, final String pass){

        xacThuc.createUserWithEmailAndPassword(email,pass).addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = xacThuc.getCurrentUser();
                    presenter.taoTaiKhoanSuccess(user.getEmail(),pass);
                }else {
                    presenter.taoTaiKhoanFailure(String.valueOf(task.getException()));
                }
            }
        });

    }
}
